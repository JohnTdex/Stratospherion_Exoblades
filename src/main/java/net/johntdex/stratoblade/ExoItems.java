package net.johntdex.stratoblade;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ExoItems {
    // Create a Deferred Register to hold Items which will all be registered under the "exoblade" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(StratoBlade.MODID);

    // Creates a new BlockItem with the id "exoblade:example_block", combining the namespace and path
    public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("example_block", StratoBlade.EXAMPLE_BLOCK);

    // Creates a new food item with the id "exoblade:example_item", nutrition 1 and saturation 2
    public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.registerSimpleItem("example_item", new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEdible().nutrition(1).saturationModifier(2f).build()));

    // Add new custom tools here, e.g.:
    // public static final DeferredItem<Item> EXO_BLADE = ITEMS.registerSimpleItem("exo_blade", new Item.Properties());
}
