package dev.ftb.mods.ftbxmodcompat.ftbchunks.ftbranks;

import com.mojang.authlib.GameProfile;
import dev.ftb.mods.ftbchunks.data.FTBChunksAPI;
import dev.ftb.mods.ftbchunks.data.FTBChunksTeamData;
import dev.ftb.mods.ftbchunks.integration.PermissionsHelper;
import dev.ftb.mods.ftbranks.api.RankManager;
import dev.ftb.mods.ftbranks.api.event.*;
import dev.ftb.mods.ftbteams.FTBTeamsAPI;
import dev.ftb.mods.ftbteams.data.Team;
import dev.ftb.mods.ftbxmodcompat.FTBXModCompat;
import net.minecraft.server.level.ServerPlayer;

import static dev.ftb.mods.ftbchunks.integration.PermissionsHelper.*;

public class FTBRanksEventHandler {
	public static void registerEvents() {
		RankEvent.ADD_PLAYER.register(FTBRanksEventHandler::playerAdded);
		RankEvent.REMOVE_PLAYER.register(FTBRanksEventHandler::playerRemoved);
		RankEvent.PERMISSION_CHANGED.register(FTBRanksEventHandler::permissionSet);
		RankEvent.RELOADED.register(FTBRanksEventHandler::ranksReloaded);
		RankEvent.CONDITION_CHANGED.register(FTBRanksEventHandler::conditionChanged);

		FTBXModCompat.LOGGER.info("[FTB Chunks] FTB Ranks detected, listening for ranks events");
	}

	private static void playerAdded(PlayerAddedToRankEvent event) {
		updateForPlayer(event.getManager(), event.getPlayer());
	}

	private static void playerRemoved(PlayerRemovedFromRankEvent event) {
		updateForPlayer(event.getManager(), event.getPlayer());
	}

	private static void permissionSet(PermissionNodeChangedEvent event) {
		String node = event.getNode();
		if (node.equals(MAX_CLAIMED_PERM) || node.equals(MAX_FORCE_LOADED_PERM) || node.equals(CHUNK_LOAD_OFFLINE_PERM)) {
			updateAll(event.getManager());
		}
	}

	private static void ranksReloaded(RanksReloadedEvent event) {
		updateAll(event.getManager());
	}

	private static void conditionChanged(ConditionChangedEvent event) {
		updateAll(event.getManager());
	}

	private static void updateAll(RankManager manager) {
		if (FTBChunksAPI.isManagerLoaded()) {
			manager.getServer().getPlayerList().getPlayers().forEach(player -> {
				FTBChunksTeamData data = FTBChunksAPI.getManager().getData(player);
				data.setForceLoadMember(player.getUUID(), PermissionsHelper.getChunkLoadOffline(player, false));
			});
			FTBTeamsAPI.getManager().getTeams().forEach(team -> FTBChunksAPI.getManager().getData(team).updateLimits());
		}
	}

	private static void updateForPlayer(RankManager manager, GameProfile profile) {
		Team team = FTBTeamsAPI.getPlayerTeam(profile.getId());
		if (team != null) {
			FTBChunksTeamData teamData = FTBChunksAPI.getManager().getData(team);
			ServerPlayer player = manager.getServer().getPlayerList().getPlayer(profile.getId());
			if (player != null) {
				teamData.setForceLoadMember(player.getUUID(), PermissionsHelper.getChunkLoadOffline(player, false));
			}
			teamData.updateLimits();
		}
	}
}