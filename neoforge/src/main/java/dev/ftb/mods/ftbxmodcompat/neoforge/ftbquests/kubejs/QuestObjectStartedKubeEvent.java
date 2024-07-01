package dev.ftb.mods.ftbxmodcompat.neoforge.ftbquests.kubejs;

import dev.ftb.mods.ftbquests.events.ObjectStartedEvent;
import dev.ftb.mods.ftbquests.quest.QuestObject;
import dev.ftb.mods.ftbquests.quest.ServerQuestFile;
import dev.latvian.mods.kubejs.player.EntityArrayList;
import dev.latvian.mods.kubejs.server.ServerKubeEvent;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.Nullable;

public class QuestObjectStartedKubeEvent extends ServerKubeEvent {
	public final ObjectStartedEvent<?> event;
	private final FTBQuestsKubeJSTeamDataWrapper wrapper;

	public QuestObjectStartedKubeEvent(ObjectStartedEvent<?> event) {
		super(FTBQuestsKubeJSPlugin.getServer(event.getObject()));

		this.event = event;
		wrapper = new FTBQuestsKubeJSTeamDataWrapper(this.event.getData());
	}

	public FTBQuestsKubeJSTeamDataWrapper getData() {
		return wrapper;
	}

	public QuestObject getObject() {
		return event.getObject();
	}

	public EntityArrayList getNotifiedPlayers() {
		return new EntityArrayList(server.overworld(), event.getNotifiedPlayers());
	}

	public EntityArrayList getOnlineMembers() {
		return getData().getOnlineMembers();
	}

	@Nullable
	public ServerPlayer getPlayer() {
		return event.getData().getFile() instanceof ServerQuestFile file ? file.getCurrentPlayer() : null;
	}
}
