package dev.ftb.mods.ftbxmodcompat.generic.gamestages.neoforge;

import dev.ftb.mods.ftblibrary.integration.stages.StageHelper;
import dev.ftb.mods.ftbxmodcompat.FTBXModCompat;

public class StagesSetupImpl {
    public static void addKubeJSProvider() {
        StageHelper.getInstance().setProviderImpl(new KubeJSStageProvider());
    }
}
