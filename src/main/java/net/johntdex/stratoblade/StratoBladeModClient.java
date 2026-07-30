package net.johntdex.stratoblade;

import net.johntdex.stratoblade.client.model.ExoriumAxeModel;
import net.johntdex.stratoblade.client.model.HatchetModel;
import net.johntdex.stratoblade.client.renderer.ExoriumAxeProjectileRenderer;
import net.johntdex.stratoblade.client.renderer.HatchetProjectileRenderer;
import net.johntdex.stratoblade.entity.ExoEntities;
import net.johntdex.stratoblade.particle.BleedingParticles;
import net.johntdex.stratoblade.particle.ElectrocutionParticles;
import net.johntdex.stratoblade.particle.ExoParticles;
import net.johntdex.stratoblade.particle.StunParticles;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = StratoBlade.MODID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = StratoBlade.MODID, value = Dist.CLIENT)
public class StratoBladeModClient {
    public StratoBladeModClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        StratoBlade.LOGGER.info("HELLO FROM CLIENT SETUP");
        StratoBlade.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }
    @SubscribeEvent
    static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(HatchetModel.LAYER_LOCATION, HatchetModel::createBodyLayer);
        event.registerLayerDefinition(ExoriumAxeModel.LAYER_LOCATION, ExoriumAxeModel::createBodyLayer);
    }

    @SubscribeEvent
    static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ExoParticles.ELECTROCUTION_PARTICLES.get(), ElectrocutionParticles.Provider::new);
        event.registerSpriteSet(ExoParticles.BLEEDING_PARTICLES.get(), BleedingParticles.Provider::new);
        event.registerSpriteSet(ExoParticles.STUN_PARTICLES.get(), StunParticles.Provider::new);
    }

    @SubscribeEvent
    static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ExoEntities.HATCHET_PROJECTILE.get(), HatchetProjectileRenderer::new);
        event.registerEntityRenderer(ExoEntities.EXORIUM_AXE_PROJECTILE.get(), ExoriumAxeProjectileRenderer::new);
    }
}
