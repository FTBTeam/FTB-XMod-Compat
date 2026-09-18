package dev.ftb.mods.ftbxmodcompat.ftbquests.jei;

import dev.architectury.fluid.FluidStack;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.ftb.mods.ftbquests.client.ClientQuestFile;
import dev.ftb.mods.ftbquests.registry.ModDataComponents;
import dev.ftb.mods.ftbxmodcompat.FTBXModCompat;
import dev.ftb.mods.ftbxmodcompat.ftbquests.QuestItems;
import dev.ftb.mods.ftbxmodcompat.ftbquests.jei.helper.JEIRecipeHelper;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.ingredients.subtypes.ISubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.registration.IAdvancedRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

@JeiPlugin
public class FTBQuestsJEIIntegration implements IModPlugin {
	private static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(FTBXModCompat.MOD_ID, "ftbquests_jei");
	public static @Nullable IJeiRuntime runtime;

	@Override
	public void onRuntimeAvailable(IJeiRuntime r) {
		runtime = FTBXModCompat.isFTBQuestsLoaded ? r : null;

		if (ClientQuestFile.exists()) {
			ClientQuestFile.INSTANCE.updateLootCrates();
		}
	}

	@Override
	public void onRuntimeUnavailable() {
		runtime = null;
	}

	@Override
	public ResourceLocation getPluginUid() {
		return UID;
	}

	@Override
	public void registerItemSubtypes(ISubtypeRegistration r) {
		if (FTBXModCompat.isFTBQuestsLoaded) {
			r.registerSubtypeInterpreter(VanillaTypes.ITEM_STACK, QuestItems.lootCrate(),
                    new ISubtypeInterpreter<>() {
                        @Override
                        public @Nullable Object getSubtypeData(ItemStack ingredient, UidContext context) {
                            return ingredient.getOrDefault(ModDataComponents.LOOT_CRATE.get(), "");
                        }

                        @Override
                        @SuppressWarnings("deprecation")
                        public String getLegacyStringSubtypeInfo(ItemStack ingredient, UidContext context) {
                            return "";
                        }
                    });
		}
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		if (FTBXModCompat.isFTBQuestsLoaded) {
			registration.addRecipeCatalysts(JEIRecipeTypes.QUEST, QuestItems.questBook());
			registration.addRecipeCatalysts(JEIRecipeTypes.LOOT_CRATE, QuestItems.lootCrate());
		}
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration r) {
		if (FTBXModCompat.isFTBQuestsLoaded) {
			r.addRecipeCategories(new QuestCategory(r.getJeiHelpers().getGuiHelper()));
			r.addRecipeCategories(new LootCrateCategory(r.getJeiHelpers().getGuiHelper()));
		}
	}

	@Override
	public void registerAdvanced(IAdvancedRegistration registration) {
		if (FTBXModCompat.isFTBQuestsLoaded) {
			registration.addTypedRecipeManagerPlugin(JEIRecipeTypes.QUEST, QuestRecipeManagerPlugin.INSTANCE);
			registration.addTypedRecipeManagerPlugin(JEIRecipeTypes.LOOT_CRATE, LootCrateRecipeManagerPlugin.INSTANCE);
		}
	}

	public static void showRecipes(ItemStack stack) {
		IJeiRuntime runtime = FTBQuestsJEIIntegration.runtime;
		if (runtime != null) {
			runtime.getIngredientManager().createTypedIngredient(VanillaTypes.ITEM_STACK, stack, false)
					.ifPresent(ingredient -> runtime.getRecipesGui().show(
							runtime.getJeiHelpers().getFocusFactory().createFocus(RecipeIngredientRole.OUTPUT, ingredient)
					));
		}
	}

	public static void showRecipes(FluidStack fluid) {
		showFluidRecipes(runtime, fluid);
	}

	@ExpectPlatform
	public static void showFluidRecipes(IJeiRuntime runtime, FluidStack fluid) {
		throw new AssertionError();
	}
}
