package dev.ftb.mods.ftbxmodcompat.fabric;

import dev.ftb.mods.ftbxmodcompat.FTBXModCompat;
import dev.ftb.mods.ftbxmodcompat.fabric.ftbchunks.commonprot.FTBChunksProtectionProvider;
import dev.ftb.mods.ftbxmodcompat.fabric.ftbchunks.waystones.WaystonesCompat;
import dev.ftb.mods.ftbxmodcompat.fabric.ftbessentials.EssentialsCompat;
import dev.ftb.mods.ftbxmodcompat.fabric.ftbranks.RanksCompat;
import net.fabricmc.api.ModInitializer;

public class FTBXModCompatFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        FTBXModCompat.init();

        if (FTBXModCompat.isFTBChunksLoaded && FTBXModCompat.isWaystonesLoaded) {
            WaystonesCompat.init();
        }

        if (FTBXModCompat.isFTBChunksLoaded && FTBXModCompat.isCommonProtLoaded) {
            FTBChunksProtectionProvider.init();
        }

        if (FTBXModCompat.isFTBEssentialsLoaded) {
            EssentialsCompat.init();
        }

        if (FTBXModCompat.isFTBRanksLoaded) {
            RanksCompat.init();
        }
    }
}
