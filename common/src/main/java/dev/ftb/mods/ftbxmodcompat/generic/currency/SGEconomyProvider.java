package dev.ftb.mods.ftbxmodcompat.generic.currency;

import dev.ftb.mods.ftblibrary.integration.currency.CurrencyProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.sirgrantd.sg_economy.api.SGEconomyApi;
import net.sirgrantd.sg_economy.api.economy.EconomyProvider;

public enum SgEconomyProvider implements CurrencyProvider {
    INSTANCE;

    private final EconomyProvider sgEconomy = SGEconomyApi.getSGEconomy();

    @Override
    public String getName() {
        return "SG Economy";
    }

    @Override
    public int getTotalCurrency(Player player) {
        return sgEconomy.getCoins(player);
    }

    @Override
    public boolean takeCurrency(Player player, int amount) {
        int coins = getTotalCurrency(player);
        if (coins >= amount) {
            sgEconomy.removeCoins(player, amount);
            return true;
        }
        return false;
    }

    @Override
    public void giveCurrency(Player player, int amount) {
        sgEconomy.addCoins(player, amount);
    }

    @Override
    public Component coinName(boolean plural) {
        return Component.translatable(plural ? "text.coins" : "text.coin");
    }
}