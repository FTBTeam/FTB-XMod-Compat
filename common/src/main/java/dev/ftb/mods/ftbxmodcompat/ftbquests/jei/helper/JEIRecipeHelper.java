package dev.ftb.mods.ftbxmodcompat.ftbquests.jei.helper;

import dev.ftb.mods.ftbxmodcompat.ftbquests.BaseRecipeHelper;
import dev.ftb.mods.ftbxmodcompat.ftbquests.jei.FTBQuestsJEIIntegration;
import dev.ftb.mods.ftbxmodcompat.ftbquests.jei.LootCrateRecipeManagerPlugin;
import dev.ftb.mods.ftbxmodcompat.ftbquests.jei.QuestRecipeManagerPlugin;
import net.minecraft.world.item.ItemStack;

public class JEIRecipeHelper extends BaseRecipeHelper {
    @Override
    public void showRecipes(ItemStack itemStack) {
        FTBQuestsJEIIntegration.showRecipes(itemStack);
    }

    @Override
    public String getHelperName() {
        return "JEI";
    }

    protected void refreshQuests() {
        QuestRecipeManagerPlugin.INSTANCE.refresh();
    }

    protected void refreshLootcrates() {
        LootCrateRecipeManagerPlugin.INSTANCE.refresh();
    }

}
