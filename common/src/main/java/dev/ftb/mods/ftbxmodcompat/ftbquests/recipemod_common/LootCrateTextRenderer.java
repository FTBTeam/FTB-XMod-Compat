package dev.ftb.mods.ftbxmodcompat.ftbquests.recipemod_common;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.ftb.mods.ftbquests.client.ClientQuestFile;
import dev.ftb.mods.ftbquests.quest.loot.LootCrate;
import dev.ftb.mods.ftbquests.quest.loot.RewardTable;
import dev.ftb.mods.ftbquests.quest.loot.WeightedReward;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;

public class LootCrateTextRenderer {
    public static void drawText(PoseStack poseStack, LootCrate crate, int x, int width) {
        Font font = Minecraft.getInstance().font;

        font.drawShadow(poseStack, Component.translatable(crate.itemName).withStyle(ChatFormatting.UNDERLINE), x + 10, 0, 0xFFFFFF00);

        int total = ClientQuestFile.INSTANCE.lootCrateNoDrop.passive;
        for (RewardTable table : ClientQuestFile.INSTANCE.rewardTables) {
            if (table.lootCrate != null) {
                total += table.lootCrate.drops.passive;
            }
        }
        Component p = chance("passive", crate.drops.passive, total);

        total = ClientQuestFile.INSTANCE.lootCrateNoDrop.monster;
        for (RewardTable table : ClientQuestFile.INSTANCE.rewardTables) {
            if (table.lootCrate != null) {
                total += table.lootCrate.drops.monster;
            }
        }
        Component m = chance("monster", crate.drops.monster, total);

        total = ClientQuestFile.INSTANCE.lootCrateNoDrop.boss;
        for (RewardTable table : ClientQuestFile.INSTANCE.rewardTables) {
            if (table.lootCrate != null) {
                total += table.lootCrate.drops.boss;
            }
        }
        Component b = chance("boss", crate.drops.boss, total);

        int w = Math.max(font.width(p), Math.max(font.width(m), font.width(b)));
        int drawX = x + width - w - 2;
        font.draw(poseStack, p, drawX, 0, 0xFF404040);
        font.draw(poseStack, m, drawX, font.lineHeight, 0xFF404040);
        font.draw(poseStack, b, drawX, font.lineHeight * 2, 0xFF404040);
    }

    private static Component chance(String type, int w, int t) {
        return Component.translatable("ftbquests.loot.entitytype." + type).append(": " + WeightedReward.chanceString(w, t, true));
    }
}
