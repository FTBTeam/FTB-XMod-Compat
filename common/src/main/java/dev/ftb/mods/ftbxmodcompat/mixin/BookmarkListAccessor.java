package dev.ftb.mods.ftbxmodcompat.mixin;

import mezz.jei.gui.bookmarks.BookmarkFactory;
import mezz.jei.gui.bookmarks.BookmarkList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BookmarkList.class)
public interface BookmarkListAccessor {
    @Accessor
    BookmarkFactory getBookmarkFactory();
}
