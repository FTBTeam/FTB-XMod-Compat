package dev.ftb.mods.ftbxmodcompat.neoforge;

import dev.ftb.mods.ftbxmodcompat.FTBXModCompat;
import dev.ftb.mods.ftbxmodcompat.neoforge.ftbchunks.waystones.WaystonesCompat;
import dev.ftb.mods.ftbxmodcompat.neoforge.ftbquests.gamestages.GameStagesEventHandlerQuests;
import net.neoforged.fml.common.Mod;

@Mod(FTBXModCompat.MOD_ID)
public class FTBXModCompatNeoForge {
    public FTBXModCompatNeoForge() {
        // Submit our event bus to let architectury register our content on the right time
        FTBXModCompat.init();

        // KubeJS handles gamestage functionality when it's installed
        // but this covers the case where Gamestages is present but KubeJS is not
        if (FTBXModCompat.isFTBQuestsLoaded && FTBXModCompat.isGameStagesLoaded && !FTBXModCompat.isKubeJSLoaded) {
            GameStagesEventHandlerQuests.register();
        }

        if (FTBXModCompat.isFTBChunksLoaded && FTBXModCompat.isWaystonesLoaded) {
            WaystonesCompat.init();
        }
    }
}
