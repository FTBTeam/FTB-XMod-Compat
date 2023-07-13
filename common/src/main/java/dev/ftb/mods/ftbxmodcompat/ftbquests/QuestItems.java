package dev.ftb.mods.ftbxmodcompat.ftbquests;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class QuestItems {
    private static final ResourceKey<Item> QUEST_BOOK = ResourceKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("ftbquests:book"));
    private static final ResourceKey<Item> LOOT_CRATE = ResourceKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("ftbquests:lootcrate"));

    private static Item questBook;
    private static Item lootCrate;

    public static Item questBook() {
        if (questBook == null) {
            questBook = Registry.ITEM.getOrThrow(QUEST_BOOK);
        }
        return questBook;
    }

    public static Item lootCrate() {
        if (lootCrate == null) {
            lootCrate = Registry.ITEM.getOrThrow(LOOT_CRATE);
        }
        return lootCrate;
    }
}
