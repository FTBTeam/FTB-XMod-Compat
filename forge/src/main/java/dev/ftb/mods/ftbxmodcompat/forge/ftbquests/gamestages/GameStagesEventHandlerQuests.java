package dev.ftb.mods.ftbxmodcompat.forge.ftbquests.gamestages;

import dev.ftb.mods.ftbquests.quest.task.StageTask;
import net.darkhax.gamestages.event.GameStageEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.MinecraftForge;

public class GameStagesEventHandlerQuests {
    public static void register() {
        MinecraftForge.EVENT_BUS.addListener(GameStagesEventHandlerQuests::onStageAdded);
        MinecraftForge.EVENT_BUS.addListener(GameStagesEventHandlerQuests::onStageRemoved);
        MinecraftForge.EVENT_BUS.addListener(GameStagesEventHandlerQuests::onStagesCleared);
    }

    private static void onStageAdded(GameStageEvent.Added event) {
        if (event.getEntity() instanceof ServerPlayer sp) StageTask.checkStages(sp);
    }

    private static void onStageRemoved(GameStageEvent.Removed event) {
        if (event.getEntity() instanceof ServerPlayer sp) StageTask.checkStages(sp);
    }

    private static void onStagesCleared(GameStageEvent.Cleared event) {
        if (event.getEntity() instanceof ServerPlayer sp) StageTask.checkStages(sp);
    }
}
