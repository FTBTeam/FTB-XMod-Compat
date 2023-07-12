package dev.ftb.mods.ftbxmodcompat.ftbquests.jei;

import dev.ftb.mods.ftbquests.FTBQuests;
import dev.ftb.mods.ftbxmodcompat.ftbquests.jei_rei_common.WrappedLootCrate;
import dev.ftb.mods.ftbxmodcompat.ftbquests.jei_rei_common.WrappedQuest;
import mezz.jei.api.recipe.RecipeType;

public class JEIRecipeTypes {
    public static RecipeType<WrappedQuest> QUEST = RecipeType.create(FTBQuests.MOD_ID, "quest", WrappedQuest.class);
    public static RecipeType<WrappedLootCrate> LOOT_CRATE = RecipeType.create(FTBQuests.MOD_ID, "loot_crate", WrappedLootCrate.class);
}
