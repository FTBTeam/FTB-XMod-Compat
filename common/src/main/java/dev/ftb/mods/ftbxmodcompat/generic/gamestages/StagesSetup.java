package dev.ftb.mods.ftbxmodcompat.generic.gamestages;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.ftb.mods.ftblibrary.integration.stages.StageHelper;
import dev.ftb.mods.ftbxmodcompat.FTBXModCompat;
import dev.ftb.mods.ftbxmodcompat.config.FTBXModConfig;
import dev.ftb.mods.ftbxmodcompat.config.FTBXModConfig.StageSelector;

public class StagesSetup {
    public static void init() {
        StageSelector sel = FTBXModConfig.STAGE_SELECTOR.get();
        if (sel != StageSelector.DEFAULT && !sel.isUsable()) {
            FTBXModCompat.LOGGER.error("Stages implementation {} isn't available, falling back to default", sel);
            sel = StageSelector.DEFAULT;
        }

        switch (sel) {
            case KUBEJS -> addKubeJSProvider();
            case GAMESTAGES -> StageHelper.getInstance().setProviderImpl(new GameStagesStageProvider());
            case VANILLA -> { /* do nothing, this is the fallback */ }
            case DEFAULT -> {
                if (FTBXModCompat.isKubeJSLoaded) {
                    addKubeJSProvider();
                }
                // TODO: Gamestages does not have a stages implementation yet on 1.21
                /*else if (FTBXModCompat.isGameStagesLoaded) {
                    StageHelper.getInstance().setProviderImpl(new GameStagesStageProvider());
                }*/
            }
        }

        FTBXModCompat.LOGGER.info("Chose [{}] as the active game stages implementation", StageHelper.getInstance().getProvider().getName());
    }

    @ExpectPlatform
    public static void addKubeJSProvider() {
        throw new AssertionError();
    }
}
