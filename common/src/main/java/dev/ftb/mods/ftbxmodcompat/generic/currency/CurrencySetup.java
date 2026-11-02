package dev.ftb.mods.ftbxmodcompat.generic.currency;

import dev.ftb.mods.ftblibrary.integration.currency.CurrencyHelper;
import dev.ftb.mods.ftblibrary.integration.currency.CurrencyProvider;
import dev.ftb.mods.ftblibrary.integration.currency.FallbackCurrencyProvider;
import dev.ftb.mods.ftbxmodcompat.FTBXModCompat;
import dev.ftb.mods.ftbxmodcompat.config.FTBXModConfig;

public class CurrencySetup {
    public static void init() {
        CurrencyHelper helper = CurrencyHelper.getInstance();
        FTBXModConfig.CurrencySelector selector = FTBXModConfig.CURRENCY_SELECTOR.get();

        if (selector == FTBXModConfig.CurrencySelector.DEFAULT) {
            for (var s : FTBXModConfig.CurrencySelector.values()) {
                if (s != FTBXModConfig.CurrencySelector.DEFAULT && s.isUsable()) {
                    setActiveImpl(helper, s.getProvider());
                    return;
                }
            }
        } else if (selector.isUsable()) {
            setActiveImpl(helper, selector.getProvider());
        } else {
            FTBXModCompat.LOGGER.error("Currency implementation {} isn't available, falling back to default", selector);
            helper.setActiveImpl(FTBXModConfig.CurrencySelector.DEFAULT.getProvider());
        }
    }

    private static void setActiveImpl(CurrencyHelper helper, CurrencyProvider provider) {
        helper.setActiveImpl(provider);
        FTBXModCompat.LOGGER.info("Chose [{}] as the active currency implementation", provider.getName());
    }
}
