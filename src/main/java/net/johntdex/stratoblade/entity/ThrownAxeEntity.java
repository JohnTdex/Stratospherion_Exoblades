package net.johntdex.stratoblade.entity;

import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

/**
 * Shared behaviour for every throwable axe: trident-style hit (damage, knockback, bounce, no
 * despawn), pickup that preserves the thrown stack, and the six-direction resting pose used by
 * the renderer.
 *
 * Subclasses supply their own entity type, pickup item, damage, and the three pitch values their
 * model needs. Everything else lives here so a fix or a tweak lands on all axes at once.
 */
public abstract class ThrownAxeEntity extends AbstractArrow {
    private boolean dealtDamage;

    /** Resting pose while stuck in a block: x = pitch, y = yaw. Read by the renderer. */
    public Vec2 groundedOffset;
    /** True for floor/ceiling hits. The renderer offsets those differently to the four walls. */
    public boolean hitHorizontalSurface;
    /** Which block face we stuck into — floor and ceiling need opposite corrections. */
    public Direction hitFace;

    protected ThrownAxeEntity(EntityType<? extends ThrownAxeEntity> entityType, Level level) {
        super(entityType, level);
    }

    /**
     * thrownStack is the ACTUAL axe that was thrown. AbstractArrow copies it into pickupItemStack
     * and hands that same stack back on pickup, so durability, enchantments and a custom name all
     * survive the round trip. Passing a fresh ItemStack here would silently repair it every throw.
     */
    protected ThrownAxeEntity(EntityType<? extends ThrownAxeEntity> entityType, LivingEntity shooter,
                              Level level, ItemStack thrownStack) {
        super(entityType, shooter, level, thrownStack, null);
        this.pickup = shooter instanceof Player player && player.hasInfiniteMaterials()
                ? Pickup.CREATIVE_ONLY
                : Pickup.ALLOWED;
        // default is DISALLOWED — without this it can't be picked up
    }

    // ---- per-axe tuning knobs -------------------------------------------------------------

    /** Damage dealt on a direct hit. */
    protected float getImpactDamage() {
        return 6.0F;
    }

    /** Pitch used for all four walls. Yaw is derived from the face, 90 degrees apart. */
    protected float getWallPitch() {
        return -45.0F;
    }

    /** Pitch for a floor hit (the block's UP face). */
    protected float getFloorPitch() {
        return 220.0F;
    }

    /** Pitch for a ceiling hit (the block's DOWN face). Normally the floor value +/- 180. */
    protected float getCeilingPitch() {
        return 40.0F;
    }

    /**
     * Yaw for each wall face. Override to swap EAST/WEST if the model is mirrored along X —
     * a mirrored model flips left/right while leaving north/south correct.
     */
    protected float getWallYaw(Direction face) {
        return switch (face) {
            case SOUTH -> 0.0F;
            case WEST -> 90.0F;
            case NORTH -> 180.0F;
            case EAST -> 270.0F;
            default -> 0.0F;
        };
    }

    /** Sound played when the axe strikes an entity. */
    protected SoundEvent getEntityHitSound() {
        return SoundEvents.TRIDENT_HIT;
    }

    /** Extra behaviour on a direct hit — lightning, effects, and so on. No-op by default. */
    protected void applyOnHitEffects(Entity target, DamageSource damageSource) {
    }

    // ---- shared behaviour ---------------------------------------------------------------

    public boolean isGrounded() {
        return inGround;
    }

    // Deliberately does NOT call super.onHitEntity() — AbstractArrow's version applies its own
    // arrow damage and then discards the projectile. ThrownTrident replaces it for the same reason.
    @Override
    protected void onHitEntity(EntityHitResult result) {
        Entity entity = result.getEntity();
        Entity owner = this.getOwner();
        DamageSource damageSource = this.damageSources().thrown(this, owner == null ? this : owner);

        this.dealtDamage = true;

        if (entity.hurt(damageSource, this.getImpactDamage()) && entity instanceof LivingEntity living) {
            this.doKnockback(living, damageSource);
            this.doPostHurtEffects(living);
        }

        this.applyOnHitEffects(entity, damageSource);

        // bounce back and fall instead of vanishing
        this.setDeltaMovement(this.getDeltaMovement().multiply(-0.01, -0.1, -0.01));
        this.playSound(this.getEntityHitSound(), 1.0F, 1.0F);
    }

    // Once it has hit something, stop it damaging further entities as it bounces and falls.
    @Override
    protected EntityHitResult findHitEntity(Vec3 startVec, Vec3 endVec) {
        return this.dealtDamage ? null : super.findHitEntity(startVec, endVec);
    }

    // Sound when the axe sticks into a block. super.onHitBlock() plays this for us, so overriding
    // replaces the default arrow "thunk" instead of layering a second sound on top of it.
    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.TRIDENT_HIT_GROUND;
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        this.setSoundEvent(this.getDefaultHitGroundSoundEvent());

        hitFace = result.getDirection();
        hitHorizontalSurface = hitFace == Direction.UP || hitFace == Direction.DOWN;

        // NOTE: getDirection() is the FACE HIT, not the travel direction — throwing east strikes
        // the WEST face, throwing down at the floor strikes its UP face.
        switch (hitFace) {
            // Walls: one shared pitch, yaw per face from getWallYaw().
            case SOUTH, WEST, NORTH, EAST ->
                    groundedOffset = new Vec2(getWallPitch(), getWallYaw(hitFace));
            // Floor/ceiling: yaw follows the throw so it lands pointing the way it flew.
            // +180 because getYRot() points along the flight path, which aims the handle forward.
            case UP    -> groundedOffset = new Vec2(getFloorPitch(), this.getYRot() + 180.0F);
            case DOWN  -> groundedOffset = new Vec2(getCeilingPitch(), this.getYRot() + 180.0F);
        }
    }
}
