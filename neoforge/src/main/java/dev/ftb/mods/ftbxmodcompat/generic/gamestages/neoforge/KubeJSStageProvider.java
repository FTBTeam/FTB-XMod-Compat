package dev.ftb.mods.ftbxmodcompat.generic.gamestages.neoforge;

import dev.ftb.mods.ftblibrary.integration.stages.StageProvider;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class KubeJSStageProvider implements StageProvider {
    @Override
    public boolean has(Player player, String stage) {
        return false;
//        return Stages.get(player).has(stage);
    }

    @Override
    public void add(ServerPlayer player, String stage) {
//        Stages.get(player).add(stage);
    }

    @Override
    public void remove(ServerPlayer player, String stage) {
//        Stages.get(player).remove(stage);
    }

    @Override
    public void sync(ServerPlayer player) {
//        Stages.get(player).sync();
    }

    @Override
    public String getName() {
        return "KubeJS Stages (non-functional!)";
    }
}
