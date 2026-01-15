package dev.ftb.mods.ftbxmodcompat.fabric.ftbchunks.waystones;

import com.mojang.logging.LogUtils;
import dev.architectury.platform.Platform;
import dev.architectury.utils.Env;
import dev.ftb.mods.ftbxmodcompat.ClientUtil;
import dev.ftb.mods.ftbxmodcompat.config.FTBXModConfig;
import dev.ftb.mods.ftbxmodcompat.ftbchunks.waystones.WaystoneData;
import dev.ftb.mods.ftbxmodcompat.ftbchunks.waystones.WaystoneMapIcon;
import dev.ftb.mods.ftbxmodcompat.ftbchunks.waystones.WaystonesCommon;
import net.blay09.mods.waystones.api.Waystone;
import net.blay09.mods.waystones.api.WaystoneVisibility;
import net.blay09.mods.waystones.api.WaystonesAPI;
import net.blay09.mods.waystones.api.event.WaystoneRemoveReceivedEvent;
import net.blay09.mods.waystones.api.event.WaystoneUpdatedEvent;
import org.slf4j.Logger;

public class WaystonesCompat {
	private static final Logger LOGGER = LogUtils.getLogger();

	public static void init() {
		WaystoneUpdatedEvent.EVENT.register(WaystonesCompat::updateWaystone);
		WaystoneRemoveReceivedEvent.EVENT.register(WaystonesCompat::removeWaystone);
	}

	private static void removeWaystone(WaystoneRemoveReceivedEvent event) {
		LOGGER.trace("Waystone removed: {}", event.waystoneId());
		WaystonesCommon.removeWaystone(event.waystoneId());
	}

	private static void updateWaystone(WaystoneUpdatedEvent event) {
		LOGGER.trace("waystone updated: {} {}", event.waystone().getWaystoneUid(), event.waystone().getVisibility());
		Waystone w = event.waystone();
		if (Platform.getEnvironment() == Env.CLIENT && (!FTBXModConfig.ONLY_SHOW_KNOWN_WAYSTONES.get() || WaystonesAPI.isWaystoneActivated(ClientUtil.getClientPlayer(), w))) {
			WaystonesCommon.updateWaystone(w.getWaystoneUid(), new WaystoneData(w.getDimension(), new WaystoneMapIcon(w.getPos(), w.getName(), w.getVisibility() == WaystoneVisibility.GLOBAL)));
		}
	}
}
