package net.johntdex.stratoblade.entity;

import net.johntdex.stratoblade.item.ExoItems;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class HatchetProjectileEntity extends AbstractArrow {
    private float rotation;
    private boolean dealtDamage;
    public Vec2 groundedOffset;
    // true when stuck in a floor/ceiling (horizontal surface), false for the four walls.
    // The renderer needs this because the wall pullback offset doesn't apply when the hatchet is vertical.
    public boolean hitHorizontalSurface;
    // Which block face we stuck into. Floor and ceiling need opposite vertical corrections,
    // so the renderer needs to tell them apart — the boolean above isn't granular enough.
    public Direction hitFace;

    public HatchetProjectileEntity(EntityType<? extends HatchetProjectileEntity> entityType, Level level) {
        super(entityType, level);
    }

    // thrownStack is the ACTUAL hatchet that was thrown. AbstractArrow copies it into
    // pickupItemStack and hands that same stack back on pickup, so durability, enchantments
    // and a custom name all survive the round trip. Passing a fresh ItemStack here would
    // silently repair the hatchet every throw.
    public HatchetProjectileEntity(LivingEntity shooter, Level level, ItemStack thrownStack) {
        super(ExoEntities.HATCHET_PROJECTILE.get(), shooter, level, thrownStack, null);
        this.pickup = Pickup.ALLOWED; // default is DISALLOWED — without this it can't be picked up
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(ExoItems.CARBON_STEEL_HATCHET.get());
    }

    public float getRenderingRotation() {
        rotation += 0.5f;
        if(rotation >= 360) {
            rotation = 0;
        }
        return rotation;
    }


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

        if (entity.hurt(damageSource, 6.0F) && entity instanceof LivingEntity living) {
            this.doKnockback(living, damageSource);
            this.doPostHurtEffects(living);
        }

        // bounce back and fall instead of vanishing
        this.setDeltaMovement(this.getDeltaMovement().multiply(-0.01, -0.1, -0.01));
        this.playSound(SoundEvents.TRIDENT_HIT, 1.0F, 1.0F);
    }

    // Once it has hit something, stop it damaging further entities as it bounces and falls.
    @Override
    protected EntityHitResult findHitEntity(Vec3 startVec, Vec3 endVec) {
        return this.dealtDamage ? null : super.findHitEntity(startVec, endVec);
    }

    // The sound played when the hatchet sticks into a block. super.onHitBlock() plays this for us,
    // so overriding here replaces the default arrow "thunk" instead of layering a second sound on it.
    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.TRIDENT_HIT_GROUND;
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);

        hitFace = result.getDirection();

        // Walls: fixed yaw per face, shared -45 pitch.
        if(result.getDirection() == Direction.SOUTH) {
            groundedOffset = new Vec2(-45f,0f);
            hitHorizontalSurface = false;
        }
        if(result.getDirection() == Direction.NORTH) {
            groundedOffset = new Vec2(-45f, 180f);
            hitHorizontalSurface = false;
        }
        if(result.getDirection() == Direction.EAST) {
            groundedOffset = new Vec2(-45f,90f);
            hitHorizontalSurface = false;
        }
        if(result.getDirection() == Direction.WEST) {
            groundedOffset = new Vec2(-45F,270F);
            hitHorizontalSurface = false;
        }

        // Floor/ceiling: yaw follows the throw direction, pitches are 180 apart.
        if(result.getDirection() == Direction.DOWN) {
            groundedOffset = new Vec2(40f, this.getYRot() + -180);
            hitHorizontalSurface = true;
        }
        if(result.getDirection() == Direction.UP) {
            // +180: getYRot() points along the flight path, which aims the handle forward.
            // The blade needs to lead, so spin it around.
            groundedOffset = new Vec2(220f, this.getYRot() + 180f);
            hitHorizontalSurface = true;
        }
    }
}
