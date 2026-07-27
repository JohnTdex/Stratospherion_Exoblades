package net.johntdex.stratoblade.item.custom;

import net.johntdex.stratoblade.sound.ExoSounds;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

public class ExoriumSwordItem extends SwordItem {
    private static final int BUFF_DURATION = 200;   // 10s x 20 ticks
    private static final int COOLDOWN = 400;        // 20s

    public ExoriumSwordItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        player.getCooldowns().addCooldown(this, COOLDOWN);   // both sides, like vanilla

        if (!level.isClientSide) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, BUFF_DURATION, 0));
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, BUFF_DURATION, 0));
            stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
        }

        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                ExoSounds.EXORIUM_SWORD_USE.get(), SoundSource.PLAYERS, 1.0F, 1.4F);

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }
}