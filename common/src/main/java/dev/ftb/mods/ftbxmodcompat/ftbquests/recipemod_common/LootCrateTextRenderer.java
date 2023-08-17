package dev.ftb.mods.ftbxmodcompat.ftbquests.recipemod_common;

import dev.ftb.mods.ftbquests.client.ClientQuestFile;
import dev.ftb.mods.ftbquests.quest.loot.LootCrate;
import dev.ftb.mods.ftbquests.quest.loot.RewardTable;
import dev.ftb.mods.ftbquests.quest.loot.WeightedReward;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public class LootCrateTextRenderer {
    public static void drawText(GuiGraphics graphics, LootCrate crate, int x, int width) {
        Font font = Minecraft.getInstance().font;

        graphics.drawString(font, Component.translatable(crate.getItemName()).withStyle(ChatFormatting.UNDERLINE), x + 10, 0, 0xFFFFFF00);

        int total = ClientQuestFile.INSTANCE.getLootCrateNoDrop().passive;
        for (RewardTable table : ClientQuestFile.INSTANCE.getRewardTables()) {
            if (table.getLootCrate() != null) {
                total += table.getLootCrate().getDrops().passive;
            }
        }
        Component p = chance("passive", crate.getDrops().passive, total);

        total = ClientQuestFile.INSTANCE.getLootCrateNoDrop().monster;
        for (RewardTable table : ClientQuestFile.INSTANCE.getRewardTables()) {
            if (table.getLootCrate() != null) {
                total += table.getLootCrate().getDrops().monster;
            }
        }
        Component m = chance("monster", crate.getDrops().monster, total);

        total = ClientQuestFile.INSTANCE.getLootCrateNoDrop().boss;
        for (RewardTable table : ClientQuestFile.INSTANCE.getRewardTables()) {
            if (table.getLootCrate() != null) {
                total += table.getLootCrate().getDrops().boss;
            }
        }
        Component b = chance("boss", crate.getDrops().boss, total);

        int w = Math.max(font.width(p), Math.max(font.width(m), font.width(b)));
        int drawX = x + width - w - 2;
        graphics.drawString(font, p, drawX, 0, 0xFF404040, false);
        graphics.drawString(font, m, drawX, font.lineHeight, 0xFF404040, false);
        graphics.drawString(font, b, drawX, font.lineHeight * 2, 0xFF404040, false);
    }

    private static Component chance(String type, int w, int t) {
        return Component.translatable("ftbquests.loot.entitytype." + type).append(": " + WeightedReward.chanceString(w, t, true));
    }
}
