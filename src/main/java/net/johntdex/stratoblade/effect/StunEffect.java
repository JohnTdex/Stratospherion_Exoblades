package net.johntdex.stratoblade.effect;

import net.johntdex.stratoblade.StratoBlade;
import net.johntdex.stratoblade.particle.ExoParticles;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;


/**
 * Mob-only hard stun: can't walk, can't path, deals no melee damage.
 * Players get a vanilla-effect approximation instead — see ExoEffects.applyConcussion.
 */
public class StunEffect extends MobEffect {

    protected StunEffect() {
        super(MobEffectCategory.HARMFUL, 0x9BB7D4);


        // Applied on start and removed automatically on expiry, so there's no cleanup code
        // and no risk of leaving a mob permanently crippled.
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.fromNamespaceAndPath(StratoBlade.MODID, "effect.stun.speed"),
                -1.0D, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE, ResourceLocation.fromNamespaceAndPath(StratoBlade.MODID, "effect.stun.damage"),
                -1.0D, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }

    @Override
    public ParticleOptions createParticleOptions(MobEffectInstance effect) {
        return ExoParticles.STUN_PARTICLES.get();
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        // Deliberately no hurt() — a stun immobilises, it doesn't damage.
        // Deliberately no setDeltaMovement() either: zeroing velocity would cancel knockback.
        // MOVEMENT_SPEED at 0 stops self-propelled movement while leaving external pushes intact.
        if (entity instanceof Mob mob){
            mob.getNavigation().stop();
            mob.setTarget(null);
            mob.setJumping(false);
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true; // base class returns false; mob AI re-paths every tick so we must too
    }
}
