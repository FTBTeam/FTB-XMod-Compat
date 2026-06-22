package dev.ftb.mods.ftbxmodcompat.neoforge.ftbranks;

import dev.ftb.mods.ftbranks.api.neoforge.FTBRanksEvent;
import dev.ftb.mods.ftbxmodcompat.ftbchunks.ftbranks.FTBRanksEventHandler;
import net.neoforged.neoforge.common.NeoForge;

public class RanksCompat {
    public static void init() {
        NeoForge.EVENT_BUS.addListener(FTBRanksEvent.PlayerAdded.class,
                event -> FTBRanksEventHandler.playerAdded(event.getEventData()));
        NeoForge.EVENT_BUS.addListener(FTBRanksEvent.PlayerRemoved.class,
                event -> FTBRanksEventHandler.playerRemoved(event.getEventData()));
        NeoForge.EVENT_BUS.addListener(FTBRanksEvent.PermissionNodeChanged.class,
                event -> FTBRanksEventHandler.permissionSet(event.getEventData()));
        NeoForge.EVENT_BUS.addListener(FTBRanksEvent.Reloaded.class,
                event -> FTBRanksEventHandler.ranksReloaded(event.getEventData()));
        NeoForge.EVENT_BUS.addListener(FTBRanksEvent.ConditionChanged.class,
                event -> FTBRanksEventHandler.conditionChanged(event.getEventData()));
    }
}
