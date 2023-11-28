package dev.ftb.mods.ftbxmodcompat.ftbquests.kubejs;

import dev.ftb.mods.ftbquests.api.event.CustomFilterDisplayItemsEvent;
import dev.latvian.mods.kubejs.event.EventJS;
import net.minecraft.world.item.ItemStack;

import java.util.Collection;

public class CustomFilterItemEventJS extends EventJS {
    private final CustomFilterDisplayItemsEvent event;

    public CustomFilterItemEventJS(CustomFilterDisplayItemsEvent event) {
        this.event = event;
    }

    public void addStack(ItemStack stack) {
        event.add(stack);
    }

    public void addStacks(Collection<ItemStack> stacks) {
        event.add(stacks);
    }
}
