package dev.ftb.mods.ftbxmodcompat.ftbquests.jei.helper;

import dev.ftb.mods.ftblibrary.config.Tristate;
import dev.ftb.mods.ftblibrary.ui.input.Key;
import dev.ftb.mods.ftbxmodcompat.mixin.BookmarkListAccessor;
import dev.ftb.mods.ftbxmodcompat.mixin.BookmarkOverlayAccessor;
import mezz.jei.api.runtime.IJeiRuntime;
import mezz.jei.common.Internal;
import mezz.jei.gui.bookmarks.BookmarkFactory;
import mezz.jei.gui.bookmarks.BookmarkList;
import mezz.jei.gui.bookmarks.IBookmark;
import mezz.jei.gui.overlay.bookmarks.BookmarkOverlay;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class JEIInternalsHelper {
    static @NotNull Tristate toggleBookmark(ItemStack stack, IJeiRuntime runtime) {
        // TODO non-API usage!
        return runtime.getIngredientManager().createTypedIngredient(stack, true).map(ingr -> {
            // if EMI is present, the overlay will be a JemiBookmarkOverlay...
            if (runtime.getBookmarkOverlay() instanceof BookmarkOverlay overlay) {
                BookmarkList list = ((BookmarkOverlayAccessor) overlay).getBookmarkList();
                BookmarkFactory factory = ((BookmarkListAccessor) list).getBookmarkFactory();
                IBookmark bookmark = factory.create(ingr);
                boolean hasBookmark = list.contains(bookmark);
                list.toggleBookmark(bookmark);
                return hasBookmark ? Tristate.FALSE : Tristate.TRUE;
            }
            return Tristate.DEFAULT;
        }).orElse(Tristate.DEFAULT);
    }

    static boolean isBookmarkK(Key key) {
        return Internal.getKeyMappings().getBookmark().isActiveAndMatches(key.getInputMapping());
    }
}
