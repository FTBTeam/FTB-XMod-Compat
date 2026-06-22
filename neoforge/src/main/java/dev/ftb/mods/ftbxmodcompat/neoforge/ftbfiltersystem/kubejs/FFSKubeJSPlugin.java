package dev.ftb.mods.ftbxmodcompat.neoforge.ftbfiltersystem.kubejs;

import dev.ftb.mods.ftbfiltersystem.api.event.CustomFilterEvent;
import dev.ftb.mods.ftbfiltersystem.api.neoforge.FTBFilterSystemEvent;
import dev.ftb.mods.ftblibrary.util.result.Outcome;
import dev.latvian.mods.kubejs.event.EventGroupRegistry;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.ScriptType;
import net.neoforged.neoforge.common.NeoForge;

public class FFSKubeJSPlugin implements KubeJSPlugin {
    @Override
    public void init() {
        NeoForge.EVENT_BUS.addListener(FTBFilterSystemEvent.CustomFilter.class,
                event -> event.setOutcome(onCustomFilter(event.getEventData()))
        );
    }

    @Override
    public void registerEvents(EventGroupRegistry registry) {
        registry.register(FFSEvents.EVENT_GROUP);
    }

    private static Outcome onCustomFilter(CustomFilterEvent.Data eventData) {
        var result = FFSEvents.CUSTOM_FILTER.post(ScriptType.SERVER, eventData.id(), new CustomFilterKubeEvent(eventData.stack(), eventData.extraData()));
        if (result.pass()) {
            return Outcome.PASS;
        } else if (result.interruptTrue()) {
            return Outcome.SUCCESS;
        } else {
            return Outcome.FAIL;
        }
    }
}
