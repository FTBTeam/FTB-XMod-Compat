package dev.ftb.mods.ftbxmodcompat.ftbquests.rei;

import com.google.common.collect.Lists;
import dev.ftb.mods.ftbquests.registry.ModItems;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.entry.type.VanillaEntryTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class QuestDisplayCategory implements DisplayCategory<QuestDisplay> {
    @Override
    public CategoryIdentifier<? extends QuestDisplay> getCategoryIdentifier() {
        return REICategories.QUEST;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("ftbquests.quests");
    }

    @Override
    public Renderer getIcon() {
        return EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(ModItems.BOOK.get()));
    }

    @Override
    public List<Widget> setupDisplay(QuestDisplay display, Rectangle bounds) {
        List<Widget> widgets = Lists.newArrayList();

        widgets.add(Widgets.createRecipeBase(bounds));

        int inputSize = Math.min(9, display.getInputEntries().size());
        for (int i = 0; i < 9; i++) {
            EntryIngredient ingredient = i < inputSize ? display.getInputEntries().get(i) : EntryIngredient.empty();
            widgets.add(Widgets.createSlot(new Point(bounds.x + (i % 3) * 18 + 8, bounds.y + (i / 3) * 18 + 20))
                    .entries(ingredient)
                    .markInput());
        }

        int outputSize = Math.min(9, display.getOutputEntries().size());
        for (int i = 0; i < 9; i++) {
            EntryIngredient ingredient = i < outputSize ? display.getOutputEntries().get(i) : EntryIngredient.empty();
            widgets.add(Widgets.createSlot(new Point(bounds.x + (i % 3) * 18 + 90, bounds.y + (i / 3) * 18 + 20))
                    .entries(ingredient)
                    .markOutput());
        }

        widgets.add(Widgets.createArrow(new Point(bounds.x + 60, bounds.y + 40)));

        Component text = display.getWrappedQuest().quest.getTitle().copy().withStyle(ChatFormatting.UNDERLINE);
        float x = bounds.x + bounds.width / 2f;
        float y = bounds.y + 5f;
        widgets.add(Widgets.createClickableLabel(new Point(x, y), text, c -> {
            Minecraft.getInstance().setScreen(null);  // avoid an infinite close-reopen loop between FTBQ and REI
            display.getWrappedQuest().openQuestGui();
        }));

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 80;
    }

}
