package dev.ftb.mods.ftbxmodcompat.neoforge.ftbchunks.kubejs;

import dev.ftb.mods.ftbchunks.api.ClaimResult;
import dev.ftb.mods.ftbchunks.api.ClaimedChunk;
import dev.latvian.mods.kubejs.entity.KubeEntityEvent;
import dev.latvian.mods.kubejs.event.EventExit;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.entity.Entity;

public class BeforeEventJS extends AfterEventJS {
	private ClaimResult result;

	public BeforeEventJS(CommandSourceStack source, ClaimedChunk chunk) {
		super(source, chunk);
		result = chunk;
	}

	public ClaimResult getResult() {
		return result;
	}

	public void setResult(ClaimResult r) {
		result = r;
//		cancel(result);
	}

	public void setCustomResult(String messageKey) {
		result = ClaimResult.customProblem(messageKey);
//		cancel(result);
	}
}
