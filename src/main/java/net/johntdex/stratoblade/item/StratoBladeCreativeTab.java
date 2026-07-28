package net.johntdex.stratoblade.item;

import net.johntdex.stratoblade.StratoBlade;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class StratoBladeCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, StratoBlade.MODID);


    public static final Supplier<CreativeModeTab> EXO_TAB = CREATIVE_MODE_TABS.register("stratoblade_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ExoItems.EXORIUM_SWORD.get()))
            .title(Component.translatable("creativetab.stratoblade.stratoblade_tab"))
            .displayItems(((itemDisplayParameters, output) -> {

                //output.accept(ExoItems.SILICON.get());
                //output.accept(ExoItems.PRISMAL_EXCORE.get());
                output.accept(ExoItems.REINFORCED_STICK.get());
                output.accept(ExoItems.CARBON_HANDLE.get());
                output.accept(ExoItems.SWORD_MOLDER.get());
                output.accept(ExoItems.KATANA_MOLDER.get());
                output.accept(ExoItems.SCYTHE_MOLDER.get());
                output.accept(ExoItems.DAGGER_MOLDER.get());
                output.accept(ExoItems.MACHETE_MOLDER.get());
                output.accept(ExoItems.HATCHET_MOLDER.get());
                output.accept(ExoItems.SPEAR_MOLDER.get());
                output.accept(ExoItems.EXORIUM_SWORD_MOLDER.get());
                output.accept(ExoItems.EXORIUM_KATANA_MOLDER.get());
                output.accept(ExoItems.EXORIUM_DAGGER_MOLDER.get());
                output.accept(ExoItems.EXORIUM_MACHETE_MOLDER.get());
                output.accept(ExoItems.EXORIUM_SPEAR_MOLDER.get());
                output.accept(ExoItems.EXORIUM_SCYTHE_MOLDER.get());
                output.accept(ExoItems.EXORIUM_HAMMER_MOLDER.get());
                output.accept(ExoItems.EXORIUM_AXE_MOLDER.get());
                output.accept(ExoItems.STEEL_SWORD.get());
                output.accept(ExoItems.STEEL_PICKAXE.get());
                output.accept(ExoItems.STEEL_AXE.get());
                output.accept(ExoItems.STEEL_SHOVEL.get());
                output.accept(ExoItems.STEEL_HOE.get());
                output.accept(ExoItems.CARBON_STEEL_SWORD.get());
                output.accept(ExoItems.CARBON_STEEL_MACHETE.get());
                output.accept(ExoItems.CARBON_STEEL_DAGGER.get());
                output.accept(ExoItems.CARBON_STEEL_KATANA.get());
                output.accept(ExoItems.CARBON_STEEL_SCYTHE.get());
                output.accept(ExoItems.CARBON_STEEL_HATCHET.get());
                output.accept(ExoItems.CARBON_STEEL_SPEAR.get());
                output.accept(ExoItems.EXORIUM_SWORD.get());
                output.accept(ExoItems.EXORIUM_DAGGER.get());
                output.accept(ExoItems.EXORIUM_AXE.get());
                output.accept(ExoItems.EXORIUM_SPEAR.get());
                output.accept(ExoItems.EXORIUM_HAMMER.get());
                output.accept(ExoItems.EXORIUM_MACHETE.get());
                output.accept(ExoItems.EXORIUM_KATANA.get());
                output.accept(ExoItems.EXORIUM_SCYTHE.get());
                output.accept(ExoItems.EXORIUM_HAMMER.get());

            }))
            .build());

    public static void register(IEventBus bus) {
        CREATIVE_MODE_TABS.register(bus);
    }
}
