package dev.ftb.mods.ftbxmodcompat.ftbquests;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class QuestItems {
    private static final ResourceKey<Item> QUEST_BOOK = ResourceKey.create(Registries.ITEM, new ResourceLocation("ftbquests:book"));
    private static final ResourceKey<Item> LOOT_CRATE = ResourceKey.create(Registries.ITEM, new ResourceLocation("ftbquests:lootcrate"));

    private static Item questBook;
    private static Item lootCrate;

    public static Item questBook() {
        if (questBook == null) {
            questBook = BuiltInRegistries.ITEM.getOrThrow(QUEST_BOOK);
        }
        return questBook;
    }

    public static Item lootCrate() {
        if (lootCrate == null) {
            lootCrate = BuiltInRegistries.ITEM.getOrThrow(LOOT_CRATE);
        }
        return lootCrate;
    }
}
