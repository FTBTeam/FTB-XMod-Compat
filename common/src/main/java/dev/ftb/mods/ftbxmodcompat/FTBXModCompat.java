package dev.ftb.mods.ftbxmodcompat;

import dev.architectury.platform.Platform;
import dev.ftb.mods.ftbxmodcompat.ftbchunks.FTBChunksSetup;
import dev.ftb.mods.ftbxmodcompat.ftbquests.FTBQuestsSetup;
import dev.ftb.mods.ftbxmodcompat.generic.gamestages.StagesSetup;
import dev.ftb.mods.ftbxmodcompat.generic.permissions.PermissionsSetup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FTBXModCompat {
    public static final String MOD_ID = "ftbxmodcompat";

    public static final Logger LOGGER = LogManager.getLogger("FTB XMod Compat");

    public static boolean isFTBQuestsLoaded;
    public static boolean isFTBChunksLoaded;
    public static boolean isFTBRanksLoaded;

    public static boolean isKubeJSLoaded;
    public static boolean isGameStagesLoaded;
    public static boolean isREILoaded;
    public static boolean isJEILoaded;
    public static boolean isEMILoaded;
    public static boolean isLuckPermsLoaded;
    public static boolean isWaystonesLoaded;
    public static boolean isCommonProtLoaded;

    public static void init() {
        detectLoadedMods();

        StagesSetup.init();
        PermissionsSetup.init();
        if (isFTBQuestsLoaded) {
            FTBQuestsSetup.init();
        }
        if (isFTBChunksLoaded) {
            FTBChunksSetup.init();
        }
    }

    private static void detectLoadedMods() {
        isFTBQuestsLoaded = Platform.isModLoaded("ftbquests");
        isFTBChunksLoaded = Platform.isModLoaded("ftbchunks");
        isFTBRanksLoaded = Platform.isModLoaded("ftbranks");

        isKubeJSLoaded = Platform.isModLoaded("kubejs");
        isGameStagesLoaded = Platform.isModLoaded("gamestages");
        isREILoaded = Platform.isModLoaded("roughlyenoughitems");
        isJEILoaded = Platform.isModLoaded("jei");
        isEMILoaded = Platform.isModLoaded("emi");
        isLuckPermsLoaded = Platform.isModLoaded("luckperms");
        isWaystonesLoaded = Platform.isModLoaded("waystones");
        isCommonProtLoaded = Platform.isModLoaded("common-protection-api");
    }
}
