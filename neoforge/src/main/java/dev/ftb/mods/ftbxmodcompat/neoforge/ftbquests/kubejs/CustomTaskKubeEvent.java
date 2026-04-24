package dev.ftb.mods.ftbxmodcompat.neoforge.ftbquests.kubejs;

import dev.ftb.mods.ftbquests.api.event.CustomTaskEvent;
import dev.ftb.mods.ftbquests.quest.task.CustomTask;
import dev.latvian.mods.kubejs.event.KubeEvent;

public class CustomTaskKubeEvent implements KubeEvent {
	public final CustomTaskEvent.Data event;

	CustomTaskKubeEvent(CustomTaskEvent.Data e) {
		event = e;
	}

	public CustomTask getTask() {
		return event.task();
	}

	public void setCheck(CustomTask.Check c) {
		getTask().setCheck(c);
	}

	public void setCheckTimer(int t) {
		getTask().setCheckTimer(t);
	}

	public void setEnableButton(boolean b) {
		getTask().setEnableButton(b);
	}

	public void setMaxProgress(long max) {
		getTask().setMaxProgress(max);
	}
}