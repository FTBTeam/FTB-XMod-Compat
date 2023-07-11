package dev.ftb.mods.ftbxmodcompat.generic.gamestages;

import dev.ftb.mods.ftblibrary.integration.stages.StageHelper;
import dev.ftb.mods.ftbxmodcompat.FTBXModCompat;

public class StagesSetup {
    public static void init() {
        if (FTBXModCompat.isKubeJSLoaded) {
            StageHelper.getInstance().setProviderImpl(new KubeJSStageProvider());
        } else if (FTBXModCompat.isGameStagesLoaded) {
            StageHelper.getInstance().setProviderImpl(new GameStagesStageProvider());
        }
        FTBXModCompat.LOGGER.info("Chose [{}] as the active game stages implementation", StageHelper.getInstance().getProvider().getName());
    }
}
