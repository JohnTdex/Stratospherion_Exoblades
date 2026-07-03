package net.johntdex.stratoblade.item;

import net.johntdex.stratoblade.util.StratoTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class StratoToolTiers {
    public static final Tier STEEL = new SimpleTier(
            StratoTags.Blocks.INCORRECT_STEEL_TOOL, // blocks steel CAN'T mine
            1024,   // uses (durability)
            7.0f,   // speed (mining)
            2.5f,   // attackDamageBonus
            28,     // enchantmentValue
            () -> Ingredient.of(ExoItems.STEEL_INGOT.get())
    );
}