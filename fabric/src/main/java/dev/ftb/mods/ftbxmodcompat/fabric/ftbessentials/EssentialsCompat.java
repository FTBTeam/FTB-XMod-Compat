package dev.ftb.mods.ftbxmodcompat.fabric.ftbessentials;

import dev.ftb.mods.ftbessentials.fabric.FTBEssentialsEvents;
import dev.ftb.mods.ftbxmodcompat.ftbchunks.ftbessentials.EssentialsListener;

public class EssentialsCompat {
    public static void init() {
        FTBEssentialsEvents.RTP.register(EssentialsListener::onRTP);
    }
}
