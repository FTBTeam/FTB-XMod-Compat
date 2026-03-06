package dev.ftb.mods.ftbxmodcompat.ftbquests.rei;

import dev.ftb.mods.ftbxmodcompat.ftbquests.recipemod_common.WrappedQuest;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import org.jetbrains.annotations.Nullable;

public class QuestDisplay extends BasicDisplay {
    private final WrappedQuest wrappedQuest;

    public QuestDisplay(WrappedQuest wrappedQuest) {
        super(EntryIngredients.ofIngredients(wrappedQuest.inputIngredients()), EntryIngredients.ofIngredients(wrappedQuest.outputIngredients()));

        this.wrappedQuest = wrappedQuest;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return REICategories.QUEST;
    }

    @Override
    public @Nullable DisplaySerializer<? extends Display> getSerializer() {
        return null;
    }

    public WrappedQuest getWrappedQuest() {
        return wrappedQuest;
    }
}
