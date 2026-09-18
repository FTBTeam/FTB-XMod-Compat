package dev.ftb.mods.ftbxmodcompat.ftbquests.jei.fabric;

import dev.architectury.fluid.FluidStack;
import mezz.jei.api.fabric.constants.FabricTypes;
import mezz.jei.api.fabric.ingredients.fluids.JeiFluidIngredient;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.runtime.IJeiRuntime;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;

public class FTBQuestsJEIIntegrationImpl {
    public static void showFluidRecipes(IJeiRuntime runtime, FluidStack fluidStack) {
        if (runtime != null) {
            var ingredient = new JeiFluidIngredient(FluidVariant.of(fluidStack.getFluid(), fluidStack.getPatch()), fluidStack.getAmount());
            runtime.getIngredientManager().createTypedIngredient(FabricTypes.FLUID_STACK, ingredient, false)
                    .ifPresent(typedIngredient -> runtime.getRecipesGui().show(
                            runtime.getJeiHelpers().getFocusFactory().createFocus(RecipeIngredientRole.OUTPUT, typedIngredient)
                    ));
        }
    }
}
