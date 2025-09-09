package dev.ftb.mods.ftbxmodcompat.generic.currency;

import dev.ftb.mods.ftblibrary.integration.currency.CurrencyHelper;
import dev.ftb.mods.ftblibrary.integration.currency.FallbackCurrencyProvider;
import dev.ftb.mods.ftbxmodcompat.FTBXModCompat;
import dev.ftb.mods.ftbxmodcompat.config.FTBXModConfig;

public class CurrencySetup {
    public static void init() {
        FTBXModConfig.CurrencySelector sel = FTBXModConfig.CURRENCY_SELECTOR.get();
        if (sel != FTBXModConfig.CurrencySelector.DEFAULT && !sel.isUsable()) {
            FTBXModCompat.LOGGER.error("Currency implementation {} isn't available, falling back to default", sel);
            sel = FTBXModConfig.CurrencySelector.DEFAULT;
        }

        CurrencyHelper helper = CurrencyHelper.getInstance();
        switch (sel) {
            case MAGIC_COINS -> helper.setActiveImpl(MagicCoinsProvider.INSTANCE);
            case DEFAULT -> {
                if (FTBXModCompat.isMagicCoinsLoaded) {
                    helper.setActiveImpl(MagicCoinsProvider.INSTANCE);
                } else {
                    helper.setActiveImpl(FallbackCurrencyProvider.INSTANCE);
                }
            }
        }

        FTBXModCompat.LOGGER.info("Chose [{}] as the active currency implementation", helper.getProvider().getName());
    }
}
