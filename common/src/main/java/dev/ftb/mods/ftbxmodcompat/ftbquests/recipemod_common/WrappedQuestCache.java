package dev.ftb.mods.ftbxmodcompat.ftbquests.recipemod_common;

import dev.ftb.mods.ftbquests.client.ClientQuestFile;
import dev.ftb.mods.ftbquests.quest.Chapter;
import dev.ftb.mods.ftbquests.quest.ChapterGroup;
import dev.ftb.mods.ftbquests.quest.Quest;
import dev.ftb.mods.ftbquests.quest.reward.Reward;
import dev.ftb.mods.ftbquests.quest.reward.RewardAutoClaim;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class WrappedQuestCache {
    private final List<WrappedQuest> wrappedQuestsCache = new ArrayList<>();
    private final ItemStackToListCache<WrappedQuest> inputCache = new ItemStackToListCache<>();
    private final ItemStackToListCache<WrappedQuest> outputCache = new ItemStackToListCache<>();
    private boolean needsRefresh = true;

    public List<WrappedQuest> getCachedItems() {
        if (needsRefresh) {
            rebuildWrappedQuestCache();
            needsRefresh = false;
        }
        return wrappedQuestsCache;
    }

    /**
     * Called whenever client quests are updated (either by GUI or by sync from server)
     */
    public void clear() {
        needsRefresh = true;

        inputCache.clear();
        outputCache.clear();
    }

    private void rebuildWrappedQuestCache() {
        wrappedQuestsCache.clear();

        if (ClientQuestFile.exists()) {
            for (ChapterGroup group : ClientQuestFile.INSTANCE.chapterGroups) {
                for (Chapter chapter : group.chapters) {
                    for (Quest quest : chapter.getQuests()) {
                        if (ClientQuestFile.INSTANCE.self.canStartTasks(quest) && !quest.rewards.isEmpty() && !quest.disableJEI.get(ClientQuestFile.INSTANCE.defaultQuestDisableJEI)) {
                            List<Reward> rewards = quest.rewards.stream()
                                    .filter(reward -> reward.getAutoClaimType() != RewardAutoClaim.INVISIBLE && reward.getIngredient() != null)
                                    .toList();
                            if (!rewards.isEmpty()) {
                                wrappedQuestsCache.add(new WrappedQuest(quest, rewards));
                            }
                        }
                    }
                }
            }
        }
    }

    public List<WrappedQuest> findQuestsWithInput(ItemStack stack) {
        return inputCache.getList(stack, k -> getCachedItems().stream().filter(q -> q.hasInput(stack)).toList());
    }

    public List<WrappedQuest> findQuestsWithOutput(ItemStack stack) {
        return outputCache.getList(stack, k -> getCachedItems().stream().filter(q -> q.hasOutput(stack)).toList());
    }
}
