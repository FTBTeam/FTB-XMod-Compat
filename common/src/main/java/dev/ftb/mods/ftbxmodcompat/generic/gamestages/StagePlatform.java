package dev.ftb.mods.ftbxmodcompat.generic.gamestages;

import net.minecraft.world.entity.player.Player;

import java.util.ServiceLoader;

public interface StagePlatform {
    StagePlatform INSTANCE = ServiceLoader.load(StagePlatform.class).findFirst().orElseThrow();

    static StagePlatform get() {
        return INSTANCE;
    }

    void addKubeJSProvider();

    boolean hasStage(Player player, String stage);

    void addStage(Player player, String stage);

    void removeStage(Player player, String stage);

    void syncStages(Player player);
}
