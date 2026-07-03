package net.johntdex.stratoblade.item;

import net.johntdex.stratoblade.StratoBlade;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.SwordItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ExoItems {
    // Create a Deferred Register to hold Items which will all be registered under the "exoblade" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(StratoBlade.MODID);

    //These are for the items
    public static final DeferredItem<Item> STEEL_INGOT = ITEMS.register("steel_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> IMPURE_STEEL = ITEMS.register("impure_steel", () -> new Item(new Item.Properties()));

    //This is for the tools
    public static final DeferredItem<SwordItem> STEEL_SWORD = ITEMS.register("steel_sword",
            () -> new SwordItem(StratoToolTiers.STEEL, new Item.Properties()
                    .attributes(SwordItem.createAttributes(StratoToolTiers.STEEL,
                            3f, -1.8f))));
    public static final DeferredItem<PickaxeItem> STEEL_PICKAXE = ITEMS.register("steel_pickaxe",
            () -> new PickaxeItem(StratoToolTiers.STEEL, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(StratoToolTiers.STEEL,
                            1.5f, -2.8f))));
    public static final DeferredItem<AxeItem> STEEL_AXE = ITEMS.register("steel_axe",
            () -> new AxeItem(StratoToolTiers.STEEL, new Item.Properties()
                    .attributes(AxeItem.createAttributes(StratoToolTiers.STEEL,
                            5.5f, -2.4f))));




    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }



    // Creates a new food item with the id "exoblade:example_item", nutrition 1 and saturation 2
    public static final DeferredItem<Item> EXAMPLE_ITEM = ExoItems.ITEMS.registerSimpleItem("example_item", new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEdible().nutrition(1).saturationModifier(2f).build()));

    // Add new custom tools here, e.g.:
    // public static final DeferredItem<Item> EXO_BLADE = ITEMS.registerSimpleItem("exo_blade", new Item.Properties());
}
