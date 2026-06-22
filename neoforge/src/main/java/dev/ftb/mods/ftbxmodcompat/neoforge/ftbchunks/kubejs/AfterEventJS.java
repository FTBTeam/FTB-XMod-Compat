package dev.ftb.mods.ftbxmodcompat.neoforge.ftbchunks.kubejs;

import dev.ftb.mods.ftbchunks.api.ClaimedChunk;
import dev.latvian.mods.kubejs.entity.KubeEntityEvent;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.Nullable;

public class AfterEventJS implements KubeEntityEvent {
	public final CommandSourceStack source;
	public final ClaimedChunk chunk;

	public AfterEventJS(CommandSourceStack source, ClaimedChunk chunk) {
		this.source = source;
		this.chunk = chunk;
	}

	@Override
	@Nullable
	public Entity getEntity() {
		return source.getEntity();
	}

	public BlockPos getClaimPos() {
		return chunk.getPos().chunkPos().getWorldPosition();
	}
}
