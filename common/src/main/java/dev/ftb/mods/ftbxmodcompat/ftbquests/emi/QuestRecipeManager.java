package dev.ftb.mods.ftbxmodcompat.ftbquests.emi;

import dev.ftb.mods.ftbxmodcompat.ftbquests.recipemod_common.WrappedQuest;
import dev.ftb.mods.ftbxmodcompat.ftbquests.recipemod_common.WrappedQuestCache;

import java.util.List;

public enum QuestRecipeManager {
    INSTANCE;

    private final WrappedQuestCache cache = new WrappedQuestCache();

    public void refresh() {
        cache.clear();
    }

    public List<WrappedQuest> getQuests() {
        return cache.getCachedItems();
    }
}
