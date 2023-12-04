package dev.ftb.mods.ftbxmodcompat.generic.permissions;

import dev.ftb.mods.ftblibrary.integration.permissions.PermissionHelper;
import dev.ftb.mods.ftbxmodcompat.FTBXModCompat;
import dev.ftb.mods.ftbxmodcompat.config.FTBXModConfig;
import dev.ftb.mods.ftbxmodcompat.config.FTBXModConfig.PermSelector;
import org.jetbrains.annotations.NotNull;

public class PermissionsSetup {
    public static void init() {
        PermSelector sel = FTBXModConfig.PERMISSION_SELECTOR.get();
        if (sel != PermSelector.DEFAULT && !sel.isUsable()) {
            FTBXModCompat.LOGGER.error("Permissions implementation {} isn't available, falling back to default", sel);
            sel = PermSelector.DEFAULT;
        }

        PermissionHelper pHelper = setupPermissionHelper(sel);

        FTBXModCompat.LOGGER.info("Chose [{}] as the active permissions implementation", pHelper.getProvider().getName());
    }

    @NotNull
    private static PermissionHelper setupPermissionHelper(PermSelector sel) {
        PermissionHelper pHelper = PermissionHelper.INSTANCE;
        switch (sel) {
            case LUCKPERMS -> pHelper.setProviderImpl(new LuckPermsProvider());
            case FTB_RANKS -> pHelper.setProviderImpl(new FTBRanksProvider());
            case DEFAULT -> {
                if (FTBXModCompat.isFTBRanksLoaded) {
                    pHelper.setProviderImpl(new FTBRanksProvider());
                } else if (FTBXModCompat.isLuckPermsLoaded) {
                    pHelper.setProviderImpl(new LuckPermsProvider());
                }
            }
        }
        return pHelper;
    }
}
