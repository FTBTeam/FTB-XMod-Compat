package dev.ftb.mods.ftbxmodcompat.ftbquests.emi;

import dev.emi.emi.api.recipe.BasicEmiRecipe;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import dev.ftb.mods.ftbquests.FTBQuests;
import dev.ftb.mods.ftbxmodcompat.ftbquests.emi.widget.ClickableTextWidget;
import dev.ftb.mods.ftbxmodcompat.ftbquests.recipemod_common.WrappedQuest;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class QuestRecipe extends BasicEmiRecipe {
    private final WrappedQuest wrappedQuest;

    public QuestRecipe(WrappedQuest wrappedQuest) {
        super(EMICategories.QUEST, ResourceLocation.tryBuild(FTBQuests.MOD_ID, String.valueOf(wrappedQuest.quest.id)), 152, 70);

        for (Ingredient ingredient : wrappedQuest.inputIngredients()) {
            this.inputs.add(EmiIngredient.of(ingredient));
        }

        for (Ingredient ingredient : wrappedQuest.outputIngredients()) {
            for (ItemStack item : ingredient.getItems()) {
                this.outputs.add(EmiStack.of(item));
            }
        }

        this.wrappedQuest = wrappedQuest;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        int inputSize = Math.min(9, inputs.size());
        for (int i = 0; i < 9; i++) {
            EmiIngredient ingredient = i < inputSize ? inputs.get(i) : EmiStack.EMPTY;
            widgets.addSlot(ingredient, (i % 3) * 18 + 4, (i / 3) * 18 + 13);
        }

        int outputSize = Math.min(9, outputs.size());
        for (int i = 0; i < 9; i++) {
            EmiIngredient ingredient = i < outputSize ? outputs.get(i) : EmiStack.EMPTY;
            widgets.addSlot(ingredient, (i % 3) * 18 + 94, (i / 3) * 18 + 13).recipeContext(this);
        }

        widgets.addTexture(EmiTexture.EMPTY_ARROW, 64, 32);

        Component text = wrappedQuest.quest.getTitle().copy().withStyle(ChatFormatting.UNDERLINE);
        widgets.add(new ClickableTextWidget(text.getVisualOrderText(), 1, 1, 0, false, c -> {
            Minecraft.getInstance().setScreen(null);
            wrappedQuest.openQuestGui();
        }));
    }

    @Override
    public boolean supportsRecipeTree() {
        return false;
    }
}
