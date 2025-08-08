package dev.ftb.mods.ftbxmodcompat.ftbquests.jade;

import dev.ftb.mods.ftbquests.api.FTBQuestsAPI;
import dev.ftb.mods.ftbquests.block.entity.BaseBarrierBlockEntity;
import dev.ftb.mods.ftbquests.quest.TeamData;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.IElement;
import snownee.jade.api.ui.IElementHelper;

public class BarrierComponentProvider implements IComponentProvider<BlockAccessor> {
    @Override
    public void appendTooltip(ITooltip iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig) {
    }

    @Override
    public @Nullable IElement getIcon(BlockAccessor accessor, IPluginConfig config, IElement currentIcon) {
        if (accessor.getBlockEntity() instanceof BaseBarrierBlockEntity barrier
                && !barrier.getSkin().isEmpty()
                && !TeamData.get(accessor.getPlayer()).getCanEdit(accessor.getPlayer()))
        {
            return IElementHelper.get().item(barrier.getSkin());
        }
        return null;
    }

    @Override
    public ResourceLocation getUid() {
        return ResourceLocation.fromNamespaceAndPath(FTBQuestsAPI.MOD_ID, "barrier");
    }
}
