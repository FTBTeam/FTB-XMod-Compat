package dev.ftb.mods.ftbxmodcompat.neoforge.ftbessentials;

import dev.ftb.mods.ftbessentials.api.neoforge.FTBEssentialsEvent;
import dev.ftb.mods.ftbxmodcompat.ftbchunks.ftbessentials.EssentialsListener;
import net.neoforged.neoforge.common.NeoForge;

public class EssentialsCompat {
    public static void init() {
        NeoForge.EVENT_BUS.addListener(FTBEssentialsEvent.RTP.class, event -> {
            if (!EssentialsListener.onRTP(event.getEventData()).isFail()) {
                event.setCanceled(true);
            }
        });
    }
}
