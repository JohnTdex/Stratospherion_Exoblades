package net.johntdex.stratoblade.item;

import net.johntdex.stratoblade.effect.ExoEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class BleedingWeaponItem extends SwordItem {
    private final float bleedChance;
    private final int bleedDuration;

    public BleedingWeaponItem(Tier tier, Properties properties, float bleedChance, int bleedDuration) {
        super(tier, properties);
        this.bleedChance = bleedChance;
        this.bleedDuration = bleedDuration;
    }

    @Override
    public boolean hurtEnemy(ItemStack item, LivingEntity target, LivingEntity attacker) {
        if (!attacker.level().isClientSide && attacker.getRandom().nextFloat() < this.bleedChance) {
            target.addEffect(new MobEffectInstance(ExoEffects.BLEEDING, this.bleedDuration, 0), attacker);
        }
        return super.hurtEnemy(item, target, attacker);
    }
}
