package dev.ftb.mods.ftbxmodcompat.ftbquests.jade;

import dev.ftb.mods.ftblibrary.util.ModUtils;
import dev.ftb.mods.ftbquests.block.QuestBarrierBlock;
import dev.ftb.mods.ftbquests.block.entity.BaseBarrierBlockEntity;
import dev.ftb.mods.ftbquests.client.ClientQuestFile;
import dev.ftb.mods.ftbquests.registry.ModBlocks;
import dev.ftb.mods.ftbquests.registry.ModDataComponents;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class JadePlugin implements IWailaPlugin {
    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.usePickedResult(ModBlocks.BARRIER.get());
        registration.usePickedResult(ModBlocks.STAGE_BARRIER.get());

        registration.registerBlockComponent(new BarrierComponentProvider(), QuestBarrierBlock.class);
        registration.registerBlockIcon(new BarrierComponentProvider(), QuestBarrierBlock.class);
        registration.addItemModNameCallback(itemStack -> {
            if (ClientQuestFile.canClientPlayerEdit()) return null;
            BaseBarrierBlockEntity.BarrierSavedData data = itemStack.get(ModDataComponents.BARRIER_SAVED.get());
            return data != null ? ModUtils.getModName(data.skin().getItem()).orElse(null) : null;
        });
    }
}
