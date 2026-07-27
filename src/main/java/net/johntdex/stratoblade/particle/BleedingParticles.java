package net.johntdex.stratoblade.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

public class BleedingParticles extends TextureSheetParticle {

    protected BleedingParticles(ClientLevel level, double x, double y, double z, SpriteSet spriteSet, double xSpeed, double ySpeed, double zSpeed) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);

        // gravity is a multiplier on downward acceleration (0 = floats). This is what makes the
        // droplets fall instead of orbiting the mob like the vanilla effect swirl.
        this.gravity = 0.7F;
        // hasPhysics stays true (the default) so drops stop on the ground instead of sinking through.
        this.friction = 0.96F;   // little air drag, so they fall naturally rather than stalling

        this.lifetime = 25;      // gone shortly after landing
        this.quadSize *= 0.6F;   // small droplets rather than blobs
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
            return new BleedingParticles(clientLevel, pX, pY, pZ, spriteSet, pXSpeed, pYSpeed, pZSpeed);
        }
    }
}
