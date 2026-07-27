package net.johntdex.stratoblade.entity;

import net.johntdex.stratoblade.StratoBlade;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ExoEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, StratoBlade.MODID);

    public static final Supplier<EntityType<HatchetProjectileEntity>> HATCHET_PROJECTILE = ENTITIES.register("hatchet_projectile",
            () -> EntityType.Builder.<HatchetProjectileEntity>of(HatchetProjectileEntity::new, MobCategory.MISC)
            .sized(0.5f, 0.5f)
            .clientTrackingRange(4)
                    .updateInterval(5)
            .build("hatchet_projectile"));
    public static final Supplier<EntityType<ExoriumAxeProjectileEntity>> EXORIUM_AXE_PROJECTILE = ENTITIES.register("exorium_axe_projectile",
            () -> EntityType.Builder.<ExoriumAxeProjectileEntity>of(ExoriumAxeProjectileEntity::new, MobCategory.MISC)
            .sized(0.5f, 0.5f)
            .clientTrackingRange(4)
            .updateInterval(5)
            .build("exorium_axe_projectile"));

    public static void register(IEventBus bus) {
        ENTITIES.register(bus);
    }
}
