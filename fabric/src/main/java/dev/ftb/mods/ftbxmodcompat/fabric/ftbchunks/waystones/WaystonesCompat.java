package dev.ftb.mods.ftbxmodcompat.fabric.ftbchunks.waystones;

import com.mojang.logging.LogUtils;
import dev.ftb.mods.ftbxmodcompat.config.FTBXModConfig;
import dev.ftb.mods.ftbxmodcompat.ftbchunks.waystones.WaystoneData;
import dev.ftb.mods.ftbxmodcompat.ftbchunks.waystones.WaystoneMapIcon;
import dev.ftb.mods.ftbxmodcompat.ftbchunks.waystones.WaystonesCommon;
import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.waystones.api.Waystone;
import net.blay09.mods.waystones.api.WaystoneVisibility;
import net.blay09.mods.waystones.api.WaystonesAPI;
import net.blay09.mods.waystones.api.event.WaystoneRemoveReceivedEvent;
import net.blay09.mods.waystones.api.event.WaystoneUpdatedEvent;
import net.minecraft.client.Minecraft;
import org.slf4j.Logger;

public class WaystonesCompat {
	private static final Logger LOGGER = LogUtils.getLogger();

	public static void init() {
		Balm.getEvents().onEvent(WaystoneUpdatedEvent.class, WaystonesCompat::updateWaystone);
		Balm.getEvents().onEvent(WaystoneRemoveReceivedEvent.class, WaystonesCompat::removeWaystone);
	}

	private static void removeWaystone(WaystoneRemoveReceivedEvent event) {
		LOGGER.trace("Waystone removed: {}", event.getWaystoneId());
		WaystonesCommon.removeWaystone(event.getWaystoneId());
	}

	private static void updateWaystone(WaystoneUpdatedEvent event) {
		LOGGER.trace("waystone updated: {} {}", event.getWaystone().getWaystoneUid(), event.getWaystone().getVisibility());
		Waystone w = event.getWaystone();
		if (!FTBXModConfig.ONLY_SHOW_KNOW_WAYSTONES.get() || WaystonesAPI.isWaystoneActivated(Minecraft.getInstance().player, w)) {
			WaystonesCommon.updateWaystone(w.getWaystoneUid(), new WaystoneData(w.getDimension(), new WaystoneMapIcon(w.getPos(), w.getName(), w.getVisibility() == WaystoneVisibility.GLOBAL)));
		}
	}
}
