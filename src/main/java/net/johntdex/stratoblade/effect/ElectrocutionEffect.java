package net.johntdex.stratoblade.effect;

import net.johntdex.stratoblade.particle.ExoParticles;
import net.johntdex.stratoblade.util.ExoDamageTypes;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public class ElectrocutionEffect extends MobEffect {
    public ElectrocutionEffect() {
        super(MobEffectCategory.HARMFUL, 0x3B9EE7); // electric blue particle color
    }

    /**
     * Replaces the default tinted ENTITY_EFFECT swirl with our own particle.
     *
     * MobEffect also has a 3-arg constructor taking a ParticleOptions, but it can't be used here:
     * BuiltInRegistries creates MOB_EFFECT before PARTICLE_TYPE, so the particle holder is still
     * unbound while this effect is being constructed. Overriding here defers the lookup to
     * runtime, long after every registry has filled.
     */
    @Override
    public ParticleOptions createParticleOptions(MobEffectInstance effect) {
        return ExoParticles.ELECTROCUTION_PARTICLES.get();
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        // 1 heart per hit, scaling with the level (amplifier is 0-indexed).
        entity.hurt(entity.damageSources().source(ExoDamageTypes.ELECTROCUTION), 2.0f * (amplifier + 1));
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 10 == 0; // twice per second
    }
}
