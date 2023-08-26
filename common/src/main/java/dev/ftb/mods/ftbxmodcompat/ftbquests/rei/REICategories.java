package dev.ftb.mods.ftbxmodcompat.ftbquests.rei;

import dev.ftb.mods.ftbquests.api.FTBQuestsAPI;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;

public class REICategories {
    public static final CategoryIdentifier<QuestDisplay> QUEST = CategoryIdentifier.of(FTBQuestsAPI.MOD_ID, "quest");
    public static final CategoryIdentifier<LootCrateDisplay> LOOT_CRATE = CategoryIdentifier.of(FTBQuestsAPI.MOD_ID, "loot_crate");
}
