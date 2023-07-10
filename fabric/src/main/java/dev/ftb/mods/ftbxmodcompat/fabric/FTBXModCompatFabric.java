package dev.ftb.mods.ftbxmodcompat.fabric;

import dev.ftb.mods.ftbxmodcompat.FTBXModCompat;
import net.fabricmc.api.ModInitializer;

public class FTBXModCompatFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        FTBXModCompat.init();
    }
}
