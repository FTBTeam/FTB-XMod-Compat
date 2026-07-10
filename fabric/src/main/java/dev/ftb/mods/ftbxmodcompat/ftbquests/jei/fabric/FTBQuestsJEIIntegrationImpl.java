package dev.ftb.mods.ftbxmodcompat.ftbquests.jei.fabric;

import dev.architectury.fluid.FluidStack;
import mezz.jei.api.fabric.constants.FabricTypes;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.runtime.IJeiRuntime;

public class FTBQuestsJEIIntegrationImpl {
    public static void showFluidRecipes(IJeiRuntime runtime, FluidStack fluidStack) {
        if (runtime != null) {
            var ingredient = FabricTypes.FLUID_STACK.getDefaultIngredient(fluidStack.getFluid());
            runtime.getIngredientManager().getIngredientTypeChecked(ingredient)
                    .ifPresent(type -> runtime.getRecipesGui().show(
                            runtime.getJeiHelpers().getFocusFactory().createFocus(RecipeIngredientRole.OUTPUT, type, ingredient)
                    ));
        }
    }
}
