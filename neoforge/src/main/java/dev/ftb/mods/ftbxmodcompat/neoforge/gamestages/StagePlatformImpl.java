package dev.ftb.mods.ftbxmodcompat.neoforge.gamestages;

import dev.ftb.mods.ftblibrary.integration.stages.StageHelper;
import dev.ftb.mods.ftbxmodcompat.generic.gamestages.StagePlatform;
import dev.ftb.mods.ftbxmodcompat.generic.gamestages.neoforge.KubeJSStageProvider;
import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class StagePlatformImpl implements StagePlatform {
    @Override
    public void addKubeJSProvider() {
        StageHelper.getInstance().setProviderImpl(new KubeJSStageProvider());
    }

    @Override
    public boolean hasStage(Player player, String stage) {
        return GameStageHelper.hasStage(player, stage);
    }

    @Override
    public void addStage(Player player, String stage) {
        if (player instanceof ServerPlayer sp) GameStageHelper.addStage(sp, stage);
    }

    @Override
    public void removeStage(Player player, String stage) {
        if (player instanceof ServerPlayer sp) GameStageHelper.removeStage(sp, stage);
    }

    @Override
    public void syncStages(Player player) {
        if (player instanceof ServerPlayer sp) GameStageHelper.syncPlayer(sp);
    }
}
