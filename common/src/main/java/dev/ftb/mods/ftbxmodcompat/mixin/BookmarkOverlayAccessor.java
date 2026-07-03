package dev.ftb.mods.ftbxmodcompat.mixin;

import mezz.jei.gui.bookmarks.BookmarkList;
import mezz.jei.gui.overlay.bookmarks.BookmarkOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BookmarkOverlay.class)
public interface BookmarkOverlayAccessor {
    @Accessor
    BookmarkList getBookmarkList();
}
