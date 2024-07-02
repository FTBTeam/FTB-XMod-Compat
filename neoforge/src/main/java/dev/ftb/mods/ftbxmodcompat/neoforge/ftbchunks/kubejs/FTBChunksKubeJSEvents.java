package dev.ftb.mods.ftbxmodcompat.neoforge.ftbchunks.kubejs;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;
import dev.latvian.mods.kubejs.event.EventTargetType;
import dev.latvian.mods.kubejs.event.TargetedEventHandler;

public interface FTBChunksKubeJSEvents {
    EventGroup EVENT_GROUP = EventGroup.of("FTBChunksEvents");

    TargetedEventHandler<String> BEFORE = EVENT_GROUP.server("before", () -> BeforeEventJS.class)
            .requiredTarget(EventTargetType.STRING).hasResult();
    TargetedEventHandler<String> AFTER = EVENT_GROUP.server("after", () -> AfterEventJS.class)
            .requiredTarget(EventTargetType.STRING);
}
