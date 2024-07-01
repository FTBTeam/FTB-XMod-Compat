package dev.ftb.mods.ftbxmodcompat.generic.gamestages.neoforge;

import dev.ftb.mods.ftblibrary.integration.stages.StageHelper;
import org.jline.utils.Log;

public class StagesSetupImpl {
    public static void addKubeJSProvider() {
        Log.warn("KubeJS gamestages are not functional on 1.21 yet!");
        StageHelper.getInstance().setProviderImpl(new KubeJSStageProvider());
    }
}
