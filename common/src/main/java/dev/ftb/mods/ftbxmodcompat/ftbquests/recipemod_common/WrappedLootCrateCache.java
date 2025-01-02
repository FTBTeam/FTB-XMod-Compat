package dev.ftb.mods.ftbxmodcompat.ftbquests.recipemod_common;

import dev.ftb.mods.ftbquests.client.ClientQuestFile;
import dev.ftb.mods.ftbquests.quest.loot.RewardTable;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public class WrappedLootCrateCache {
    private final List<WrappedLootCrate> wrappedLootCratesCache = new ArrayList<>();
    private final ItemStackToListCache<WrappedLootCrate> inputCache = new ItemStackToListCache<>();
    private final ItemStackToListCache<WrappedLootCrate> outputCache = new ItemStackToListCache<>();

    private final List<ItemStack> crateStacks = new ArrayList<>();
    private final Consumer<List<ItemStack>> preRebuild;
    private final Consumer<List<ItemStack>> postRebuild;

    private boolean needsRefresh = true;

    public WrappedLootCrateCache(Consumer<List<ItemStack>> preRebuild, Consumer<List<ItemStack>> postRebuild) {
        this.preRebuild = preRebuild;
        this.postRebuild = postRebuild;
    }

    public List<WrappedLootCrate> getWrappedLootCrates() {
        if (needsRefresh) {
            rebuildWrappedLootCrateCache();
            needsRefresh = false;
        }
        return wrappedLootCratesCache;
    }

    private void rebuildWrappedLootCrateCache() {
        preRebuild.accept(List.copyOf(crateStacks));

        wrappedLootCratesCache.clear();
        crateStacks.clear();

        if (ClientQuestFile.exists()) {
            for (RewardTable table : ClientQuestFile.INSTANCE.getRewardTables()) {
                if (table.getLootCrate() != null) {
                    WrappedLootCrate wrapper = new WrappedLootCrate(table.getLootCrate());
                    wrappedLootCratesCache.add(wrapper);
                    crateStacks.add(table.getLootCrate().createStack());
                }
            }
        }

        postRebuild.accept(List.copyOf(crateStacks));
    }

    public void refresh() {
        needsRefresh = true;
        outputCache.clear();
        inputCache.clear();
    }

    public List<WrappedLootCrate> findCratesWithOutput(ItemStack stack) {
        return outputCache.getList(stack, k -> getWrappedLootCrates().stream()
                .filter(crate -> crate.outputs.stream().anyMatch(s1 -> s1.is(stack.getItem())))
                .toList()
        );
    }

    public List<WrappedLootCrate> findCratesWithInput(ItemStack stack) {
        return inputCache.getList(stack, k -> getWrappedLootCrates().stream()
                .filter(crate -> ItemStack.isSameItemSameTags(crate.crateStack, stack))
                .toList()
        );
    }

    public Collection<ItemStack> knownCrateStacks() {
        return crateStacks;
    }
}
