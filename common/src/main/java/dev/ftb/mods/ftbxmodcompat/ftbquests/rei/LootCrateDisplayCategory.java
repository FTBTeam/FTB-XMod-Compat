package dev.ftb.mods.ftbxmodcompat.ftbquests.rei;

import com.google.common.collect.Lists;
import dev.ftb.mods.ftbquests.quest.loot.WeightedReward;
import dev.ftb.mods.ftbquests.registry.ModItems;
import dev.ftb.mods.ftbxmodcompat.ftbquests.recipemod_common.LootCrateTextRenderer;
import dev.ftb.mods.ftbxmodcompat.ftbquests.recipemod_common.WrappedLootCrate;
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
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class LootCrateDisplayCategory implements DisplayCategory<LootCrateDisplay> {
    @Override
    public CategoryIdentifier<? extends LootCrateDisplay> getCategoryIdentifier() {
        return REICategories.LOOT_CRATE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.ftbquests.lootcrates");
    }

    @Override
    public Renderer getIcon() {
        return EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(ModItems.LOOTCRATE.get()));
    }

    @Override
    public List<Widget> setupDisplay(LootCrateDisplay display, Rectangle bounds) {
        List<Widget> widgets = Lists.newArrayList();

        widgets.add(Widgets.createRecipeBase(bounds));

        for (int slot = 0; slot < Math.min(WrappedLootCrate.ITEMS, display.getOutputEntries().size()); slot++) {
            int sx = bounds.x + (slot % WrappedLootCrate.ITEMSX) * 18 + 8;
            int sy = bounds.y + (slot / WrappedLootCrate.ITEMSX) * 18 + 36;
            EntryIngredient stacks = display.getOutputEntries().get(slot);
            widgets.add(Widgets.createSlot(new Point(sx, sy))
                    .entries(stacks)
                    .markOutput()
            );
            String chanceStr = ChatFormatting.GOLD + WeightedReward.chanceString(
                    display.getWrappedLootCrate().sortedRewards.get(slot).getWeight(),
                    display.getWrappedLootCrate().crate.getTable().getTotalWeight(true)
            );
            widgets.add(Widgets.createTooltip(new Rectangle(sx, sy, 18, 18),
                    stacks.get(0).asFormattedText(),
                    Component.translatable("jei.ftbquests.lootcrates.chance", chanceStr).withStyle(ChatFormatting.GRAY)));
        }

        widgets.add(Widgets.createDrawableWidget((graphics, mouseX, mouseY, delta) -> {
            graphics.pose().pushPose();
            graphics.pose().translate(-5, bounds.y + 5, 0);
            LootCrateTextRenderer.drawText(graphics, display.getWrappedLootCrate().crate, bounds.x, bounds.width);
            graphics.pose().popPose();
        }));

        return widgets;
    }


    @Override
    public int getDisplayHeight() {
        return WrappedLootCrate.ITEMSY * 18 + 46;
    }

    @Override
    public int getDisplayWidth(LootCrateDisplay display) {
        return WrappedLootCrate.ITEMSX * 18 + 10;
    }
}
