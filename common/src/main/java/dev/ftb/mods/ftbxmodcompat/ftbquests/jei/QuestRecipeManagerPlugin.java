package dev.ftb.mods.ftbxmodcompat.ftbquests.jei;

import dev.ftb.mods.ftbquests.item.FTBQuestsItems;
import dev.ftb.mods.ftbxmodcompat.ftbquests.recipemod_common.WrappedQuestCache;
import mezz.jei.api.recipe.IFocus;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.advanced.IRecipeManagerPlugin;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public enum QuestRecipeManagerPlugin implements IRecipeManagerPlugin {
    INSTANCE;

    private final WrappedQuestCache cache = new WrappedQuestCache();

    public void refresh() {
        cache.clear();
    }

    @Override
    public <T, V> List<T> getRecipes(IRecipeCategory<T> recipeCategory, IFocus<V> focus) {
        if (recipeCategory instanceof QuestCategory && focus.getTypedValue().getIngredient() instanceof ItemStack stack) {
            // (List<T>) casts should be safe since we've verified the category
            if (stack.getItem() == FTBQuestsItems.BOOK.get() && focus.getRole() == RecipeIngredientRole.CATALYST) {
                //noinspection unchecked
                return (List<T>) cache.getCachedItems();
            }
            return switch (focus.getRole()) {
                case INPUT -> //noinspection unchecked
                        (List<T>) cache.findQuestsWithInput(stack);
                case OUTPUT -> //noinspection unchecked
                        (List<T>) cache.findQuestsWithOutput(stack);
                default -> List.of();
            };
        } else {
            return List.of();
        }
    }

    @Override
    public <T> List<T> getRecipes(IRecipeCategory<T> recipeCategory) {
        // safe to cast since we verified the category already
        //noinspection unchecked
        return recipeCategory instanceof QuestCategory ? (List<T>) cache.getCachedItems() : List.of();
    }

    @Override
    public <V> List<RecipeType<?>> getRecipeTypes(IFocus<V> focus) {
        if (focus.getTypedValue().getIngredient() instanceof ItemStack stack) {
            if (focus.getRole() == RecipeIngredientRole.INPUT && (stack.getItem() == FTBQuestsItems.BOOK.get() || !cache.findQuestsWithInput(stack).isEmpty())
                    || focus.getRole() == RecipeIngredientRole.OUTPUT && !cache.findQuestsWithOutput(stack).isEmpty()) {
                return List.of(JEIRecipeTypes.QUEST);
            }
        }

        return List.of();

    }
}