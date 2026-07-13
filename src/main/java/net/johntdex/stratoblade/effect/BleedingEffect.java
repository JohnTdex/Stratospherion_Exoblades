package net.johntdex.stratoblade.effect;

import net.johntdex.stratoblade.util.ExoDamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class BleedingEffect extends MobEffect {
    public BleedingEffect() {
        super(MobEffectCategory.HARMFUL, 0x8B0000); // dark red particle color
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
