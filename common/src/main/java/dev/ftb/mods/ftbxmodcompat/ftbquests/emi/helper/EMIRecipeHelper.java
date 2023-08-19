package dev.ftb.mods.ftbxmodcompat.ftbquests.emi.helper;

import dev.ftb.mods.ftbxmodcompat.ftbquests.emi.FTBQuestsEMIIntegration;
import dev.ftb.mods.ftbxmodcompat.ftbquests.recipemod_common.BaseRecipeHelper;
import net.minecraft.world.item.ItemStack;

public class EMIRecipeHelper extends BaseRecipeHelper {
    @Override
    public void showRecipes(ItemStack itemStack) {
        FTBQuestsEMIIntegration.showRecipes(itemStack);
    }

    @Override
    public String getHelperName() {
        return "EMI";
    }

    protected void refreshQuests() {
        // TODO
    }

    protected void refreshLootcrates() {
        // TODO
    }
}
