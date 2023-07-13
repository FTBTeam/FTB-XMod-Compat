package dev.ftb.mods.ftbxmodcompat.ftbquests.rei;

import dev.ftb.mods.ftbxmodcompat.ftbquests.recipemod_common.WrappedQuestCache;
import me.shedaniel.rei.api.client.registry.display.DynamicDisplayGenerator;
import me.shedaniel.rei.api.client.view.ViewSearchBuilder;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.entry.type.VanillaEntryTypes;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Optional;

public enum QuestDisplayGenerator implements DynamicDisplayGenerator<QuestDisplay> {
    INSTANCE;

    final WrappedQuestCache cache = new WrappedQuestCache();

    public void refresh() {
        cache.clear();
    }

    @Override
    public Optional<List<QuestDisplay>> getRecipeFor(EntryStack<?> entry) {
        if (entry.getType() == VanillaEntryTypes.ITEM) {
            EntryStack<ItemStack> itemEntry = entry.cast();
            return Optional.of(cache.findQuestsWithOutput(itemEntry.getValue()).stream().map(QuestDisplay::new).toList());
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<QuestDisplay>> getUsageFor(EntryStack<?> entry) {
        if (entry.getType() == VanillaEntryTypes.ITEM) {
            EntryStack<ItemStack> itemEntry = entry.cast();
            return Optional.of(cache.findQuestsWithInput(itemEntry.getValue()).stream().map(QuestDisplay::new).toList());
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<QuestDisplay>> generate(ViewSearchBuilder builder) {
        // TODO seems to work OK, but could do with verifying what exactly this method is for...
        return Optional.empty();
    }
}
