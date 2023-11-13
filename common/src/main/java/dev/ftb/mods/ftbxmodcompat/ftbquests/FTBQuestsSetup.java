package dev.ftb.mods.ftbxmodcompat.ftbquests;

import dev.ftb.mods.ftbquests.FTBQuests;
import dev.ftb.mods.ftbxmodcompat.FTBXModCompat;
import dev.ftb.mods.ftbxmodcompat.ftbquests.emi.helper.EMIRecipeHelper;
import dev.ftb.mods.ftbxmodcompat.ftbquests.jei.helper.JEIRecipeHelper;
import dev.ftb.mods.ftbxmodcompat.ftbquests.rei.helper.REIRecipeHelper;

public class FTBQuestsSetup {
    public static void init() {
        // EMI will load JEI plugins if JEI is also installed,
        // and REI will do the same if "REI Plugin Compatibilities" is installed,
        // so we make sure to check for JEI *last*
        if (FTBXModCompat.isEMILoaded) {
            FTBQuests.setRecipeModHelper(new EMIRecipeHelper());
        } else if (FTBXModCompat.isREILoaded) {
            FTBQuests.setRecipeModHelper(new REIRecipeHelper());
        } else if (FTBXModCompat.isJEILoaded) {
            FTBQuests.setRecipeModHelper(new JEIRecipeHelper());
        }
        FTBXModCompat.LOGGER.info("[FTB Quests] recipe helper provider is [{}]", FTBQuests.getRecipeModHelper().getHelperName());
    }
}