package dev.ftb.mods.ftbxmodcompat.config;

import dev.ftb.mods.ftblibrary.config.NameMap;
import dev.ftb.mods.ftblibrary.snbt.config.BooleanValue;
import dev.ftb.mods.ftblibrary.snbt.config.EnumValue;
import dev.ftb.mods.ftblibrary.snbt.config.SNBTConfig;
import dev.ftb.mods.ftbxmodcompat.FTBXModCompat;

import java.util.function.BooleanSupplier;

public interface FTBXModConfig {
    SNBTConfig CONFIG = SNBTConfig.create(FTBXModCompat.MOD_ID);

    EnumValue<StageSelector> STAGE_SELECTOR = CONFIG.addEnum("stage_selector", NameMap.of(StageSelector.DEFAULT, StageSelector.values()).create())
            .comment("Select the game stages implementation to use", "DEFAULT: use KubeJS, Game Stages, vanilla in preference order, depending on mod availability");

    EnumValue<PermSelector> PERMISSION_SELECTOR = CONFIG.addEnum("permission_selector", NameMap.of(PermSelector.DEFAULT, PermSelector.values()).create())
            .comment("Select the permissions implementation to use", "DEFAULT: use FTB Ranks then Luckperms in preference order, depending on mod availability");

    BooleanValue ONLY_SHOW_KNOW_WAYSTONES = CONFIG.addBoolean("only_show_known_waystones", true)
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
}
