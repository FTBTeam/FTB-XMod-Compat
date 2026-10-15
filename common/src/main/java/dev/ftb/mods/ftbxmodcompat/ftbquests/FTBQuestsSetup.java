package dev.ftb.mods.ftbxmodcompat.ftbquests;

import dev.ftb.mods.ftbquests.FTBQuests;
import dev.ftb.mods.ftbxmodcompat.FTBXModCompat;
import dev.ftb.mods.ftbxmodcompat.ftbquests.emi.EMIRecipeHelper;
import dev.ftb.mods.ftbxmodcompat.ftbquests.filtering.ItemFilteringSetup;
import dev.ftb.mods.ftbxmodcompat.ftbquests.jei.helper.JEIRecipeHelper;
import dev.ftb.mods.ftbxmodcompat.ftbquests.rei.helper.REIRecipeHelper;

public class FTBQuestsSetup {
    public static void init() {
        if (FTBXModCompat.isJEILoaded) {
            FTBQuests.setRecipeModHelper(new JEIRecipeHelper());
        } else if (FTBXModCompat.isREILoaded) {
            FTBQuests.setRecipeModHelper(new REIRecipeHelper());
        } else if (FTBXModCompat.isEMILoaded) {
            FTBQuests.setRecipeModHelper(new EMIRecipeHelper());
        }
        FTBXModCompat.LOGGER.info("[FTB Quests] recipe helper provider is [{}]", FTBQuests.getRecipeModHelper().getHelperName());

        ItemFilteringSetup.init();
    }
}
