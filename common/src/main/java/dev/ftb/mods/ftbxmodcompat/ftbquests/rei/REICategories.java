package dev.ftb.mods.ftbxmodcompat.ftbquests.rei;

import dev.ftb.mods.ftbquests.FTBQuests;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;

public class REICategories {
    public static final CategoryIdentifier<QuestDisplay> QUEST = CategoryIdentifier.of(FTBQuests.MOD_ID, "quest");
    public static final CategoryIdentifier<LootCrateDisplay> LOOT_CRATE = CategoryIdentifier.of(FTBQuests.MOD_ID, "loot_crate");
}
