package dev.ftb.mods.ftbxmodcompat.ftbquests.jei;

import dev.ftb.mods.ftbxmodcompat.ftbquests.recipemod_common.WrappedQuest;
import dev.ftb.mods.ftbxmodcompat.ftbquests.recipemod_common.WrappedQuestCache;
import mezz.jei.api.ingredients.ITypedIngredient;
import mezz.jei.api.recipe.advanced.ISimpleRecipeManagerPlugin;

import java.util.List;

public enum QuestRecipeManagerPlugin implements ISimpleRecipeManagerPlugin<WrappedQuest> {
    INSTANCE;

    private final WrappedQuestCache cache = new WrappedQuestCache();

    public void refresh() {
        cache.clear();
    }

    @Override
    public boolean isHandledInput(ITypedIngredient<?> input) {
        return !getRecipesForInput(input).isEmpty();
    }

    @Override
    public boolean isHandledOutput(ITypedIngredient<?> output) {
        return !getRecipesForOutput(output).isEmpty();
    }

    @Override
    public List<WrappedQuest> getRecipesForInput(ITypedIngredient<?> input) {
        return input.getItemStack().map(cache::findQuestsWithInput).orElseGet(List::of);
    }

    @Override
    public List<WrappedQuest> getRecipesForOutput(ITypedIngredient<?> output) {
        return output.getItemStack().map(cache::findQuestsWithOutput).orElseGet(List::of);
    }

    @Override
    public List<WrappedQuest> getAllRecipes() {
        return cache.getCachedItems();
    }
}