package net.johntdex.stratoblade.item;

import net.johntdex.stratoblade.StratoBlade;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemLore;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class ExoItems {
    // Create a Deferred Register to hold Items which will all be registered under the "exoblade" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(StratoBlade.MODID);

    //These are for the items
    public static final DeferredItem<Item> STEEL_INGOT = ITEMS.register("steel_ingot", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> IMPURE_STEEL = ITEMS.register("impure_steel", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> REINFORCED_STICK = ITEMS.register("reinforced_stick", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> UNSTABLE_EXORIUM_INGOT = ITEMS.register("unstable_exorium_ingot", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> EXORIUM_INGOT = ITEMS.register("exorium_ingot", () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final DeferredItem<Item> CARBON_STEEL = ITEMS.register("carbon_steel", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<Item> SILICON = ITEMS.register("silicon", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PRISMAL_EXCORE = ITEMS.register("prismal_excore", () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));
    public static final DeferredItem<Item> SWORD_MOLDER = ITEMS.register("sword_molder", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> KATANA_MOLDER = ITEMS.register("katana_molder", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SCYTHE_MOLDER = ITEMS.register("scythe_molder", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DAGGER_MOLDER = ITEMS.register("dagger_molder", () -> new Item(new Item.Properties()));

    //This is for the Steel Tools
    public static final DeferredItem<SwordItem> STEEL_SWORD = ITEMS.register("steel_sword",
            () -> new SwordItem(StratoToolTiers.STEEL_SWORD, new Item.Properties()
                    .attributes(SwordItem.createAttributes(StratoToolTiers.STEEL_SWORD,
                            3f, -1.8f))));
    public static final DeferredItem<PickaxeItem> STEEL_PICKAXE = ITEMS.register("steel_pickaxe",
            () -> new PickaxeItem(StratoToolTiers.STEEL, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(StratoToolTiers.STEEL,
                            1.5f, -2.8f))));
    public static final DeferredItem<AxeItem> STEEL_AXE = ITEMS.register("steel_axe",
            () -> new AxeItem(StratoToolTiers.STEEL, new Item.Properties()
                    .attributes(AxeItem.createAttributes(StratoToolTiers.STEEL,
                            5.5f, -2.4f))));
    public static final DeferredItem<ShovelItem> STEEL_SHOVEL = ITEMS.register("steel_shovel",
            () -> new ShovelItem(StratoToolTiers.STEEL, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(StratoToolTiers.STEEL,
                            1.5f, -2.8f))));
    public static final DeferredItem<HoeItem> STEEL_HOE = ITEMS.register("steel_hoe",
            () -> new HoeItem(StratoToolTiers.STEEL, new Item.Properties()
                    .attributes(HoeItem.createAttributes(StratoToolTiers.STEEL,
                            -1.0f, -1.0f))));

    //Carbon Steel
    public static final DeferredItem<SwordItem> CARBON_STEEL_SWORD = ITEMS.register("carbon_steel_sword",
            () -> new SwordItem(StratoToolTiers.CARBON_STEEL, new Item.Properties()
                    .attributes(SwordItem.createAttributes(StratoToolTiers.CARBON_STEEL,
                            4f, -1.8f))
                    .rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<SwordItem> CARBON_STEEL_KATANA = ITEMS.register("carbon_steel_katana",
            () -> new SwordItem(StratoToolTiers.CARBON_STEEL, new Item.Properties()
                    .attributes(SwordItem.createAttributes(StratoToolTiers.CARBON_STEEL,
                            3.6f, -1.4f))
                    .rarity(Rarity.UNCOMMON)));
    public static final DeferredItem<SwordItem> CARBON_STEEL_SCYTHE = ITEMS.register("carbon_steel_scythe",
            () -> new SwordItem(StratoToolTiers.CARBON_STEEL, new Item.Properties()
                    .attributes(SwordItem.createAttributes(StratoToolTiers.CARBON_STEEL,
                            4.5f, -2.2f))
                    .rarity(Rarity.UNCOMMON)
                    .component(DataComponents.LORE, new ItemLore(List.of(Component.translatable("tooltip.exoblade.carbon_steel_scythe"))))));
    public static final DeferredItem<SwordItem> CARBON_STEEL_DAGGER = ITEMS.register("carbon_steel_dagger",
            ()  -> new BleedingWeaponItem(StratoToolTiers.CARBON_STEEL, new Item.Properties()
                    .attributes(SwordItem.createAttributes(StratoToolTiers.CARBON_STEEL,
                            0.5f, -1.0f))
                    .rarity(Rarity.UNCOMMON)
                    .component(DataComponents.LORE, new ItemLore(List.of(Component.translatable("tooltip.exoblade.carbon_steel_dagger")))),
                    0.30f, 100));



    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }



    // Creates a new food item with the id "exoblade:example_item", nutrition 1 and saturation 2
    public static final DeferredItem<Item> EXAMPLE_ITEM = ExoItems.ITEMS.registerSimpleItem("example_item", new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEdible().nutrition(1).saturationModifier(2f).build()));

    // Add new custom tools here, e.g.:
    // public static final DeferredItem<Item> EXO_BLADE = ITEMS.registerSimpleItem("exo_blade", new Item.Properties());
}
