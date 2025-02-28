package dev.ftb.mods.ftbxmodcompat.ftbquests.recipemod_common;

import dev.ftb.mods.ftbquests.client.ClientQuestFile;
import dev.ftb.mods.ftbquests.quest.reward.Reward;
import dev.ftb.mods.ftbquests.quest.reward.RewardAutoClaim;
import dev.ftb.mods.ftbxmodcompat.FTBXModCompat;
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
            ClientQuestFile file = ClientQuestFile.INSTANCE;
            file.forAllQuests(quest -> {
                if (file.selfTeamData.canStartTasks(quest) && quest.isSearchable(file.selfTeamData) && !quest.getRewards().isEmpty() && quest.showInRecipeMod()) {
                    List<Reward> rewards = quest.getRewards().stream()
                            .filter(reward -> reward.getAutoClaimType() != RewardAutoClaim.INVISIBLE && reward.getIcon().getIngredient() != null)
                            .toList();
                    if (!rewards.isEmpty()) {
                        wrappedQuestsCache.add(new WrappedQuest(quest, rewards));
                    }
                }
            });
        }
    }

    public List<WrappedQuest> findQuestsWithInput(ItemStack stack) {
        return inputCache.getList(stack, k -> getCachedItems().stream().filter(q -> q.hasInput(stack)).toList());
    }

    public List<WrappedQuest> findQuestsWithOutput(ItemStack stack) {
        return outputCache.getList(stack, k -> getCachedItems().stream().filter(q -> q.hasOutput(stack)).toList());
    }
}
