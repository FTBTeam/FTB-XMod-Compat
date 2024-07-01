package dev.ftb.mods.ftbxmodcompat.kubejs;

import dev.architectury.event.CompoundEventResult;
import dev.latvian.mods.kubejs.event.EventResult;

public class KJSUtil {
    public static <T> CompoundEventResult<T> asCompoundArchResult(EventResult result, T object) {
        return switch (result.type()) {
            case INTERRUPT_DEFAULT -> CompoundEventResult.interruptDefault(object);
            case INTERRUPT_FALSE -> CompoundEventResult.interruptFalse(object);
            case INTERRUPT_TRUE -> CompoundEventResult.interruptTrue(object);
            default -> CompoundEventResult.pass();
        };
    }

    public static dev.architectury.event.EventResult asArchResult(EventResult result) {
        return switch (result.type()) {
            case INTERRUPT_DEFAULT -> dev.architectury.event.EventResult.interruptDefault();
            case INTERRUPT_FALSE -> dev.architectury.event.EventResult.interruptFalse();
            case INTERRUPT_TRUE -> dev.architectury.event.EventResult.interruptTrue();
            default -> dev.architectury.event.EventResult.pass();
        };
    }
}
