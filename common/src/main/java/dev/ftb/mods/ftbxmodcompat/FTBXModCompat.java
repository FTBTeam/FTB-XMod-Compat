package dev.ftb.mods.ftbxmodcompat;

import dev.ftb.mods.ftblibrary.config.manager.ConfigManager;
import dev.ftb.mods.ftblibrary.platform.Platform;
import dev.ftb.mods.ftbxmodcompat.config.FTBXModConfig;
import dev.ftb.mods.ftbxmodcompat.ftbchunks.FTBChunksSetup;
import dev.ftb.mods.ftbxmodcompat.ftbquests.FTBQuestsSetup;
import dev.ftb.mods.ftbxmodcompat.generic.currency.CurrencySetup;
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
    public static boolean isFTBEssentialsLoaded;
    public static boolean isFTBFilterSystemLoaded;

    public static boolean isKubeJSLoaded;
    public static boolean isItemFiltersLoaded;
    public static boolean isGameStagesLoaded;
    public static boolean isREILoaded;
    public static boolean isJEILoaded;
    public static boolean isLuckPermsLoaded;
    public static boolean isWaystonesLoaded;
    public static boolean isCommonProtLoaded;
    public static boolean isMagicCoinsLoaded;

    public static void init() {
        detectLoadedMods();

        ConfigManager.getInstance().registerStartupConfig(FTBXModConfig.CONFIG, MOD_ID);

        onSetup();
//        LifecycleEvent.SETUP.register(FTBXModCompat::onSetup);
    }

    private static void onSetup() {
        StagesSetup.init();
        PermissionsSetup.init();
        CurrencySetup.init();
        if (isFTBQuestsLoaded) {
            FTBQuestsSetup.init();
        }
        if (isFTBChunksLoaded) {
            FTBChunksSetup.init();
        }
    }

    private static void detectLoadedMods() {
        isFTBQuestsLoaded = Platform.get().isModLoaded("ftbquests");
        isFTBChunksLoaded = Platform.get().isModLoaded("ftbchunks");
        isFTBRanksLoaded = Platform.get().isModLoaded("ftbranks");
        isFTBEssentialsLoaded = Platform.get().isModLoaded("ftbessentials");
        isFTBFilterSystemLoaded = Platform.get().isModLoaded("ftbfiltersystem");

        isKubeJSLoaded = Platform.get().isModLoaded("kubejs");
        isItemFiltersLoaded = Platform.get().isModLoaded("itemfilters");
        isGameStagesLoaded = Platform.get().isModLoaded("gamestages");
        isREILoaded = Platform.get().isModLoaded("roughlyenoughitems");
        isJEILoaded = Platform.get().isModLoaded("jei");
        isLuckPermsLoaded = Platform.get().isModLoaded("luckperms");
        isWaystonesLoaded = Platform.get().isModLoaded("waystones");
        isCommonProtLoaded = Platform.get().isModLoaded("common-protection-api");
        isMagicCoinsLoaded = Platform.get().isModLoaded("magic_coins");
    }
}
