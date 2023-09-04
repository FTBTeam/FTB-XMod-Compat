package dev.ftb.mods.ftbxmodcompat.ftbquests.kubejs;

import dev.ftb.mods.ftbquests.events.ObjectStartedEvent;
import dev.ftb.mods.ftbquests.quest.QuestObject;
import dev.ftb.mods.ftbquests.quest.ServerQuestFile;
import dev.latvian.mods.kubejs.player.EntityArrayList;
import dev.latvian.mods.kubejs.server.ServerEventJS;
import dev.latvian.mods.kubejs.util.UtilsJS;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.Nullable;

/**
 * @author LatvianModder
 */
public class QuestObjectStartedEventJS extends ServerEventJS {
	public final ObjectStartedEvent<?> event;
	private final FTBQuestsKubeJSTeamDataWrapper wrapper;

	public QuestObjectStartedEventJS(ObjectStartedEvent<?> e) {
		super(UtilsJS.staticServer);
		event = e;
		wrapper = new FTBQuestsKubeJSTeamDataWrapper(event.getData());
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
