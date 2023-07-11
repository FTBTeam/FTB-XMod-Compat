package dev.ftb.mods.ftbxmodcompat.ftbquests.rei.helper;

import dev.ftb.mods.ftbxmodcompat.ftbquests.BaseRecipeHelper;
import me.shedaniel.rei.api.client.view.ViewSearchBuilder;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.entry.type.EntryDefinition;
import me.shedaniel.rei.api.common.entry.type.EntryTypeRegistry;
import net.minecraft.world.item.ItemStack;

public class REIRecipeHelper extends BaseRecipeHelper {
    @Override
    public void showRecipes(ItemStack stack) {
        for (EntryDefinition<?> definition : EntryTypeRegistry.getInstance().values()) {
            if (definition.getValueType().isInstance(stack)) {
                try {
                    ViewSearchBuilder.builder()
                            .addRecipesFor(EntryStack.of(definition.getType().cast(), stack))
                            .open();
                } catch (Exception e) {
                    // EntryStack.of could fail because we are guessing the type
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    @Override
    public String getHelperName() {
        return "REI";
    }

    protected void refreshQuests() {
        // TODO native REI support
    }

    protected void refreshLootcrates() {
        // TODO native REI support
    }
}
