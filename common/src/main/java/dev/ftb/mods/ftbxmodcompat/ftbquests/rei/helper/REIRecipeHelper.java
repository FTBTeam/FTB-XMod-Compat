package dev.ftb.mods.ftbxmodcompat.ftbquests.rei.helper;

import dev.architectury.fluid.FluidStack;
import dev.ftb.mods.ftbxmodcompat.ftbquests.recipemod_common.BaseRecipeHelper;
import dev.ftb.mods.ftbxmodcompat.ftbquests.rei.FTBQuestsREIIntegration;
import dev.ftb.mods.ftbxmodcompat.ftbquests.rei.LootCrateDisplayGenerator;
import dev.ftb.mods.ftbxmodcompat.ftbquests.rei.QuestDisplayGenerator;
import net.minecraft.world.item.ItemStack;

public class REIRecipeHelper extends BaseRecipeHelper {
    @Override
    public void showRecipes(ItemStack stack) {
        FTBQuestsREIIntegration.showRecipes(stack);
    }

    @Override
    public void showRecipes(FluidStack stack) {
        FTBQuestsREIIntegration.showRecipes(stack);
    }

    @Override
    public String getHelperName() {
        return "REI";
    }

    protected void refreshQuests() {
        QuestDisplayGenerator.INSTANCE.refresh();
    }

    protected void refreshLootcrates() {
        LootCrateDisplayGenerator.INSTANCE.refresh();
    }
}
