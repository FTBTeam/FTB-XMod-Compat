package dev.ftb.mods.ftbxmodcompat;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

public class ClientUtil {
    public static Player getClientPlayer() {
        return Minecraft.getInstance().player;
    }
}
