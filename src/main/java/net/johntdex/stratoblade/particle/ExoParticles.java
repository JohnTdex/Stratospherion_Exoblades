package net.johntdex.stratoblade.particle;

import net.johntdex.stratoblade.StratoBlade;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ExoParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, StratoBlade.MODID);

    public static final Supplier<SimpleParticleType> ELECTROCUTION_PARTICLES = PARTICLE_TYPES.register("electrocution_particles",
            () -> new SimpleParticleType(true));

    public static final Supplier<SimpleParticleType> BLEEDING_PARTICLES = PARTICLE_TYPES.register("bleeding_particles",
            () -> new SimpleParticleType(true));

    public static final Supplier<SimpleParticleType> STUN_PARTICLES = PARTICLE_TYPES.register("stun_particles",
            () -> new SimpleParticleType(true));

    public static void register(IEventBus bus) {
        PARTICLE_TYPES.register(bus);
    }
}
