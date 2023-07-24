package dev.ftb.mods.ftbxmodcompat.generic.gamestages.forge;

import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

@SuppressWarnings("unused")
public class GameStagesStageProviderImpl {
    public static boolean hasStage(Player player, String stage) {
        return GameStageHelper.hasStage(player, stage);
    }

    public static void addStage(Player player, String stage) {
        if (player instanceof ServerPlayer sp) GameStageHelper.addStage(sp, stage);
    }

    public static void removeStage(Player player, String stage) {
        if (player instanceof ServerPlayer sp) GameStageHelper.removeStage(sp, stage);
    }
}
