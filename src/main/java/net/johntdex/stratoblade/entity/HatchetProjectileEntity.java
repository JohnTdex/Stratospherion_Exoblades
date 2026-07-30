package net.johntdex.stratoblade.entity;

import net.johntdex.stratoblade.item.ExoItems;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * The carbon steel hatchet in flight. All the sticking/pickup/bounce behaviour lives in
 * {@link ThrownAxeEntity} — this only supplies what is specific to the hatchet.
 */
public class HatchetProjectileEntity extends ThrownAxeEntity {

    public HatchetProjectileEntity(EntityType<? extends HatchetProjectileEntity> entityType, Level level) {
        super(entityType, level);
    }

    public HatchetProjectileEntity(LivingEntity shooter, Level level, ItemStack thrownStack) {
        super(ExoEntities.HATCHET_PROJECTILE.get(), shooter, level, thrownStack);
    }
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

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(ExoItems.CARBON_STEEL_HATCHET.get());
    }

    // Pose values tuned against the hatchet model's geometry.
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
