package net.johntdex.stratoblade.item;

import net.johntdex.stratoblade.StratoBlade;
import net.johntdex.stratoblade.block.StratoBlocks;
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
            .icon(() -> new ItemStack(ExoItems.STEEL_INGOT.get()))
            .title(Component.translatable("creativetab.stratoblade.stratoblade_tab"))
            .displayItems(((itemDisplayParameters, output) -> {
                output.accept(StratoBlocks.STEEL_BLOCK.get());
                output.accept(StratoBlocks.EXORIUM_BLOCK.get());
                output.accept(ExoItems.STEEL_INGOT.get());
                output.accept(ExoItems.IMPURE_STEEL.get());
                output.accept(ExoItems.CARBON_STEEL.get());
                output.accept(ExoItems.UNSTABLE_EXORIUM_INGOT.get());
                output.accept(ExoItems.EXORIUM_INGOT.get());
                output.accept(ExoItems.SILICON.get());
                output.accept(ExoItems.PRISMAL_EXCORE.get());
                output.accept(ExoItems.REINFORCED_STICK.get());
                output.accept(ExoItems.SWORD_MOLDER.get());
                output.accept(ExoItems.KATANA_MOLDER.get());
                output.accept(ExoItems.SCYTHE_MOLDER.get());
                output.accept(ExoItems.STEEL_SWORD.get());
                output.accept(ExoItems.STEEL_PICKAXE.get());
                output.accept(ExoItems.STEEL_AXE.get());
                output.accept(ExoItems.STEEL_SHOVEL.get());
                output.accept(ExoItems.STEEL_HOE.get());
                output.accept(ExoItems.CARBON_STEEL_SWORD.get());
                output.accept(ExoItems.CARBON_STEEL_KATANA.get());
                output.accept(ExoItems.CARBON_STEEL_SCYTHE.get());

            }))
            .build());

    public static void register(IEventBus bus) {
        CREATIVE_MODE_TABS.register(bus);
    }
}
