package dev.ftb.mods.ftbxmodcompat.ftbquests.jade;

import dev.ftb.mods.ftbquests.api.FTBQuestsAPI;
import dev.ftb.mods.ftbquests.block.entity.BaseBarrierBlockEntity;
import dev.ftb.mods.ftbquests.quest.TeamData;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.Nullable;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.Element;
import snownee.jade.api.ui.JadeUI;

public class BarrierComponentProvider implements IComponentProvider<BlockAccessor> {
    @Override
    public void appendTooltip(ITooltip iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig) {
    }

    @Override
    public @Nullable Element getIcon(BlockAccessor accessor, IPluginConfig config, @Nullable Element currentIcon) {
        if (accessor.getBlockEntity() instanceof BaseBarrierBlockEntity barrier
                && !barrier.getSkin().isEmpty()
                && !TeamData.get(accessor.getPlayer()).getCanEdit(accessor.getPlayer()))
        {
            return JadeUI.item(barrier.getSkin());
        }
        return null;
    }

    @Override
    public Identifier getUid() {
        return Identifier.fromNamespaceAndPath(FTBQuestsAPI.MOD_ID, "barrier");
    }
}
