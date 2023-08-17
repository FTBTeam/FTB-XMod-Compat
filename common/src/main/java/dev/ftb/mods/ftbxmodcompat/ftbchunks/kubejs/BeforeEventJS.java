package dev.ftb.mods.ftbxmodcompat.ftbchunks.kubejs;

import dev.ftb.mods.ftbchunks.api.ClaimResult;
import dev.ftb.mods.ftbchunks.api.ClaimedChunk;
import dev.latvian.mods.kubejs.event.EventExit;
import net.minecraft.commands.CommandSourceStack;

public class BeforeEventJS extends AfterEventJS {
	private ClaimResult result;

	public BeforeEventJS(CommandSourceStack source, ClaimedChunk chunk) {
		super(source, chunk);
		result = chunk;
	}

	public ClaimResult getResult() {
		return result;
	}

	public void setResult(ClaimResult r) throws EventExit {
		result = r;
		cancel(result);
	}

	public void setCustomResult(String messageKey) throws EventExit {
		result = ClaimResult.customProblem(messageKey);
		cancel(result);
	}
}
