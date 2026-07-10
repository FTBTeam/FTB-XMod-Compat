package dev.ftb.mods.ftbxmodcompat.ftbquests.rei;

import dev.architectury.fluid.FluidStack;
import dev.ftb.mods.ftbxmodcompat.FTBXModCompat;
import dev.ftb.mods.ftbxmodcompat.ftbquests.recipemod_common.WrappedLootCrate;
import dev.ftb.mods.ftbxmodcompat.ftbquests.recipemod_common.WrappedQuest;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.entry.filtering.base.BasicFilteringRule;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ExclusionZones;
import me.shedaniel.rei.api.client.registry.screen.ExclusionZonesProvider;
import me.shedaniel.rei.api.client.view.ViewSearchBuilder;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.entry.type.EntryDefinition;
import me.shedaniel.rei.api.common.entry.type.EntryType;
import me.shedaniel.rei.api.common.entry.type.EntryTypeRegistry;
import me.shedaniel.rei.api.common.entry.type.VanillaEntryTypes;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;

import java.util.Collection;

public class FTBQuestsREIIntegration implements REIClientPlugin {
    private static BasicFilteringRule.MarkDirty cratesChanged;

    @Override
    public void registerCategories(CategoryRegistry registry) {
        if (FTBXModCompat.isFTBQuestsLoaded) {
            registry.add(new QuestDisplayCategory());
            registry.add(new LootCrateDisplayCategory());
        }
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        if (FTBXModCompat.isFTBQuestsLoaded) {
            registry.registerFiller(WrappedQuest.class, QuestDisplay::new);
            registry.registerFiller(WrappedLootCrate.class, LootCrateDisplay::new);

            registry.registerDisplayGenerator(REICategories.QUEST, QuestDisplayGenerator.INSTANCE);
            registry.registerDisplayGenerator(REICategories.LOOT_CRATE, LootCrateDisplayGenerator.INSTANCE);
        }
    }

    @Override
    public void registerBasicEntryFiltering(BasicFilteringRule<?> rule) {
        if (FTBXModCompat.isFTBQuestsLoaded) {
            cratesChanged = rule.show(LootCrateDisplayGenerator.INSTANCE::getCrateEntryStacks);
        }
    }

    public static void showRecipes(ItemStack stack) {
        show(VanillaEntryTypes.ITEM, stack);
    }

    public static void showRecipes(FluidStack fluid) {
        show(VanillaEntryTypes.FLUID, fluid);
    }

    private static <T> void show(EntryType<T> type, T object) {
        for (EntryDefinition<?> definition : EntryTypeRegistry.getInstance().values()) {
            if (definition.getType() == type) {
                ViewSearchBuilder.builder()
                        .addRecipesFor(EntryStack.of(type, object))
                        .open();
            }
        }
    }

    public static void onLootCratesChanged() {
        if (cratesChanged != null) {
            cratesChanged.markDirty();
        }
    }
}
