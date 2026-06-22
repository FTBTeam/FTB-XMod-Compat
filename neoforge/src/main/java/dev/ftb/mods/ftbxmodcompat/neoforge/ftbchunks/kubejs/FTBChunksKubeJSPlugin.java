package dev.ftb.mods.ftbxmodcompat.neoforge.ftbchunks.kubejs;

import dev.ftb.mods.ftbchunks.api.ClaimResult;
import dev.ftb.mods.ftbchunks.api.event.ChunkChangeEvent;
import dev.ftb.mods.ftbchunks.api.neoforge.FTBChunksEvent;
import dev.ftb.mods.ftbxmodcompat.FTBXModCompat;
import dev.latvian.mods.kubejs.event.EventGroupRegistry;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.ScriptType;
import dev.latvian.mods.kubejs.script.TypeWrapperRegistry;
import net.neoforged.neoforge.common.NeoForge;

public class FTBChunksKubeJSPlugin implements KubeJSPlugin {
	@Override
	public void init() {
		NeoForge.EVENT_BUS.addListener(FTBChunksEvent.ChunkChange.Pre.class, this::before);
		NeoForge.EVENT_BUS.addListener(FTBChunksEvent.ChunkChange.Post.class, this::after);

		FTBXModCompat.LOGGER.info("[FTB Chunks] Enabled KubeJS integration");
	}

	@Override
	public void registerEvents(EventGroupRegistry registry) {
		registry.register(FTBChunksKubeJSEvents.EVENT_GROUP);
	}

	@Override
	public void registerTypeWrappers(TypeWrapperRegistry registry) {
		registry.register(ClaimResult.class, (_, from, _) -> ClaimResult.StandardProblem.valueOf(from.toString().toUpperCase()));
	}

	private void before(FTBChunksEvent.ChunkChange.Pre event) {
		ChunkChangeEvent.Pre.Data data = event.getEventData();
		BeforeEventJS kjsEvent = new BeforeEventJS(data.sourceStack(), data.claimedChunk());
		FTBChunksKubeJSEvents.BEFORE.post(ScriptType.SERVER, data.operation(), kjsEvent);
		event.setResult(kjsEvent.getResult()); // will cancel the event if not successful
    }

	private void after(FTBChunksEvent.ChunkChange.Post event) {
		ChunkChangeEvent.Post.Data data = event.getEventData();
		FTBChunksKubeJSEvents.AFTER.post(ScriptType.SERVER, data.operation(), new AfterEventJS(data.sourceStack(), data.claimedChunk()));
	}
}
