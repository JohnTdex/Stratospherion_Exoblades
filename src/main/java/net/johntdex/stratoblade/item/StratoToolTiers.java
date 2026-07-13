package net.johntdex.stratoblade.item;

import net.johntdex.stratoblade.util.StratoTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class StratoToolTiers {
    private static Tier steel(int uses) {
        return new SimpleTier(
                StratoTags.Blocks.INCORRECT_STEEL_TOOL,
                uses,
                7.0f,   // speed
                2.5f,   // attackDamageBonus
                28,     // enchantmentValue
                () -> Ingredient.of(ExoItems.STEEL_INGOT.get()));
    }
    public static final Tier STEEL = steel(1024);

    //Weapons
    public static final Tier STEEL_SWORD = steel(800);

    public static final Tier CARBON_STEEL = new SimpleTier(
            StratoTags.Blocks.INCORRECT_STEEL_TOOL,
            1200,
            7.0f,
            2.7f,
            28,
            () -> Ingredient.of(ExoItems.CARBON_STEEL.get()));
}