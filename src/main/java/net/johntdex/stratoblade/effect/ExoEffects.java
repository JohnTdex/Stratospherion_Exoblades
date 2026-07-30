package net.johntdex.stratoblade.effect;

import net.johntdex.stratoblade.StratoBlade;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.Nullable;

public class ExoEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, StratoBlade.MODID);

    public static final Holder<MobEffect> BLEEDING = EFFECTS.register("bleeding", BleedingEffect::new);
    public static final Holder<MobEffect> ELECTROCUTION = EFFECTS.register("electrocution", ElectrocutionEffect::new);
    public static final Holder<MobEffect> STUNNING = EFFECTS.register("stun", StunEffect::new);


    /**
     * A concussion means different things per target: mobs get the full STUNNING lockdown,
     * players get a vanilla-effect approximation because player movement is client-authoritative
     * and a hard stun simply doesn't apply to them.
     */
    public static void applyConcussion(LivingEntity entity, int durationTicks, @Nullable Entity source) {
        if (entity instanceof Player) {
            entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, durationTicks, 6), source);
            entity.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, durationTicks, 4), source);
            entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, durationTicks, 1), source);
        }   else {
            entity.addEffect(new MobEffectInstance(STUNNING, durationTicks, 0), source);
        }
    }

    public static void register(IEventBus bus) {
        EFFECTS.register(bus);
    }
}

