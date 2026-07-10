package dev.ftb.mods.ftbxmodcompat.ftbquests.jei.neoforge;

import dev.architectury.fluid.FluidStack;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.runtime.IJeiRuntime;

public class FTBQuestsJEIIntegrationImpl {
    public static void showFluidRecipes(IJeiRuntime runtime, FluidStack fluidStack) {
        net.neoforged.neoforge.fluids.FluidStack neoStack
                = new net.neoforged.neoforge.fluids.FluidStack(fluidStack.getFluid().builtInRegistryHolder(), (int) fluidStack.getAmount(), fluidStack.getPatch());

        if (runtime != null) {
            runtime.getIngredientManager().getIngredientTypeChecked(neoStack)
                    .ifPresent(type -> runtime.getRecipesGui().show(
                            runtime.getJeiHelpers().getFocusFactory().createFocus(RecipeIngredientRole.OUTPUT, type, neoStack)
                    ));
        }
    }
}
