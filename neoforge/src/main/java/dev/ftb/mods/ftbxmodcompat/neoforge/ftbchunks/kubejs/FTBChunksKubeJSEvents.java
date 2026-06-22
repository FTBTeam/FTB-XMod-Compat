package dev.ftb.mods.ftbxmodcompat.neoforge.ftbchunks.kubejs;

import dev.ftb.mods.ftbchunks.api.event.ChunkChangeEvent;
import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;
import dev.latvian.mods.kubejs.event.EventTargetType;
import dev.latvian.mods.kubejs.event.TargetedEventHandler;

public interface FTBChunksKubeJSEvents {
    EventGroup EVENT_GROUP = EventGroup.of("FTBChunksEvents");

    EventTargetType<ChunkChangeEvent.Operation> OPERATION = EventTargetType.fromEnum(ChunkChangeEvent.Operation.class);

    TargetedEventHandler<ChunkChangeEvent.Operation> BEFORE = EVENT_GROUP.server("before", () -> BeforeEventJS.class)
            .requiredTarget(OPERATION).hasResult();
    TargetedEventHandler<ChunkChangeEvent.Operation> AFTER = EVENT_GROUP.server("after", () -> AfterEventJS.class)
            .requiredTarget(OPERATION);
}
