package dev.ftb.mods.ftbxmodcompat.ftbquests.kubejs;

import dev.ftb.mods.ftbquests.events.CustomTaskEvent;
import dev.ftb.mods.ftbquests.quest.task.CustomTask;
import dev.latvian.mods.kubejs.event.EventJS;

/**
 * @author LatvianModder
 */
public class CustomTaskEventJS extends EventJS {
	public final CustomTaskEvent event;

	CustomTaskEventJS(CustomTaskEvent e) {
		event = e;
	}

	public CustomTask getTask() {
		return event.getTask();
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