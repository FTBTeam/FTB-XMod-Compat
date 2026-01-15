package dev.ftb.mods.ftbxmodcompat.ftbquests.jei;

import dev.ftb.mods.ftbquests.api.FTBQuestsAPI;
import dev.ftb.mods.ftbxmodcompat.ftbquests.recipemod_common.WrappedLootCrate;
import dev.ftb.mods.ftbxmodcompat.ftbquests.recipemod_common.WrappedQuest;
import mezz.jei.api.recipe.types.IRecipeType;

public class JEIRecipeTypes {
    public static IRecipeType<WrappedQuest> QUEST = IRecipeType.create(FTBQuestsAPI.MOD_ID, "quest", WrappedQuest.class);
    public static IRecipeType<WrappedLootCrate> LOOT_CRATE = IRecipeType.create(FTBQuestsAPI.MOD_ID, "loot_crate", WrappedLootCrate.class);
}
