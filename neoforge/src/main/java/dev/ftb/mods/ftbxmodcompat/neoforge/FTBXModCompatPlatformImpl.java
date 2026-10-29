package dev.ftb.mods.ftbxmodcompat.neoforge;

import dev.ftb.mods.ftblibrary.platform.fluid.FluidStack;
import dev.ftb.mods.ftbxmodcompat.FTBXModCompatPlatform;
import mezz.jei.api.neoforge.NeoForgeTypes;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.runtime.IJeiRuntime;
import org.jspecify.annotations.Nullable;

public class FTBXModCompatPlatformImpl implements FTBXModCompatPlatform {
    @Override
    public void showFluidRecipes(@Nullable IJeiRuntime runtime, FluidStack fluidStack) {
        if (runtime != null) {
            var neoStack = NeoForgeTypes.FLUID_STACK.getDefaultIngredient(fluidStack.fluid());
            neoStack.setAmount((int) fluidStack.amount());
            neoStack.applyComponents(fluidStack.getComponentsPatch());

            runtime.getIngredientManager().getIngredientTypeChecked(neoStack)
                    .ifPresent(type -> runtime.getRecipesGui().show(
                            runtime.getJeiHelpers().getFocusFactory().createFocus(RecipeIngredientRole.OUTPUT, type, neoStack)
                    ));
        }
    }
}
