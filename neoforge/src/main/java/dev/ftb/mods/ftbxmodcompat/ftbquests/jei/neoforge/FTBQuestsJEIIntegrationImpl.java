package dev.ftb.mods.ftbxmodcompat.ftbquests.jei.neoforge;

import dev.architectury.fluid.FluidStack;
import mezz.jei.api.neoforge.NeoForgeTypes;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.runtime.IJeiRuntime;

public class FTBQuestsJEIIntegrationImpl {
    public static void showFluidRecipes(IJeiRuntime runtime, FluidStack fluidStack) {
        if (runtime != null) {
            var neoStack = NeoForgeTypes.FLUID_STACK.getDefaultIngredient(fluidStack.getFluid());
            neoStack.setAmount((int) fluidStack.getAmount());
            neoStack.applyComponents(fluidStack.getPatch());
            runtime.getIngredientManager().createTypedIngredient(NeoForgeTypes.FLUID_STACK, neoStack, false)
                    .ifPresent(typedIngredient -> runtime.getRecipesGui().show(
                            runtime.getJeiHelpers().getFocusFactory().createFocus(RecipeIngredientRole.OUTPUT, typedIngredient)
                    ));
        }
    }
}
