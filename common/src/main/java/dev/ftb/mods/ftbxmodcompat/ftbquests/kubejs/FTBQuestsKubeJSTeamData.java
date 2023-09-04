package dev.ftb.mods.ftbxmodcompat.ftbquests.kubejs;

import dev.ftb.mods.ftbquests.quest.*;
import dev.ftb.mods.ftbquests.quest.task.Task;
import dev.ftb.mods.ftbquests.util.ProgressChange;
import dev.latvian.mods.kubejs.player.EntityArrayList;
import dev.latvian.mods.kubejs.util.UtilsJS;
import net.minecraft.Util;

import java.util.function.Consumer;

public abstract class FTBQuestsKubeJSTeamData {
	public abstract BaseQuestFile getFile();

	public abstract TeamData getData();

	public boolean addProgress(Object id, long progress) {
		TeamData data = getData();
		Task task = data.getFile().getTask(data.getFile().getID(id));

		if (task != null && data.canStartTasks(task.getQuest())) {
			data.addProgress(task, progress);
			return true;
		}

		return false;
	}

	public void changeProgress(Object id, Consumer<ProgressChange> consumer) {
		TeamData data = getData();
		QuestObjectBase origin = data.getFile().getBase(data.getFile().getID(id));
		ProgressChange progressChange = new ProgressChange(data.getFile(), origin, Util.NIL_UUID);

		if (origin != null) {
			consumer.accept(progressChange);
			origin.forceProgressRaw(data, progressChange);
		}
	}

	public void reset(Object id) {
		changeProgress(id, progressChange -> {
		});
	}

	public void complete(Object id) {
		changeProgress(id, progressChange -> {
			progressChange.setReset(false);
		});
	}

	public boolean isCompleted(Object id) {
		TeamData data = getData();
		QuestObject object = data.getFile().get(data.getFile().getID(id));
		return object != null && data.isCompleted(object);
	}

	public boolean isStarted(Object id) {
		TeamData data = getData();
		QuestObject object = data.getFile().get(data.getFile().getID(id));
		return object != null && data.isStarted(object);
	}

	public boolean canStartQuest(Object id) {
		TeamData data = getData();
		Quest quest = data.getFile().getQuest(data.getFile().getID(id));
		return quest != null && data.canStartTasks(quest);
	}

	public int getRelativeProgress(Object id) {
		TeamData data = getData();
		QuestObject object = data.getFile().get(data.getFile().getID(id));
		return object != null ? data.getRelativeProgress(object) : 0;
	}

	public long getTaskProgress(Object id) {
		TeamData data = getData();
		return data.getProgress(data.getFile().getID(id));
	}

	public boolean getLocked() {
		TeamData data = getData();
		return data.isLocked();
	}

	public void setLocked(boolean v) {
		TeamData data = getData();
		data.setLocked(v);
	}

	public EntityArrayList getOnlineMembers() {
		return new EntityArrayList(UtilsJS.staticServer.overworld(), getData().getOnlineMembers());
	}
}
