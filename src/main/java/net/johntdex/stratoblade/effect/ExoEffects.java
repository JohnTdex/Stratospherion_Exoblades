package net.johntdex.stratoblade.effect;

import net.johntdex.stratoblade.StratoBlade;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ExoEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, StratoBlade.MODID);

    public static final Holder<MobEffect> BLEEDING = EFFECTS.register("bleeding", BleedingEffect::new);

    public static void register(IEventBus bus) {
        EFFECTS.register(bus);
    }
}

