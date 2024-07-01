package dev.ftb.mods.ftbxmodcompat.neoforge.ftbfiltersystem.kubejs;

import dev.latvian.mods.kubejs.event.KubeEvent;
import net.minecraft.world.item.ItemStack;

public record CustomFilterKubeEvent(ItemStack stack, String data) implements KubeEvent {
    // getter methods are for backward compat with 1.20

    public ItemStack getStack() {
        return stack;
    }

    public String getData() {
        return data;
    }
}
