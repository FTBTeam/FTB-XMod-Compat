package dev.ftb.mods.ftbxmodcompat.config;

import dev.ftb.mods.ftblibrary.config.NameMap;
import dev.ftb.mods.ftblibrary.integration.currency.CurrencyProvider;
import dev.ftb.mods.ftblibrary.integration.currency.FallbackCurrencyProvider;
import dev.ftb.mods.ftblibrary.snbt.config.BooleanValue;
import dev.ftb.mods.ftblibrary.snbt.config.EnumValue;
import dev.ftb.mods.ftblibrary.snbt.config.SNBTConfig;
import dev.ftb.mods.ftbxmodcompat.FTBXModCompat;
import dev.ftb.mods.ftbxmodcompat.generic.currency.FTBEconomyProvider;
import dev.ftb.mods.ftbxmodcompat.generic.currency.SGEconomyProvider;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public interface FTBXModConfig {
    SNBTConfig CONFIG = SNBTConfig.create(FTBXModCompat.MOD_ID);

    EnumValue<StageSelector> STAGE_SELECTOR = CONFIG.addEnum("stage_selector", NameMap.of(StageSelector.DEFAULT, StageSelector.values()).create())
            .comment("Select the game stages implementation to use", "DEFAULT: use KubeJS, Game Stages, vanilla in preference order, depending on mod availability");

    EnumValue<PermSelector> PERMISSION_SELECTOR = CONFIG.addEnum("permission_selector", NameMap.of(PermSelector.DEFAULT, PermSelector.values()).create())
            .comment("Select the permissions implementation to use", "DEFAULT: use FTB Ranks then Luckperms in preference order, depending on mod availability");

    EnumValue<CurrencySelector> CURRENCY_SELECTOR = CONFIG.addEnum("currency_selector", NameMap.of(CurrencySelector.DEFAULT, CurrencySelector.values()).create())
            .comment("Select the currency implementation to use", "Note: currently Neoforge-only", "DEFAULT: use SG Economy if installed, otherwise no currency plugin");

    BooleanValue ONLY_SHOW_KNOWN_WAYSTONES = CONFIG.addBoolean("only_show_known_waystones", true)
            .comment("Only show waystones that have been discovered");

    enum StageSelector {
        DEFAULT(()-> true),
        VANILLA(() -> true),
        KUBEJS(() -> FTBXModCompat.isKubeJSLoaded),
        GAMESTAGES(() -> FTBXModCompat.isGameStagesLoaded);

        private final BooleanSupplier usable;

        StageSelector(BooleanSupplier usable) {
            this.usable = usable;
        }

        public boolean isUsable() {
            return usable.getAsBoolean();
        }
    }

    enum PermSelector {
        DEFAULT(()-> true),
        FTB_RANKS(() -> FTBXModCompat.isFTBRanksLoaded),
        LUCKPERMS(() -> FTBXModCompat.isLuckPermsLoaded);

        private final BooleanSupplier usable;

        PermSelector(BooleanSupplier usable) {
            this.usable = usable;
        }

        public boolean isUsable() {
            return usable.getAsBoolean();
        }
    }

    enum CurrencySelector {
        DEFAULT(() -> true, () -> FallbackCurrencyProvider.INSTANCE),
        FTB_ECONOMY(() -> FTBXModCompat.isFTBEconomyLoaded, () -> FTBEconomyProvider.INSTANCE),
        SG_ECONOMY_API(() -> FTBXModCompat.isSGEconomyLoaded, () -> SGEconomyProvider.INSTANCE);

        private final BooleanSupplier usable;
        private final Supplier<CurrencyProvider> provider;

        CurrencySelector(BooleanSupplier usable, Supplier<CurrencyProvider> provider) {
            this.usable = usable;
            this.provider = provider;
        }

        public boolean isUsable() {
            return usable.getAsBoolean();
        }

        public CurrencyProvider getProvider() {
            return provider.get();
        }
    }
}
