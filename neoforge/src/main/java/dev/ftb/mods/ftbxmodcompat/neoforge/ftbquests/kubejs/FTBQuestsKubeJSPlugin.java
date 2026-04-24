package dev.ftb.mods.ftbxmodcompat.neoforge.ftbquests.kubejs;

import dev.ftb.mods.ftbquests.api.event.CustomFilterDisplayItemsEvent;
import dev.ftb.mods.ftbquests.api.event.CustomRewardEvent;
import dev.ftb.mods.ftbquests.api.event.CustomTaskEvent;
import dev.ftb.mods.ftbquests.api.event.progress.ProgressEventData;
import dev.ftb.mods.ftbquests.api.event.progress.ProgressType;
import dev.ftb.mods.ftbquests.api.event.progress.TaskProgressEvent;
import dev.ftb.mods.ftbquests.api.neoforge.FTBQuestsClientEvent;
import dev.ftb.mods.ftbquests.api.neoforge.FTBQuestsEvent;
import dev.ftb.mods.ftbquests.quest.QuestObject;
import dev.ftb.mods.ftbquests.quest.QuestObjectBase;
import dev.ftb.mods.ftbquests.quest.ServerQuestFile;
import dev.ftb.mods.ftbxmodcompat.FTBXModCompat;
import dev.latvian.mods.kubejs.event.EventGroupRegistry;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingRegistry;
import dev.latvian.mods.kubejs.script.ScriptType;
import dev.latvian.mods.kubejs.util.AttachedData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.NeoForge;

public class FTBQuestsKubeJSPlugin implements KubeJSPlugin {
	@Override
	public void init() {
		NeoForge.EVENT_BUS.addListener(FTBQuestsEvent.CustomTask.class,
				event -> onCustomTask(event.getEventData()));
		NeoForge.EVENT_BUS.addListener(FTBQuestsEvent.CustomReward.class,
				event -> onCustomReward(event.getEventData()));
		NeoForge.EVENT_BUS.addListener(FTBQuestsEvent.TaskProgress.class,
				event -> onProgress(event.getEventData()));
		NeoForge.EVENT_BUS.addListener(FTBQuestsClientEvent.CustomFilterDisplayItems.class,
				event -> onCustomFilterItem(event.getEventData()));

//		Stages.added(event -> {
//			if (event.getPlayer() instanceof ServerPlayer sp) StageTask.checkStages(sp);
//		});
//		Stages.removed(event -> {
//			if (event.getPlayer() instanceof ServerPlayer sp) StageTask.checkStages(sp);
//		});

		FTBXModCompat.LOGGER.info("[FTB Quests] Enabled KubeJS integration");
	}

	@Override
	public void registerBindings(BindingRegistry bindings) {
		bindings.add("FTBQuests", FTBQuestsKubeJSWrapper.INSTANCE);
	}

	@Override
	public void attachPlayerData(AttachedData<Player> event) {
		event.add("ftbquests", new FTBQuestsKubeJSPlayerData(event.getParent()));
	}

	@Override
	public void registerEvents(EventGroupRegistry registry) {
		registry.register(FTBQuestsKubeJSEvents.EVENT_GROUP);
	}

	private static void onCustomFilterItem(CustomFilterDisplayItemsEvent.Data event) {
		FTBQuestsKubeJSEvents.CUSTOM_FILTER_ITEM.post(ScriptType.CLIENT, new CustomFilterItemKubeEvent(event));
	}

	public static void onCustomTask(CustomTaskEvent.Data event) {
		FTBQuestsKubeJSEvents.CUSTOM_TASK.post(ScriptType.SERVER, event.task().toString(), new CustomTaskKubeEvent(event));
	}

	public static void onCustomReward(CustomRewardEvent.Data event) {
		FTBQuestsKubeJSEvents.CUSTOM_REWARD.post(ScriptType.SERVER, event.reward().toString(), new CustomRewardKubeEvent(event));
	}

	private void onProgress(TaskProgressEvent.Data eventData) {
		handleProgressEvent(eventData.type(), eventData.progressData());
	}

	private void handleProgressEvent(ProgressType type, ProgressEventData<? extends QuestObject> progressData) {
		QuestObject qo = progressData.object();
		if (qo.getQuestFile().isServerSide()) {
			if (type == ProgressType.COMPLETED) {
				var kjsEvent = new QuestObjectProgressKubeEvent.Completed(progressData);
				FTBQuestsKubeJSEvents.OBJECT_COMPLETED.post(ScriptType.SERVER, qo.toString(), kjsEvent);
				for (String tag : qo.getTags()) {
					FTBQuestsKubeJSEvents.OBJECT_COMPLETED.post(ScriptType.SERVER, '#' + tag, kjsEvent);
				}
			} else {
				var kjsEvent = new QuestObjectProgressKubeEvent.Started(progressData);
				FTBQuestsKubeJSEvents.OBJECT_STARTED.post(ScriptType.SERVER, qo.toString(), kjsEvent);
				for (String tag : qo.getTags()) {
					FTBQuestsKubeJSEvents.OBJECT_STARTED.post(ScriptType.SERVER, '#' + tag, kjsEvent);
				}
			}
		}
	}

	static MinecraftServer getServer(QuestObjectBase qo) {
		if (qo.getQuestFile() instanceof ServerQuestFile sqf) {
			return sqf.server;
		}
		throw new IllegalStateException("only use this on the server!");
	}
}