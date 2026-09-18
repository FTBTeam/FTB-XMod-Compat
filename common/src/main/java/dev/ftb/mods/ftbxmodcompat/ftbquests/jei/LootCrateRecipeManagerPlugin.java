package dev.ftb.mods.ftbxmodcompat.ftbquests.jei;

import dev.ftb.mods.ftbxmodcompat.ftbquests.recipemod_common.WrappedLootCrate;
import dev.ftb.mods.ftbxmodcompat.ftbquests.recipemod_common.WrappedLootCrateCache;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.ingredients.ITypedIngredient;
import mezz.jei.api.recipe.advanced.ISimpleRecipeManagerPlugin;
import net.minecraft.client.Minecraft;

import java.util.List;

public enum LootCrateRecipeManagerPlugin implements ISimpleRecipeManagerPlugin<WrappedLootCrate> {
    INSTANCE;

    private final WrappedLootCrateCache cache = new WrappedLootCrateCache(
            crates -> {
                var runtime = FTBQuestsJEIIntegration.runtime;
                if (runtime != null && !crates.isEmpty()) {
                    Minecraft.getInstance().tell(() -> {
                        if (runtime == FTBQuestsJEIIntegration.runtime) {
                            runtime.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK, crates);
                        }
                    });
                }
            },
            crates -> {
                var runtime = FTBQuestsJEIIntegration.runtime;
                if (runtime != null && !crates.isEmpty()) {
                    Minecraft.getInstance().tell(() -> {
                        if (runtime == FTBQuestsJEIIntegration.runtime) {
                            runtime.getIngredientManager().addIngredientsAtRuntime(VanillaTypes.ITEM_STACK, crates);
                        }
                    });
                }
            }
    );

    @Override
    public boolean isHandledInput(ITypedIngredient<?> input) {
        return !getRecipesForInput(input).isEmpty();
    }

    @Override
    public boolean isHandledOutput(ITypedIngredient<?> output) {
        return !getRecipesForOutput(output).isEmpty();
    }

    @Override
    public List<WrappedLootCrate> getRecipesForInput(ITypedIngredient<?> input) {
        return input.getItemStack().map(cache::findCratesWithInput).orElseGet(List::of);
    }

    @Override
    public List<WrappedLootCrate> getRecipesForOutput(ITypedIngredient<?> output) {
        return output.getItemStack().map(cache::findCratesWithOutput).orElseGet(List::of);
    }

    @Override
    public List<WrappedLootCrate> getAllRecipes() {
        return cache.getWrappedLootCrates();
    }

    public List<WrappedLootCrate> getWrappedLootCrates() {
        return cache.getWrappedLootCrates();
    }

    public void refresh() {
        cache.refresh();
    }
}