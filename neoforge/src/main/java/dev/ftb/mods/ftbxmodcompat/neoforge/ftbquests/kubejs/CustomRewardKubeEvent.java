package dev.ftb.mods.ftbxmodcompat.neoforge.ftbquests.kubejs;

import dev.ftb.mods.ftbquests.api.event.CustomRewardEvent;
import dev.ftb.mods.ftbquests.quest.reward.CustomReward;
import dev.latvian.mods.kubejs.player.KubePlayerEvent;
import net.minecraft.world.entity.player.Player;

public record CustomRewardKubeEvent(CustomRewardEvent.Data event) implements KubePlayerEvent {
	@Override
	public Player getEntity() {
		return event.player();
	}

	public CustomReward getReward() {
		return event.reward();
	}

	public boolean getNotify() {
		return event.shouldNotify();
	}
}