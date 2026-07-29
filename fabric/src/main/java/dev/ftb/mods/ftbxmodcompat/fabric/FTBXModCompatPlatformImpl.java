package dev.ftb.mods.ftbxmodcompat.fabric;

import dev.ftb.mods.ftblibrary.platform.fluid.FluidStack;
import dev.ftb.mods.ftbxmodcompat.FTBXModCompatPlatform;
import mezz.jei.api.fabric.constants.FabricTypes;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.runtime.IJeiRuntime;
import org.jspecify.annotations.Nullable;

public class FTBXModCompatPlatformImpl implements FTBXModCompatPlatform {
    @Override
    public void showFluidRecipes(@Nullable IJeiRuntime runtime, FluidStack fluidStack) {
        if (runtime != null) {
            var ingredient = FabricTypes.FLUID_STACK.getDefaultIngredient(fluidStack.fluid());
            runtime.getIngredientManager().getIngredientTypeChecked(ingredient)
                    .ifPresent(type -> runtime.getRecipesGui().show(
                            runtime.getJeiHelpers().getFocusFactory().createFocus(RecipeIngredientRole.OUTPUT, type, ingredient)
                    ));
        }
    }
}
