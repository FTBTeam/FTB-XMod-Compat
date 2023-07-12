package dev.ftb.mods.ftbxmodcompat.ftbquests.rei;

import com.google.common.collect.Lists;
import dev.ftb.mods.ftbquests.item.FTBQuestsItems;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.entry.type.VanillaEntryTypes;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
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
        return EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(FTBQuestsItems.BOOK.get()));
    }

    @Override
    public List<Widget> setupDisplay(QuestDisplay display, Rectangle bounds) {
        List<Widget> widgets = Lists.newArrayList();

        widgets.add(Widgets.createRecipeBase(bounds));

        int inputSize = Math.min(9, display.getInputEntries().size());
        for (int i = 0; i < inputSize; i++) {
            widgets.add(Widgets.createSlot(new Point((i % 3) * 18 + 1, (i / 3) * 18 + 21))
                    .entries(display.getInputEntries().get(i))
                    .markInput());
        }

        int outputSize = Math.min(9, display.getOutputEntries().size());
        for (int i = 0; i < outputSize; i++) {
            widgets.add(Widgets.createSlot(new Point((i % 3) * 18 + (5 * 18) + 1, (i / 3) * 18 + 21))
                    .entries(display.getOutputEntries().get(i))
                    .markOutput());
        }

        widgets.add(Widgets.createArrow(new Point(73, 48)));

        Component text = display.getWrappedQuest().quest.getTitle().copy().withStyle(ChatFormatting.UNDERLINE);
        Font font = Minecraft.getInstance().font;
        int w = font.width(text);
        float x = (getDisplayWidth(display) - w) / 2f;
        float y = 3f;
        widgets.add(Widgets.createClickableLabel(new Point(x, y), text, c -> display.getWrappedQuest().openQuestGui()));

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 144;
    }

    @Override
    public int getDisplayWidth(QuestDisplay display) {
        return 74;
    }
}
