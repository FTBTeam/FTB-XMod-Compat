package dev.ftb.mods.ftbxmodcompat.fabric;

import dev.ftb.mods.ftblibrary.platform.fluid.FluidStack;
import dev.ftb.mods.ftbxmodcompat.FTBXModCompatPlatform;
import mezz.jei.api.fabric.ingredients.fluids.JeiFluidIngredient;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.runtime.IJeiRuntime;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import org.jspecify.annotations.Nullable;

public class FTBXModCompatPlatformImpl implements FTBXModCompatPlatform {
    @Override
    public void showFluidRecipes(@Nullable IJeiRuntime runtime, FluidStack fluidStack) {
        if (runtime != null) {
            var ingredient = new JeiFluidIngredient(FluidVariant.of(fluidStack.fluid(), fluidStack.getComponentsPatch()), fluidStack.amount());
            runtime.getIngredientManager().getIngredientTypeChecked(ingredient)
                    .ifPresent(type -> runtime.getRecipesGui().show(
                            runtime.getJeiHelpers().getFocusFactory().createFocus(RecipeIngredientRole.OUTPUT, type, ingredient)
                    ));
        }
    }
}
