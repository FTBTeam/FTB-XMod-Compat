package dev.ftb.mods.ftbxmodcompat.neoforge.ftbfiltersystem.kubejs;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventTargetType;
import dev.latvian.mods.kubejs.event.TargetedEventHandler;

public interface FFSEvents {
    EventGroup EVENT_GROUP = EventGroup.of("FTBFilterSystemEvents");

    TargetedEventHandler<String> CUSTOM_FILTER = EVENT_GROUP.server("customFilter", () -> CustomFilterKubeEvent.class)
            .supportsTarget(EventTargetType.STRING).hasResult();
}
