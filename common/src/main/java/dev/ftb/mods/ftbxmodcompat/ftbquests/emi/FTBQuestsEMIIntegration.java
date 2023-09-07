package dev.ftb.mods.ftbxmodcompat.ftbquests.emi;

import dev.emi.emi.api.EmiApi;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.stack.EmiStack;
import dev.ftb.mods.ftbquests.item.FTBQuestsItems;
import dev.ftb.mods.ftbxmodcompat.FTBXModCompat;
import net.minecraft.world.item.ItemStack;

@EmiEntrypoint
public class FTBQuestsEMIIntegration implements EmiPlugin {
    public static final EmiStack WORKSTATION = EmiStack.of(FTBQuestsItems.BOOK.get());

    @Override
    public void register(EmiRegistry registry) {
        if (FTBXModCompat.isFTBQuestsLoaded) {
            registry.addCategory(EMICategories.QUEST);
            registry.addWorkstation(EMICategories.QUEST, WORKSTATION);

            QuestRecipeManager.INSTANCE.getQuests().forEach(quest -> {
                registry.addRecipe(new QuestRecipe(quest));
            });
        }
    }

    public static void showRecipes(ItemStack stack) {
        EmiApi.displayRecipes(EmiStack.of(stack));
    }
}
