package dev.ftb.mods.ftbxmodcompat.ftbquests.emi;

import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import dev.ftb.mods.ftbquests.FTBQuests;
import dev.ftb.mods.ftbquests.item.FTBQuestsItems;
import net.minecraft.resources.ResourceLocation;

public class EMICategories {
    public static final EmiRecipeCategory QUEST = new EmiRecipeCategory(ResourceLocation.tryBuild(FTBQuests.MOD_ID, "quest"), EmiStack.of(FTBQuestsItems.BOOK.get()));
    public static final EmiRecipeCategory LOOT_CRATE = new EmiRecipeCategory(ResourceLocation.tryBuild(FTBQuests.MOD_ID, "loot_crate"), EmiStack.of(FTBQuestsItems.BOOK.get()));
}
