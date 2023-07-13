package dev.ftb.mods.ftbxmodcompat.ftbquests.recipemod_common;

import dev.ftb.mods.ftbquests.integration.RecipeModHelper;
import dev.ftb.mods.ftbquests.quest.QuestObjectBase;

public abstract class BaseRecipeHelper implements RecipeModHelper {
    @Override
    public void refreshAll(Components component) {
        switch (component) {
            case QUESTS -> refreshQuests();
            case LOOT_CRATES -> refreshLootcrates();
        }
    }

    @Override
    public void refreshRecipes(QuestObjectBase object) {
        if (object != null) {
            object.componentsToRefresh().forEach(this::refreshAll);
        }
    }

    @Override
    public boolean isRecipeModAvailable() {
        return true;
    }

    protected abstract void refreshQuests();

    protected abstract void refreshLootcrates();
}
