package dev.ftb.mods.ftbxmodcompat.generic.permissions;

import dev.ftb.mods.ftblibrary.integration.permissions.PermissionHelper;
import dev.ftb.mods.ftbxmodcompat.FTBXModCompat;

public class PermissionsSetup {
    public static void init() {
        PermissionHelper pHelper = PermissionHelper.getInstance();
        if (FTBXModCompat.isFTBRanksLoaded) {
            pHelper.setProviderImpl(new FTBRanksProvider());
        } else if (FTBXModCompat.isLuckPermsLoaded) {
            pHelper.setProviderImpl(new LuckPermsProvider());
        }
        FTBXModCompat.LOGGER.info("Chose [{}] as the active permissions implementation", pHelper.getProvider().getName());
    }
}
