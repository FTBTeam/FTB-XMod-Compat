package dev.ftb.mods.ftbxmodcompat;

import dev.architectury.platform.Platform;
import dev.ftb.mods.ftbxmodcompat.ftbquests.FTBQuestsSetup;
import dev.ftb.mods.ftbxmodcompat.generic.gamestages.StagesSetup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FTBXModCompat {
    public static final String MOD_ID = "ftbxmodcompat";

    public static final Logger LOGGER = LogManager.getLogger("FTB XMod Compat");

    public static boolean isFTBQuestsLoaded;

    public static boolean isKubeJSLoaded;
    public static boolean isGameStagesLoaded;
    public static boolean isREILoaded;
    public static boolean isJEILoaded;

    public static void init() {
        detectLoadedMods();

        StagesSetup.init();
        if (isFTBQuestsLoaded) {
            FTBQuestsSetup.init();
        }
    }

    private static void detectLoadedMods() {
        isFTBQuestsLoaded = Platform.isModLoaded("ftbquests");

        isKubeJSLoaded = Platform.isModLoaded("kubejs");
        isGameStagesLoaded = Platform.isModLoaded("gamestages");
        isREILoaded = Platform.isModLoaded("roughlyenoughitems");
        isJEILoaded = Platform.isModLoaded("jei");
    }
}
