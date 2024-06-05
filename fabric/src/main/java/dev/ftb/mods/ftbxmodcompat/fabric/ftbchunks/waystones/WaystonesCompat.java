package dev.ftb.mods.ftbxmodcompat.fabric.ftbchunks.waystones;

import dev.ftb.mods.ftbxmodcompat.FTBXModCompat;
import dev.ftb.mods.ftbxmodcompat.ftbchunks.waystones.WaystoneData;
import dev.ftb.mods.ftbxmodcompat.ftbchunks.waystones.WaystoneMapIcon;
import dev.ftb.mods.ftbxmodcompat.ftbchunks.waystones.WaystonesCommon;
import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.waystones.api.Waystone;
import net.blay09.mods.waystones.api.WaystoneVisibility;
import net.blay09.mods.waystones.api.event.WaystoneRemoveReceivedEvent;
import net.blay09.mods.waystones.api.event.WaystoneUpdatedEvent;

public class WaystonesCompat {
	public static void init() {
		Balm.getEvents().onEvent(WaystoneUpdatedEvent.class, WaystonesCompat::updateWaystone);
		Balm.getEvents().onEvent(WaystoneRemoveReceivedEvent.class, WaystonesCompat::removeWaystone);
	}

	private static void removeWaystone(WaystoneRemoveReceivedEvent event) {
		FTBXModCompat.LOGGER.info("waystone removed: " + event.getWaystoneId());
		WaystonesCommon.removeWaystone(event.getWaystoneId());
	}

	private static void updateWaystone(WaystoneUpdatedEvent event) {
		FTBXModCompat.LOGGER.info("waystone updated: " + event.getWaystone().getWaystoneUid());
		Waystone w = event.getWaystone();
		WaystonesCommon.updateWaystone(w.getWaystoneUid(), new WaystoneData(w.getDimension(), new WaystoneMapIcon(w.getPos(), w.getName(), w.getVisibility() == WaystoneVisibility.GLOBAL)));
	}
}
