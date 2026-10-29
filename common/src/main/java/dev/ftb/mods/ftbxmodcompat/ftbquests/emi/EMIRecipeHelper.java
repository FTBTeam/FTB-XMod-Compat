package dev.ftb.mods.ftbxmodcompat.ftbquests.emi;

import dev.architectury.fluid.FluidStack;
import dev.emi.emi.api.EmiApi;
import dev.emi.emi.api.stack.EmiStack;
import dev.ftb.mods.ftblibrary.config.Tristate;
import dev.ftb.mods.ftblibrary.ui.input.Key;
import dev.ftb.mods.ftbxmodcompat.ftbquests.recipemod_common.BaseRecipeHelper;
import net.minecraft.world.item.ItemStack;

public class EMIRecipeHelper extends BaseRecipeHelper {
    @Override
    public void showRecipes(ItemStack stack) {
        EmiApi.displayRecipes(EmiStack.of(stack));
    }

    @Override
    public void showRecipes(FluidStack fluid) {
        EmiApi.displayRecipes(EmiStack.of(fluid.getFluid(), fluid.getPatch(), fluid.getAmount()));
    }

    // EMI has no public API for favorites
    @Override
    public Tristate toggleBookmark(ItemStack stack) {
        return Tristate.DEFAULT;
    }

    @Override
    public boolean isBookmarkKey(Key key) {
        return false;
    }

    @Override
    public String getHelperName() {
        return "EMI";
    }

    @Override
    protected void refreshQuests() {
    }

    @Override
    protected void refreshLootcrates() {
    }
}
