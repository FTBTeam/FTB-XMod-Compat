package dev.ftb.mods.ftbxmodcompat.ftbquests.recipemod_common;

import com.google.common.collect.ImmutableList;
import dev.ftb.mods.ftblibrary.icon.ItemIcon;
import dev.ftb.mods.ftbquests.client.ClientQuestFile;
import dev.ftb.mods.ftbquests.integration.item_filtering.ItemMatchingSystem;
import dev.ftb.mods.ftbquests.quest.Quest;
import dev.ftb.mods.ftbquests.quest.loot.RewardTable;
import dev.ftb.mods.ftbquests.quest.loot.WeightedReward;
import dev.ftb.mods.ftbquests.quest.reward.RandomReward;
import dev.ftb.mods.ftbquests.quest.reward.Reward;
import dev.ftb.mods.ftbquests.quest.task.ItemTask;
import dev.ftb.mods.ftbquests.quest.task.Task;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class WrappedQuest {
    public final Quest quest;
    public final List<List<ItemStack>> input;
    public final List<List<ItemStack>> output;

    public WrappedQuest(Quest q, List<Reward> rewards) {
        quest = q;

        input = new ArrayList<>(5);
        output = new ArrayList<>(5);

        if (quest.getTasks().size() == 1) {
            // padding to center the ingredient in a 3x3 grid
            input.add(Collections.emptyList());
            input.add(Collections.emptyList());
            input.add(Collections.emptyList());
            input.add(Collections.emptyList());
        }

        for (Task task : quest.getTasks()) {
            if (task instanceof ItemTask itemTask) {
                input.add(Collections.singletonList(itemTask.getItemStack()));
            } else {
                Object object = task.getIcon().getIngredient();
                ItemStack stack = object instanceof ItemStack ? (ItemStack) object : ItemStack.EMPTY;

                if (!stack.isEmpty()) {
                    // TODO using non-API access to FTBQ here
                    input.add(List.copyOf(ItemMatchingSystem.INSTANCE.getAllMatchingStacks(stack)));
                } else if (task.getIcon() instanceof ItemIcon itemIcon) {
                    stack = itemIcon.getStack().copy();
                    stack.set(DataComponents.CUSTOM_NAME, task.getTitle());
                    input.add(Collections.singletonList(stack));
                } else {
                    stack = new ItemStack(Items.PAINTING);
                    stack.set(DataComponents.CUSTOM_NAME, task.getTitle());
//                    stack.addTagElement("icon", StringTag.valueOf(task.getIcon().toString()));
                    input.add(Collections.singletonList(stack));
                }
            }
        }

        if (rewards.size() == 1) {
            // padding to center the ingredient in a 3x3 grid
            output.add(Collections.emptyList());
            output.add(Collections.emptyList());
            output.add(Collections.emptyList());
            output.add(Collections.emptyList());
        }

        for (Reward reward : rewards) {
            Object object = reward.getIcon().getIngredient();
            ItemStack stack = ItemStack.EMPTY;
            if (object instanceof ItemStack s) {
                stack = s;
            } /*else if (object instanceof WrappedIngredient w && w.wrappedIngredient instanceof ItemStack s) {
                stack = s;
            }*/

            if (!stack.isEmpty()) {
                output.add(Collections.singletonList(stack.copy()));
            } else if (reward instanceof RandomReward r) {
                RewardTable table = r.getTable();
                if (table != null) {
                    ImmutableList.Builder<ItemStack> builder = ImmutableList.builder();
                    if (table.shouldShowTooltip()) {
                        ItemStack unknown = new ItemStack(Items.BARRIER);
                        unknown.set(DataComponents.CUSTOM_NAME, Component.literal("Unknown Reward"));
                        builder.add(unknown);
                    } else {
                        for (WeightedReward wr : table.getWeightedRewards()) {
                            if (wr.getReward().getIcon().getIngredient() instanceof ItemStack s) {
                                builder.add(s);
                            }
                        }
                    }
                    output.add(builder.build());
                }
            } else if (reward.getIcon() instanceof ItemIcon itemIcon) {
                stack = itemIcon.getStack().copy();
                stack.set(DataComponents.CUSTOM_NAME, reward.getTitle());
                output.add(Collections.singletonList(stack));
            } else {
                stack = new ItemStack(Items.PAINTING);
                stack.set(DataComponents.CUSTOM_NAME, reward.getTitle());
//                stack.addTagElement("icon", StringTag.valueOf(reward.getIcon().toString()));
                output.add(Collections.singletonList(stack));
            }
        }
    }

    public List<Ingredient> inputIngredients() {
        return input.stream().map(items -> Ingredient.of(items.toArray(new ItemStack[0]))).toList();
    }

    public List<Ingredient> outputIngredients() {
        return output.stream().map(items -> Ingredient.of(items.toArray(new ItemStack[0]))).toList();
    }

	public boolean hasInput(ItemStack stack) {
        return input.stream().flatMap(Collection::stream).anyMatch(stack1 -> ItemStack.isSameItemSameComponents(stack1, stack));
	}

	public boolean hasOutput(ItemStack stack) {
        return output.stream().flatMap(Collection::stream).anyMatch(stack1 -> ItemStack.isSameItemSameComponents(stack1, stack));
	}

    public void openQuestGui() {
        ClientQuestFile.openGui(quest, true);
    }
}