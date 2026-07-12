package net.johntdex.stratoblade.datagen;

import net.johntdex.stratoblade.StratoBlade;
import net.johntdex.stratoblade.item.ExoItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              @Nullable ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, CompletableFuture.completedFuture(TagsProvider.TagLookup.empty()),
                StratoBlade.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ItemTags.SWORDS).add(ExoItems.STEEL_SWORD.get());
        tag(ItemTags.PICKAXES).add(ExoItems.STEEL_PICKAXE.get());
        tag(ItemTags.AXES).add(ExoItems.STEEL_AXE.get());
        tag(ItemTags.SHOVELS).add(ExoItems.STEEL_SHOVEL.get());
        tag(ItemTags.HOES).add(ExoItems.STEEL_HOE.get());
        tag(ItemTags.SWORDS).add(ExoItems.CARBON_STEEL_SWORD.get());
        tag(ItemTags.SWORDS).add(ExoItems.CARBON_STEEL_KATANA.get());
        tag(ItemTags.SWORDS).add(ExoItems.CARBON_STEEL_SCYTHE.get());

    }
}
