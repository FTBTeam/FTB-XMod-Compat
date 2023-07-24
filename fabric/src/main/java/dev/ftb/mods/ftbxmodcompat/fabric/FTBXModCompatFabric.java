package dev.ftb.mods.ftbxmodcompat.fabric;

import dev.ftb.mods.ftbxmodcompat.FTBXModCompat;
import dev.ftb.mods.ftbxmodcompat.fabric.ftbchunks.commonprot.FTBChunksProtectionProvider;
import dev.ftb.mods.ftbxmodcompat.fabric.ftbchunks.waystones.WaystonesCompat;
import net.fabricmc.api.ModInitializer;

public class FTBXModCompatFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        FTBXModCompat.init();

        if (FTBXModCompat.isWaystonesLoaded) {
            WaystonesCompat.init();
        }

        if (FTBXModCompat.isCommonProtLoaded) {
            FTBChunksProtectionProvider.init();
        }
    }
}
