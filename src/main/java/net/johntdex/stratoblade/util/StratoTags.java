package net.johntdex.stratoblade.util;

import net.johntdex.stratoblade.StratoBlade;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class StratoTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_STEEL_TOOL = tag("needs_steel_tool");
        public static final TagKey<Block> INCORRECT_STEEL_TOOL = tag("incorrect_steel_tool");

        private static TagKey<Block> tag(String name) {
            return TagKey.create(Registries.BLOCK,
                    ResourceLocation.fromNamespaceAndPath(StratoBlade.MODID, name));
        }
    }
}

