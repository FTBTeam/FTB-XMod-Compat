package dev.ftb.mods.ftbxmodcompat.ftbquests.jei;

import dev.ftb.mods.ftbquests.registry.ModItems;
import dev.ftb.mods.ftbxmodcompat.ftbquests.recipemod_common.WrappedLootCrate;
import dev.ftb.mods.ftbxmodcompat.ftbquests.recipemod_common.WrappedLootCrateCache;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.recipe.IFocus;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.advanced.IRecipeManagerPlugin;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public enum LootCrateRecipeManagerPlugin implements IRecipeManagerPlugin {
    INSTANCE;

    private final WrappedLootCrateCache cache = new WrappedLootCrateCache(
            crates -> {
                if (FTBQuestsJEIIntegration.runtime != null && !crates.isEmpty()) {
                    FTBQuestsJEIIntegration.runtime.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK, crates);
                }
            },
            crates -> {
                if (FTBQuestsJEIIntegration.runtime != null && !crates.isEmpty()) {
                    FTBQuestsJEIIntegration.runtime.getIngredientManager().addIngredientsAtRuntime(VanillaTypes.ITEM_STACK, crates);
                }
            }
    );

    @Override
    public <V> List<RecipeType<?>> getRecipeTypes(IFocus<V> focus) {
        if (focus.getTypedValue().getIngredient() instanceof ItemStack stack)
            if (focus.getRole() == RecipeIngredientRole.INPUT) {
                if (stack.getItem() == ModItems.LOOTCRATE.get()) {
                    return List.of(JEIRecipeTypes.LOOT_CRATE);
                }
            } else if (focus.getRole() == RecipeIngredientRole.OUTPUT) {
                if (!cache.findCratesWithOutput(stack).isEmpty()) {
                    return List.of(JEIRecipeTypes.LOOT_CRATE);
                }
            }

        return List.of();
    }

    @Override
    public <T, V> List<T> getRecipes(IRecipeCategory<T> recipeCategory, IFocus<V> focus) {
        if (recipeCategory instanceof LootCrateCategory && focus.getTypedValue().getIngredient() instanceof ItemStack stack) {
            if (stack.getItem() == ModItems.LOOTCRATE.get() && focus.getRole() == RecipeIngredientRole.CATALYST) {
                // safe to cast here since we've checked the category
                //noinspection unchecked
                return (List<T>) cache.getWrappedLootCrates();
            }
            return switch (focus.getRole()) {
                case INPUT -> //noinspection unchecked
                        (List<T>) cache.findCratesWithInput(stack);
                case OUTPUT -> //noinspection unchecked
                        (List<T>) cache.findCratesWithOutput(stack);
                default -> List.of();
            };
        }
        return List.of();
    }

    @Override
    public <T> List<T> getRecipes(IRecipeCategory<T> recipeCategory) {
        // safe to cast here since we've checked the category
        //noinspection unchecked
        return recipeCategory instanceof LootCrateCategory ? (List<T>) cache.getWrappedLootCrates() : List.of();
    }

    public List<WrappedLootCrate> getWrappedLootCrates() {
        return cache.getWrappedLootCrates();
    }

    public void refresh() {
        cache.refresh();
    }
}