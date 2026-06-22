package dev.ftb.mods.ftbxmodcompat.fabric.ftbranks;

import dev.ftb.mods.ftbranks.api.fabric.FTBRanksEvents;
import dev.ftb.mods.ftbxmodcompat.ftbchunks.ftbranks.FTBRanksEventHandler;

public class RanksCompat {
    public static void init() {
        FTBRanksEvents.PLAYER_ADDED_TO_RANK.register(FTBRanksEventHandler::playerAdded);
        FTBRanksEvents.PLAYER_REMOVED_FROM_RANK.register(FTBRanksEventHandler::playerRemoved);
        FTBRanksEvents.PERMISSION_NODE_CHANGED.register(FTBRanksEventHandler::permissionSet);
        FTBRanksEvents.RANK_RELOADED.register(FTBRanksEventHandler::ranksReloaded);
        FTBRanksEvents.CONDITION_CHANGED.register(FTBRanksEventHandler::conditionChanged);
    }
}
