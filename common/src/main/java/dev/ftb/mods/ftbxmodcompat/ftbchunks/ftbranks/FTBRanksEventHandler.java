package dev.ftb.mods.ftbxmodcompat.ftbchunks.ftbranks;

import dev.ftb.mods.ftbchunks.api.ChunkTeamData;
import dev.ftb.mods.ftbchunks.api.FTBChunksAPI;
import dev.ftb.mods.ftbranks.api.RankManager;
import dev.ftb.mods.ftbranks.api.event.*;
import dev.ftb.mods.ftbteams.api.FTBTeamsAPI;
import dev.ftb.mods.ftbxmodcompat.FTBXModCompat;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.NameAndId;
import org.jetbrains.annotations.UnknownNullability;

import static dev.ftb.mods.ftbchunks.integration.PermissionsHelper.*;

public class FTBRanksEventHandler {
	public static void registerEvents() {
		FTBXModCompat.LOGGER.info("[FTB Chunks] FTB Ranks detected, listening for ranks events");
	}

	public static void playerAdded(PlayerAddedToRankEvent.@UnknownNullability Data event) {
		updateForPlayer(event.manager(), event.player());
	}

	public static void playerRemoved(PlayerRemovedFromRankEvent.@UnknownNullability Data event) {
		updateForPlayer(event.manager(), event.player());
	}

	public static void permissionSet(PermissionNodeChangedEvent.@UnknownNullability Data event) {
		String node = event.node();
		if (node.equals(MAX_CLAIMED_PERM) || node.equals(MAX_FORCE_LOADED_PERM) || node.equals(CHUNK_LOAD_OFFLINE_PERM)) {
			updateAll(event.manager());
		}
	}

	public static void ranksReloaded(RanksReloadedEvent.@UnknownNullability Data event) {
		updateAll(event.manager());
	}

	public static void conditionChanged(ConditionChangedEvent.@UnknownNullability Data event) {
		updateAll(event.manager());
	}

	private static void updateAll(RankManager manager) {
		if (FTBChunksAPI.api().isManagerLoaded()) {
			manager.getServer().getPlayerList().getPlayers().forEach(player -> {
				ChunkTeamData teamData = FTBChunksAPI.api().getManager().getOrCreateData(player);
				if (teamData != null) {
					teamData.checkMemberForceLoading(player.getUUID());
				}
			});
		}
	}

	private static void updateForPlayer(RankManager manager, NameAndId profile) {
		FTBTeamsAPI.api().getManager().getTeamForPlayerID(profile.id()).ifPresent(team -> {
			ChunkTeamData teamData = FTBChunksAPI.api().getManager().getOrCreateData(team);
			ServerPlayer player = manager.getServer().getPlayerList().getPlayer(profile.id());
			if (player != null) {
				teamData.checkMemberForceLoading(player.getUUID());
			}
		});
	}
}