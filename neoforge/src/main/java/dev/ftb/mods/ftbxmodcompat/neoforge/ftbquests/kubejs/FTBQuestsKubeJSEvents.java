package dev.ftb.mods.ftbxmodcompat.neoforge.ftbquests.kubejs;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;
import dev.latvian.mods.kubejs.event.EventTargetType;
import dev.latvian.mods.kubejs.event.TargetedEventHandler;

public interface FTBQuestsKubeJSEvents {
	EventGroup EVENT_GROUP = EventGroup.of("FTBQuestsEvents");

	TargetedEventHandler<String> CUSTOM_TASK = EVENT_GROUP.server("customTask", () -> CustomTaskKubeEvent.class)
			.supportsTarget(EventTargetType.STRING).hasResult();
	TargetedEventHandler<String> CUSTOM_REWARD = EVENT_GROUP.server("customReward", () -> CustomRewardKubeEvent.class)
			.supportsTarget(EventTargetType.STRING).hasResult();
	TargetedEventHandler<String> OBJECT_COMPLETED = EVENT_GROUP.server("completed", () -> QuestObjectCompletedKubeEvent.class)
			.supportsTarget(EventTargetType.STRING);
	TargetedEventHandler<String> OBJECT_STARTED = EVENT_GROUP.server("started", () -> QuestObjectStartedKubeEvent.class)
			.supportsTarget(EventTargetType.STRING);

	EventHandler CUSTOM_FILTER_ITEM = EVENT_GROUP.client("customFilterItem", () -> CustomFilterItemKubeEvent.class);
}
