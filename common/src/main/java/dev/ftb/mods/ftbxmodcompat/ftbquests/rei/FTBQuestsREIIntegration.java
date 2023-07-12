package dev.ftb.mods.ftbxmodcompat.ftbquests.rei;

import dev.ftb.mods.ftbxmodcompat.ftbquests.jei_rei_common.WrappedQuest;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;

public class FTBQuestsREIIntegration implements REIClientPlugin {
    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new QuestDisplayCategory());
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerFiller(WrappedQuest.class, QuestDisplay::new);

        registry.registerDisplayGenerator(REICategories.QUEST, new QuestDisplayGenerator());
    }
}
