package dev.ftb.mods.ftbxmodcompat.generic.jade;

import dev.ftb.mods.ftbxmodcompat.FTBXModCompat;
import dev.ftb.mods.ftbxmodcompat.ftbquests.jade.JadeQuestsSetup;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class JadePlugin implements IWailaPlugin {
    @Override
    public void registerClient(IWailaClientRegistration registration) {
        if (FTBXModCompat.isFTBQuestsLoaded) {
            JadeQuestsSetup.register(registration);
        }
    }
}
