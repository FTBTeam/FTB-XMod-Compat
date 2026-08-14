package dev.ftb.mods.ftbxmodcompat.ftbquests.jei.helper;

import dev.architectury.fluid.FluidStack;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.ftb.mods.ftblibrary.config.Tristate;
import dev.ftb.mods.ftblibrary.ui.input.Key;
import dev.ftb.mods.ftbxmodcompat.FTBXModCompat;
import dev.ftb.mods.ftbxmodcompat.ftbquests.jei.FTBQuestsJEIIntegration;
import dev.ftb.mods.ftbxmodcompat.ftbquests.jei.LootCrateRecipeManagerPlugin;
import dev.ftb.mods.ftbxmodcompat.ftbquests.jei.QuestRecipeManagerPlugin;
import dev.ftb.mods.ftbxmodcompat.ftbquests.recipemod_common.BaseRecipeHelper;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.ingredients.IIngredientTypeWithSubtypes;
import mezz.jei.api.runtime.IIngredientManager;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;

import java.util.Collection;

public class JEIRecipeHelper extends BaseRecipeHelper {
    @Override
    public void showRecipes(ItemStack itemStack) {
        FTBQuestsJEIIntegration.showRecipes(itemStack);
    }

    @Override
    public void showRecipes(FluidStack fluid) {
        FTBQuestsJEIIntegration.showRecipes(fluid);
    }

    @ExpectPlatform
    public static IIngredientTypeWithSubtypes<Fluid,?> getNativeFluidType(FluidStack fluid) {
        throw new AssertionError();
    }

    @Override
    public Tristate toggleBookmark(ItemStack stack) {
        IJeiRuntime runtime = FTBQuestsJEIIntegration.runtime;
        if (runtime != null && !FTBXModCompat.isTMRVLoaded) {
            return JEIInternalsHelper.toggleBookmark(stack, runtime);
        }
        return Tristate.DEFAULT;
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

    @Override
    public void updateItemsDynamic(Collection<ItemStack> toRemove, Collection<ItemStack> toAdd) {
        if (FTBQuestsJEIIntegration.runtime != null) {
            IIngredientManager manager = FTBQuestsJEIIntegration.runtime.getIngredientManager();
            if (!toRemove.isEmpty()) {
                manager.removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK, toRemove);
            }
            if (!toAdd.isEmpty()) {
                manager.addIngredientsAtRuntime(VanillaTypes.ITEM_STACK, toAdd);
            }
            FTBXModCompat.LOGGER.debug("removed {} items from JEI, added {} items", toRemove.size(), toAdd.size());
        }
    }

    @Override
    public boolean isBookmarkKey(Key key) {
        // TODO non-API usage!  API only exposes the 'R' and 'U' mappings
        return !FTBXModCompat.isTMRVLoaded && JEIInternalsHelper.isBookmarkK(key);
    }
}
