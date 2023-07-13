package dev.ftb.mods.ftbxmodcompat.ftbquests.rei;

import dev.ftb.mods.ftbxmodcompat.ftbquests.recipemod_common.WrappedLootCrateCache;
import me.shedaniel.rei.api.client.registry.display.DynamicDisplayGenerator;
import me.shedaniel.rei.api.client.view.ViewSearchBuilder;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.entry.type.VanillaEntryTypes;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum LootCrateDisplayGenerator implements DynamicDisplayGenerator<LootCrateDisplay> {
    INSTANCE;

    private final WrappedLootCrateCache cache = new WrappedLootCrateCache(
            crates -> { },
            crates -> FTBQuestsREIIntegration.onLootCratesChanged()
    );

    @Override
    public Optional<List<LootCrateDisplay>> getRecipeFor(EntryStack<?> entry) {
        if (entry.getType() == VanillaEntryTypes.ITEM) {
            EntryStack<ItemStack> itemEntry = entry.cast();
            return Optional.of(cache.findCratesWithOutput(itemEntry.getValue()).stream().map(LootCrateDisplay::new).toList());
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<LootCrateDisplay>> getUsageFor(EntryStack<?> entry) {
        if (entry.getType() == VanillaEntryTypes.ITEM) {
            EntryStack<ItemStack> itemEntry = entry.cast();
            return Optional.of(cache.findCratesWithInput(itemEntry.getValue()).stream().map(LootCrateDisplay::new).toList());
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<LootCrateDisplay>> generate(ViewSearchBuilder builder) {
        return DynamicDisplayGenerator.super.generate(builder);
    }

    public Collection<EntryStack<?>> getCrateEntryStacks() {
        return cache.knownCrateStacks().stream()
                .map(itemStack -> EntryStack.of(VanillaEntryTypes.ITEM, itemStack))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void refresh() {
        cache.refresh();
    }
}
