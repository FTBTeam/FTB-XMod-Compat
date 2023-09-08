package dev.ftb.mods.ftbxmodcompat.ftbquests.kubejs;

import dev.ftb.mods.ftbquests.events.CustomRewardEvent;
import dev.ftb.mods.ftbquests.quest.reward.CustomReward;
import dev.latvian.mods.kubejs.player.PlayerEventJS;
import net.minecraft.world.entity.player.Player;

public class CustomRewardEventJS extends PlayerEventJS {
	public final transient CustomRewardEvent event;

	public CustomRewardEventJS(CustomRewardEvent e) {
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