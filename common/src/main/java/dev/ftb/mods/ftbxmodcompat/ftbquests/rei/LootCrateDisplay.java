package dev.ftb.mods.ftbxmodcompat.ftbquests.rei;

import com.mojang.serialization.MapCodec;
import dev.ftb.mods.ftbxmodcompat.ftbquests.recipemod_common.WrappedLootCrate;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.windows.INPUT;

public class LootCrateDisplay extends BasicDisplay {
    private final WrappedLootCrate wrappedLootCrate;

    public LootCrateDisplay(WrappedLootCrate wrappedLootCrate) {
        super(EntryIngredients.ofIngredients(wrappedLootCrate.inputIngredients()), EntryIngredients.ofIngredients(wrappedLootCrate.outputIngredients()));

        this.wrappedLootCrate = wrappedLootCrate;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return REICategories.LOOT_CRATE;
    }

    @Override
    public @Nullable DisplaySerializer<? extends Display> getSerializer() {
        return null;
    }

    public WrappedLootCrate getWrappedLootCrate() {
        return wrappedLootCrate;
    }

}
