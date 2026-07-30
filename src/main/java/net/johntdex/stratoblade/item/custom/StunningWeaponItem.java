package net.johntdex.stratoblade.item.custom;

import net.johntdex.stratoblade.effect.ExoEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class StunningWeaponItem extends SwordItem {
    private final float stunningChance;
    private final int stunningDuration;
    private final float shieldDisableChance;


    public StunningWeaponItem(Tier tier, Properties properties, float stunningChance, int stunningDuration, float shieldDisableChance) {
        super(tier, properties);
        this.stunningChance = stunningChance;
        this.stunningDuration = stunningDuration;
        this.shieldDisableChance = shieldDisableChance;
    }

    @Override
    public boolean hurtEnemy(ItemStack item, LivingEntity target, LivingEntity attacker) {
        if (!attacker.level().isClientSide && attacker.getRandom().nextFloat() < this.stunningChance) {
            ExoEffects.applyConcussion(target, this.stunningDuration , attacker);
        }
        return super.hurtEnemy(item, target, attacker);



    }
    // NeoForge asks the weapon instead of checking `instanceof AxeItem`, so the roll lives here.
    // Only `attacker` is trustworthy — vanilla passes the attacker for `entity` too.
    @Override
    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return attacker.getRandom().nextFloat() < this.shieldDisableChance;
    }
}
