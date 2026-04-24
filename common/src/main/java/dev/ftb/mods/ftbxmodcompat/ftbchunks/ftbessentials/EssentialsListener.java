package dev.ftb.mods.ftbxmodcompat.ftbchunks.ftbessentials;

import dev.ftb.mods.ftbchunks.api.ClaimedChunk;
import dev.ftb.mods.ftbchunks.api.FTBChunksAPI;
import dev.ftb.mods.ftbessentials.api.event.RTPEvent;
import dev.ftb.mods.ftblibrary.math.ChunkDimPos;
import dev.ftb.mods.ftblibrary.util.result.Outcome;

public class EssentialsListener {
    public static Outcome onRTP(RTPEvent.Data eventData) {
        // prevent RTP from sending players into claimed chunks owned by someone else
        ClaimedChunk cc = FTBChunksAPI.api().getManager().getChunk(new ChunkDimPos(eventData.level(), eventData.pos()));
        return cc == null || cc.getTeamData().isTeamMember(eventData.serverPlayer().getUUID()) ? Outcome.PASS : Outcome.FAIL;
    }
}
