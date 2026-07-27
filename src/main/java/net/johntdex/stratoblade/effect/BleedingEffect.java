package net.johntdex.stratoblade.effect;

import net.johntdex.stratoblade.particle.ExoParticles;
import net.johntdex.stratoblade.util.ExoDamageTypes;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public class BleedingEffect extends MobEffect {
    public BleedingEffect() {
        super(MobEffectCategory.HARMFUL, 0x8B0000); // dark red — now only tints the inventory entry
    }

    /**
     * Replaces the default tinted ENTITY_EFFECT swirl with our falling blood droplet.
     *
     * MobEffect also has a 3-arg constructor taking a ParticleOptions, but it can't be used here:
     * BuiltInRegistries creates MOB_EFFECT before PARTICLE_TYPE, so the particle holder is still
     * unbound while this effect is being constructed. Overriding defers the lookup to runtime.
     */
    @Override
    public ParticleOptions createParticleOptions(MobEffectInstance effect) {
        return ExoParticles.BLEEDING_PARTICLES.get();
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        entity.hurt(entity.damageSources().source(ExoDamageTypes.BLEEDING), 1.0f * (amplifier + 1));
        return true;
    }

@Override
public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
    return duration % 20 == 0; // once per second
}
}
