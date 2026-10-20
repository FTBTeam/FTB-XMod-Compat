package dev.ftb.mods.ftbxmodcompat.neoforge.ftbquests.patchouli;

import dev.ftb.mods.ftblibrary.integration.docsmod.DocsModRegistry;

public class PatchouliCompat {
    public static void init() {
        DocsModRegistry.INSTANCE.registerDocsMod("patchouli", (player, bookId, pageId, anchor) -> {
            if (player.level().isClientSide()) {
                PatchouliClient.openBook(bookId, pageId, anchor);
            }
        });
    }
}
