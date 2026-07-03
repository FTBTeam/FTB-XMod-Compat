package dev.ftb.mods.ftbxmodcompat.ftbquests.jei.helper;

import dev.ftb.mods.ftblibrary.config.Tristate;
import dev.ftb.mods.ftblibrary.ui.input.Key;
import dev.ftb.mods.ftbxmodcompat.FTBXModCompat;
import dev.ftb.mods.ftbxmodcompat.ftbquests.jei.FTBQuestsJEIIntegration;
import dev.ftb.mods.ftbxmodcompat.ftbquests.jei.LootCrateRecipeManagerPlugin;
import dev.ftb.mods.ftbxmodcompat.ftbquests.jei.QuestRecipeManagerPlugin;
import dev.ftb.mods.ftbxmodcompat.ftbquests.recipemod_common.BaseRecipeHelper;
import dev.ftb.mods.ftbxmodcompat.mixin.BookmarkListAccessor;
import dev.ftb.mods.ftbxmodcompat.mixin.BookmarkOverlayAccessor;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.runtime.IIngredientManager;
import mezz.jei.api.runtime.IJeiRuntime;
import mezz.jei.common.Internal;
import mezz.jei.gui.bookmarks.BookmarkFactory;
import mezz.jei.gui.bookmarks.BookmarkList;
import mezz.jei.gui.bookmarks.IBookmark;
import mezz.jei.gui.overlay.bookmarks.BookmarkOverlay;
import net.minecraft.world.item.ItemStack;

import java.util.Collection;

public class JEIRecipeHelper extends BaseRecipeHelper {
    @Override
    public void showRecipes(ItemStack itemStack) {
        FTBQuestsJEIIntegration.showRecipes(itemStack);
    }

    @Override
    public Tristate toggleBookmark(ItemStack stack) {
        IJeiRuntime runtime = FTBQuestsJEIIntegration.runtime;
        if (runtime != null) {
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
        return Tristate.DEFAULT;
    }

    @Override
    public String getHelperName() {
        return "JEI";
    }

    protected void refreshQuests() {
        QuestRecipeManagerPlugin.INSTANCE.refresh();
    }

    protected void refreshLootcrates() {
        LootCrateRecipeManagerPlugin.INSTANCE.refresh();
    }

    @Override
    public void updateItemsDynamic(Collection<ItemStack> toRemove, Collection<ItemStack> toAdd) {
        if (FTBQuestsJEIIntegration.runtime != null) {
            IIngredientManager manager = FTBQuestsJEIIntegration.runtime.getIngredientManager();
            if (!toRemove.isEmpty()) {
                manager.removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK, toRemove);
            }
            if (!toAdd.isEmpty()) {
                manager.addIngredientsAtRuntime(VanillaTypes.ITEM_STACK, toAdd);
            }
            FTBXModCompat.LOGGER.debug("removed {} items from JEI, added {} items", toRemove.size(), toAdd.size());
        }
    }

    @Override
    public boolean isBookmarkKey(Key key) {
        // TODO non-API usage!  API only exposes the 'R' and 'U' mappings
        return Internal.getKeyMappings().getBookmark().isActiveAndMatches(key.getInputMapping());
    }
}
