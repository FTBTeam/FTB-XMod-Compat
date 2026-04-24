package dev.ftb.mods.ftbxmodcompat.neoforge.ftbchunks.kubejs;

import dev.ftb.mods.ftbchunks.api.ClaimResult;
import dev.ftb.mods.ftbchunks.api.ClaimedChunk;
import net.minecraft.commands.CommandSourceStack;

public class BeforeEventJS extends AfterEventJS {
	private ClaimResult result;

	public BeforeEventJS(CommandSourceStack source, ClaimedChunk chunk) {
		super(source, chunk);
		result = ClaimResult.SUCCESS;
	}

	public ClaimResult getResult() {
		return result;
	}

	public void setResult(ClaimResult result) {
		this.result = result;
	}

	public void setCustomResult(String messageKey) {
		result = ClaimResult.customProblem(messageKey);
	}
}
