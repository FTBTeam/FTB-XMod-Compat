package dev.ftb.mods.ftbxmodcompat.generic.currency;

import dev.ftb.mods.ftblibrary.integration.currency.CurrencyHelper;
import dev.ftb.mods.ftblibrary.integration.currency.CurrencyProvider;
import dev.ftb.mods.ftbxmodcompat.FTBXModCompat;
import dev.ftb.mods.ftbxmodcompat.config.FTBXModConfig;
import dev.ftb.mods.ftbxmodcompat.config.FTBXModConfig.CurrencySelector;

public class CurrencySetup {
    public static void init() {
        CurrencyHelper helper = CurrencyHelper.getInstance();
        CurrencySelector selector = FTBXModConfig.CURRENCY_SELECTOR.get();

        if (selector == CurrencySelector.DEFAULT) {
            for (var s : CurrencySelector.values()) {
                if (s != CurrencySelector.DEFAULT && s.isUsable()) {
                    setActiveImpl(helper, s.getProvider());
                    return;
                }
            }
        } else if (selector.isUsable()) {
            setActiveImpl(helper, selector.getProvider());
        } else {
            FTBXModCompat.LOGGER.error("Currency implementation {} isn't available, falling back to default", selector);
            helper.setActiveImpl(CurrencySelector.DEFAULT.getProvider());
        }
    }

    private static void setActiveImpl(CurrencyHelper helper, CurrencyProvider provider) {
        helper.setActiveImpl(provider);
        FTBXModCompat.LOGGER.info("Chose [{}] as the active currency implementation", provider.getName());
    }
}
