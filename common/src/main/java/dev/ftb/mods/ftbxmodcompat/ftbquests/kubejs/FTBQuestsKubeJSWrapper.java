package dev.ftb.mods.ftbxmodcompat.ftbquests.kubejs;

import dev.ftb.mods.ftbquests.FTBQuests;
import dev.ftb.mods.ftbquests.quest.*;
import dev.ftb.mods.ftbteams.api.FTBTeamsAPI;
import dev.ftb.mods.ftbteams.api.Team;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.UUID;

public class FTBQuestsKubeJSWrapper {
	public static final FTBQuestsKubeJSWrapper INSTANCE = new FTBQuestsKubeJSWrapper();

	public Map<String, QuestShape> getQuestShapes() {
		return QuestShape.map();
	}

	public Map<String, QuestObjectType> getQuestObjectTypes() {
		return QuestObjectType.NAME_MAP.map;
	}

	public QuestFile getFile(Level level) {
		return FTBQuests.getQuestFile(level.isClientSide);
	}

	public UUID getData(Level level, UUID uuid) {
		return FTBTeamsAPI.api().getManager().getTeamForPlayerID(uuid)
				.map(Team::getTeamId)
				.orElse(null);
	}

	@Nullable
	public TeamData getData(Player player) {
		return getFile(player.level()).getData(player);
	}

	@Nullable
	public QuestObjectBase getObject(Level level, Object id) {
		QuestFile file = getFile(level);
		return file.getBase(file.getID(id));
	}

	@Nullable
	public FTBQuestsKubeJSPlayerData getServerDataFromPlayer(Player player) {
		return null;

		// TODO if kubejs gets a 1.20 release
//		try {
//			return ((FTBQuestsKubeJSPlayerData) player.kjs$getData().get("ftbquests"));
//		} catch (Throwable e) {
//			return null;
//		}
	}

	@Nullable
	public FTBQuestsKubeJSPlayerData getServerDataFromSource(CommandSourceStack source) {
		try {
			return getServerDataFromPlayer(source.getPlayerOrException());
		} catch (Throwable e) {
			return null;
		}
	}
}
