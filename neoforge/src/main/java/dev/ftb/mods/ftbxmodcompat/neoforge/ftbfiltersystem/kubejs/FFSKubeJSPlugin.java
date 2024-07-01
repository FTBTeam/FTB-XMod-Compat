package dev.ftb.mods.ftbxmodcompat.neoforge.ftbfiltersystem.kubejs;

import dev.architectury.event.EventResult;
import dev.ftb.mods.ftbfiltersystem.api.event.CustomFilterEvent;
import dev.ftb.mods.ftbxmodcompat.kubejs.KJSUtil;
import dev.latvian.mods.kubejs.event.EventGroupRegistry;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.ScriptType;
import net.minecraft.world.item.ItemStack;

public class FFSKubeJSPlugin implements KubeJSPlugin {
    @Override
    public void init() {
        CustomFilterEvent.MATCH_ITEM.register(FFSKubeJSPlugin::onCustomFilter);
    }

    @Override
    public void registerEvents(EventGroupRegistry registry) {
        registry.register(FFSEvents.EVENT_GROUP);
    }

    private static EventResult onCustomFilter(ItemStack stack, String eventId, String extraData) {
        return KJSUtil.asArchResult(FFSEvents.CUSTOM_FILTER.post(ScriptType.SERVER, eventId, new CustomFilterKubeEvent(stack, extraData)));
    }
}
