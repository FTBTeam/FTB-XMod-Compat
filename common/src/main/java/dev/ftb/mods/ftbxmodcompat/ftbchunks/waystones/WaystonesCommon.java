package dev.ftb.mods.ftbxmodcompat.ftbchunks.waystones;

import dev.ftb.mods.ftbchunks.api.client.event.AddMapIconEvent;
import dev.ftb.mods.ftbxmodcompat.FTBXModCompat;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WaystonesCommon {
    private static final Map<ResourceKey<Level>, Map<UUID,WaystoneMapIcon>> WAYSTONES = new HashMap<>();
    private static final Map<UUID,ResourceKey<Level>> BY_ID = new HashMap<>();

    public static void init() {
        FTBXModCompat.LOGGER.info("[FTB Chunks] Enabled Waystones integration");
    }

    public static void mapWidgets(AddMapIconEvent.Data event) {
        WAYSTONES.getOrDefault(event.dimension(), Collections.emptyMap())
                .values().forEach(event::add);
    }

    public static void removeWaystone(UUID waystoneId) {
        var dim = BY_ID.get(waystoneId);
        if (dim != null && WAYSTONES.containsKey(dim)) {
            WAYSTONES.get(dim).remove(waystoneId);
            BY_ID.remove(waystoneId);
        }
    }

    public static void updateWaystone(UUID waystoneUid, WaystoneData waystoneData) {
        WAYSTONES.computeIfAbsent(waystoneData.dimension(), _ -> new HashMap<>())
                .put(waystoneUid, waystoneData.icon());
        BY_ID.put(waystoneUid, waystoneData.dimension());
    }
}
