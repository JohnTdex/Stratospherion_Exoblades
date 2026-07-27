package net.johntdex.stratoblade.entity;

import net.johntdex.stratoblade.effect.ExoEffects;
import net.johntdex.stratoblade.item.ExoItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/**
 * The exorium axe in flight. Identical to the hatchet apart from calling down lightning on a
 * direct hit, and its own pose values (the model geometry differs).
 */
public class ExoriumAxeProjectileEntity extends ThrownAxeEntity {

    public ExoriumAxeProjectileEntity(EntityType<? extends ExoriumAxeProjectileEntity> entityType, Level level) {
        super(entityType, level);
    }

    public ExoriumAxeProjectileEntity(LivingEntity shooter, Level level, ItemStack thrownStack) {
        super(ExoEntities.EXORIUM_AXE_PROJECTILE.get(), shooter, level, thrownStack);
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(ExoItems.EXORIUM_AXE.get());
    }

    @Override
    protected float getImpactDamage() {
        return 9.0F;
    }

    // Heavier, metallic hits — the hatchet keeps the trident sounds from the base class.
    @Override
    protected SoundEvent getEntityHitSound() {
        return SoundEvents.ANVIL_PLACE;
    }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.ANVIL_PLACE;
    }

    /** Extra damage the lightning deals, on top of the axe's impact damage. */
    private static final float LIGHTNING_DAMAGE = 8.0F;
    /** Chance the strike arcs out as an electrocution splash. */
    private static final float ELECTROCUTION_CHANCE = 0.80F;
    /** Radius in blocks the splash reaches from the struck entity. */
    private static final double SPLASH_RADIUS = 3.0;
    private static final int ELECTROCUTION_TICKS = 60;  // 3s
    private static final int SLOWNESS_TICKS = 20;       // 1s

    @Override
    protected void applyOnHitEffects(Entity target, DamageSource damageSource) {
        if (!(this.level() instanceof ServerLevel serverLevel)) {
            return;
        }

        LightningBolt bolt = EntityType.LIGHTNING_BOLT.create(serverLevel);
        if (bolt == null) {
            return;
        }

        bolt.moveTo(Vec3.atBottomCenterOf(BlockPos.containing(target.position())));
        // Credits the kill to the thrower rather than "struck by lightning".
        if (this.getOwner() instanceof ServerPlayer thrower) {
            bolt.setCause(thrower);
        }
        // Visual-only: LightningBolt.spawnFire() is gated on !visualOnly, so this gives us the
        // bolt and its thunderclap with no burning blocks. It also disables the bolt's own
        // damage, so we apply that ourselves below — only to the entity we actually hit.
        bolt.setVisualOnly(true);
        serverLevel.addFreshEntity(bolt);

        target.hurt(this.damageSources().lightningBolt(), LIGHTNING_DAMAGE);

        if (this.random.nextFloat() < ELECTROCUTION_CHANCE) {
            this.electrocuteArea(serverLevel, target);
        }
    }

    /**
     * The splash: everything living within SPLASH_RADIUS of the struck entity — the target
     * included — gets Electrocution plus a short Slowness. The thrower is deliberately excluded.
     */
    private void electrocuteArea(ServerLevel level, Entity struck) {
        Entity owner = this.getOwner();

        for (LivingEntity victim : level.getEntitiesOfClass(LivingEntity.class,
                struck.getBoundingBox().inflate(SPLASH_RADIUS),
                candidate -> candidate.isAlive() && (candidate != owner || candidate == struck))) {

            victim.addEffect(new MobEffectInstance(ExoEffects.ELECTROCUTION, ELECTROCUTION_TICKS, 0),
                    owner);
            // (ambient, visible, showIcon) — visible=false suppresses Slowness's own swirl
            // particles so only our electrocution sparks show, while keeping its HUD icon.
            victim.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, SLOWNESS_TICKS, 1,
                    false, false, true), owner);
        }
    }

    // This model is mirrored along X relative to the hatchet's, so EAST/WEST come out swapped
    // with the default mapping. North/south are unaffected by a mirror, which is why only these
    // two needed correcting.
    @Override
    protected float getWallYaw(Direction face) {
        return switch (face) {
            case SOUTH -> 0.0F;
            case EAST -> 90.0F;
            case NORTH -> 180.0F;
            case WEST -> 270.0F;
            default -> 0.0F;
        };
    }

    // Pose values for the exorium axe model — its geometry differs from the hatchet's,
    // so these need tuning separately. Start from the hatchet's and adjust.
    @Override
    protected float getWallPitch() {
        return -45.0F;
    }

    @Override
    protected float getFloorPitch() {
        return 220.0F;
    }

    @Override
    protected float getCeilingPitch() {
        return 40.0F;
    }
}
