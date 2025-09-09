package dev.ftb.mods.ftbxmodcompat.generic.currency;

import dev.ftb.mods.ftblibrary.integration.currency.CurrencyProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.sirgrantd.magic_coins.api.MagicCoinsApi;

public enum MagicCoinsProvider implements CurrencyProvider {
    INSTANCE;

    @Override
    public String getName() {
        return "Magic Coins";
    }

    @Override
    public int getTotalCurrency(Player player) {
        return MagicCoinsApi.getTotalCoins(player);
    }

    @Override
    public boolean takeCurrency(Player player, int amount) {
        if (getTotalCurrency(player) >= amount) {
            MagicCoinsApi.removeCoins(player, amount);
            return true;
        }
        return false;
    }

    @Override
    public void giveCurrency(Player player, int amount) {
        MagicCoinsApi.addCoins(player, amount);
    }

    @Override
    public Component coinName(boolean plural) {
        return Component.translatable(plural ? "text.coins" : "text.coin");
    }
}
