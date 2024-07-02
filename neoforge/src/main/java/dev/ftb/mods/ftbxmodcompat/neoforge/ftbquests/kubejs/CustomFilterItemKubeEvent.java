package dev.ftb.mods.ftbxmodcompat.neoforge.ftbquests.kubejs;

import dev.ftb.mods.ftbquests.api.event.CustomFilterDisplayItemsEvent;
import dev.latvian.mods.kubejs.event.KubeEvent;
import net.minecraft.world.item.ItemStack;

import java.util.Collection;

public class CustomFilterItemKubeEvent implements KubeEvent {
    private final CustomFilterDisplayItemsEvent event;

    public CustomFilterItemKubeEvent(CustomFilterDisplayItemsEvent event) {
        this.event = event;
    }

    public void addStack(ItemStack stack) {
        event.add(stack);
    }

    public void addStacks(Collection<ItemStack> stacks) {
        event.add(stacks);
    }
}
