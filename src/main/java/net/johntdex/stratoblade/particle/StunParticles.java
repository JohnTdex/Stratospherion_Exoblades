package net.johntdex.stratoblade.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

public class StunParticles extends TextureSheetParticle {


    protected StunParticles(ClientLevel level, double x, double y, double z, SpriteSet spriteSet, double xSpeed, double ySpeed, double zSpeed) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);

        this.friction = 0.8F;

        this.lifetime = 40;
        this.setSpriteFromAge(spriteSet);

        this.rCol = 1.0f;
        this.gCol = 1.0f;
        this.bCol = 1.0f;

    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Override
        public @Nullable Particle createParticle(SimpleParticleType simpleParticleType, ClientLevel clientLevel, double pX, double pY, double pZ,
                                                 double pXSpeed, double pYSpeed, double pZSpeed) {
            return new StunParticles(clientLevel, pX, pY, pZ, spriteSet, pXSpeed, pYSpeed, pZSpeed);
        }
    }
}
