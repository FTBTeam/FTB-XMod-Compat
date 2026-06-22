package dev.ftb.mods.ftbxmodcompat.ftbquests;

import dev.ftb.mods.ftblibrary.util.Lazy;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

public class QuestItems {
    private static final ResourceKey<Item> QUEST_BOOK = ResourceKey.create(Registries.ITEM, Identifier.parse("ftbquests:book"));
    private static final ResourceKey<Item> LOOT_CRATE = ResourceKey.create(Registries.ITEM, Identifier.parse("ftbquests:lootcrate"));

    private static final Lazy<Holder<Item>> questBook = Lazy.of(() -> BuiltInRegistries.ITEM.getOrThrow(QUEST_BOOK));
    private static final Lazy<Holder<Item>> lootCrate = Lazy.of(() -> BuiltInRegistries.ITEM.getOrThrow(LOOT_CRATE));

    public static Item questBook() {
        return questBook.get().value();
    }

    public static Item lootCrate() {
        return lootCrate.get().value();
    }
}
