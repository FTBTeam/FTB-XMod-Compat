package dev.ftb.mods.ftbxmodcompat.neoforge.ftbquests.kubejs;

import dev.ftb.mods.ftbquests.api.event.progress.ProgressEventData;
import dev.ftb.mods.ftbquests.quest.QuestObject;
import dev.ftb.mods.ftbquests.quest.ServerQuestFile;
import dev.latvian.mods.kubejs.player.EntityArrayList;
import dev.latvian.mods.kubejs.server.ServerKubeEvent;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.Nullable;

public abstract class QuestObjectProgressKubeEvent extends ServerKubeEvent {
	public final ProgressEventData<? extends QuestObject> progressEventData;
	private final FTBQuestsKubeJSTeamDataWrapper wrapper;

	public QuestObjectProgressKubeEvent(ProgressEventData<? extends QuestObject> progressEventData) {
		super(FTBQuestsKubeJSPlugin.getServer(progressEventData.object()));

		this.progressEventData = progressEventData;
		wrapper = new FTBQuestsKubeJSTeamDataWrapper(progressEventData.teamData());
	}

	public FTBQuestsKubeJSTeamDataWrapper getData() {
		return wrapper;
	}

	public QuestObject getObject() {
		return progressEventData.object();
	}

	public EntityArrayList getNotifiedPlayers() {
		return new EntityArrayList(progressEventData.notifiedPlayers());
	}

	public EntityArrayList getOnlineMembers() {
		return getData().getOnlineMembers();
	}

	@Nullable
	public ServerPlayer getPlayer() {
		return progressEventData.teamData().getFile() instanceof ServerQuestFile file ? file.getCurrentPlayer() : null;
	}

	public static class Started extends QuestObjectProgressKubeEvent {
		public Started(ProgressEventData<? extends QuestObject> progressEventData) {
			super(progressEventData);
		}
	}

	public static class Completed extends QuestObjectProgressKubeEvent {
		public Completed(ProgressEventData<? extends QuestObject> progressEventData) {
			super(progressEventData);
		}
	}
}
