package net.johntdex.stratoblade.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class ElectrocutionEffect extends MobEffect {
    public ElectrocutionEffect() {
        super(MobEffectCategory.HARMFUL, 0x3B9EE7); // electric blue particle color
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        // 1 heart per hit, scaling with the level (amplifier is 0-indexed).
        entity.hurt(entity.damageSources().lightningBolt(), 2.0f * (amplifier + 1));
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 10 == 0; // twice per second
    }
}
