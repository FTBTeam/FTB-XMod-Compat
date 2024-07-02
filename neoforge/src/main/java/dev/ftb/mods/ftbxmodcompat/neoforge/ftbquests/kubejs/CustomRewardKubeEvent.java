package dev.ftb.mods.ftbxmodcompat.neoforge.ftbquests.kubejs;

import dev.ftb.mods.ftbquests.events.CustomRewardEvent;
import dev.ftb.mods.ftbquests.quest.reward.CustomReward;
import dev.latvian.mods.kubejs.player.KubePlayerEvent;
import net.minecraft.world.entity.player.Player;

public class CustomRewardKubeEvent implements KubePlayerEvent {
	public final transient CustomRewardEvent event;

	public CustomRewardKubeEvent(CustomRewardEvent e) {
		event = e;
	}

	@Override
	public Player getEntity() {
		return event.getPlayer();
	}

	public CustomReward getReward() {
		return event.getReward();
	}

	public boolean getNotify() {
		return event.getNotify();
	}
}