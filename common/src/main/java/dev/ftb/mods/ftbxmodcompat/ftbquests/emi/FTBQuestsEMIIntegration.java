package dev.ftb.mods.ftbxmodcompat.ftbquests.emi;

import dev.emi.emi.api.EmiApi;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.world.item.ItemStack;

@EmiEntrypoint
public class FTBQuestsEMIIntegration implements EmiPlugin {
    @Override
    public void register(EmiRegistry registry) {
        // TODO
    }

    public static void showRecipes(ItemStack stack) {
        EmiApi.displayRecipes(EmiStack.of(stack));
    }
}
