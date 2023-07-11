package dev.ftb.mods.ftbxmodcompat.ftbquests;

import dev.ftb.mods.ftbquests.integration.RecipeModHelper;
import dev.ftb.mods.ftbquests.quest.QuestObjectBase;

import java.util.Set;

import static dev.ftb.mods.ftbquests.integration.RecipeModHelper.Components.LOOT_CRATES;
import static dev.ftb.mods.ftbquests.integration.RecipeModHelper.Components.QUESTS;

public abstract class BaseRecipeHelper implements RecipeModHelper {
    @Override
    public void refreshRecipes(QuestObjectBase object) {
        if (object != null) {
            Set<Components> toRefresh = object.componentsToRefresh();
            if (toRefresh.contains(QUESTS)) {
                refreshQuests();
            }
            if (toRefresh.contains(LOOT_CRATES)) {
                refreshLootcrates();
            }
        }
    }

    @Override
    public boolean isRecipeModAvailable() {
        return true;
    }

    protected abstract void refreshQuests();

    protected abstract void refreshLootcrates();
}
