package dev.ftb.mods.ftbxmodcompat.generic.gamestages.neoforge;

import dev.ftb.mods.ftblibrary.integration.stages.StageProvider;
import dev.latvian.mods.kubejs.core.PlayerKJS;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class KubeJSStageProvider implements StageProvider {
    @Override
    public boolean has(Player player, String stage) {
        return player instanceof PlayerKJS playerKJS && playerKJS.kjs$getStages().has(stage);
    }

    @Override
    public void add(ServerPlayer player, String stage) {
        if (player instanceof PlayerKJS playerKJS) playerKJS.kjs$getStages().add(stage);
    }

    @Override
    public void remove(ServerPlayer player, String stage) {
        if (player instanceof PlayerKJS playerKJS) playerKJS.kjs$getStages().remove(stage);
    }

    @Override
    public void sync(ServerPlayer player) {
        if (player instanceof PlayerKJS playerKJS) playerKJS.kjs$getStages().sync();
    }

    @Override
    public String getName() {
        return "KubeJS Stages";
    }
}
