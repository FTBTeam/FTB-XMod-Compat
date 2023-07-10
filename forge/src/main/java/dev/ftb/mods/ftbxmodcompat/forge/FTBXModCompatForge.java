package dev.ftb.mods.ftbxmodcompat.forge;

import dev.architectury.platform.forge.EventBuses;
import dev.ftb.mods.ftbxmodcompat.FTBXModCompat;
import dev.ftb.mods.ftbxmodcompat.generic.gamestages.GameStagesEventHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FTBXModCompat.MOD_ID)
public class FTBXModCompatForge {
    public FTBXModCompatForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(FTBXModCompat.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        FTBXModCompat.init();

        // KubeJS handles gamestage functionality when it's installed
        // but this covers the case where Gamestages is present but KubeJS is not
        if (FTBXModCompat.isGameStagesLoaded && !FTBXModCompat.isKubeJSLoaded) {
            GameStagesEventHandler.register();
        }
    }
}
