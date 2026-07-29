package dev.ftb.mods.ftbxmodcompat;

import dev.ftb.mods.ftblibrary.platform.fluid.FluidStack;
import mezz.jei.api.runtime.IJeiRuntime;
import org.jspecify.annotations.Nullable;

import java.util.ServiceLoader;

public interface FTBXModCompatPlatform {
    FTBXModCompatPlatform INSTANCE = ServiceLoader.load(FTBXModCompatPlatform.class).findFirst().orElseThrow();

    static FTBXModCompatPlatform get() {
        return INSTANCE;
    }

    void showFluidRecipes(@Nullable IJeiRuntime runtime, FluidStack fluid);
}
