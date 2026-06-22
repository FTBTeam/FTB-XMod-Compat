package dev.ftb.mods.ftbxmodcompat.fabric.gamestages;

import dev.ftb.mods.ftbxmodcompat.generic.gamestages.StagePlatform;
import net.minecraft.world.entity.player.Player;

/**
 * Dummy no-op implementation since there's no Gamestages or KubeJS on Fabric.
 */
public class StagePlatformImpl implements StagePlatform {
    @Override
    public void addKubeJSProvider() {
    }

    @Override
    public boolean hasStage(Player player, String stage) {
        return false;
    }

    @Override
    public void addStage(Player player, String stage) {
    }

    @Override
    public void removeStage(Player player, String stage) {
    }

    @Override
    public void syncStages(Player player) {
    }
}
