package dev.ftb.mods.ftbxmodcompat.generic.currency;

import dev.ftb.mods.ftblibrary.integration.currency.CurrencyProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.sirgrantd.sg_economy.api.EconomyEventProvider;
import net.sirgrantd.sg_economy.api.SGEconomyApi;

public enum SGEconomyProvider implements CurrencyProvider {
    INSTANCE;

    private final EconomyEventProvider sgEconomy = SGEconomyApi.get();

    @Override
    public String getName() {
        return "SG Economy";
    }

    @Override
    public int getTotalCurrency(Player player) {
        return sgEconomy.getBalanceAsInt(player);
    }

    @Override
    public boolean takeCurrency(Player player, int amount) {
        int coins = getTotalCurrency(player);
        if (coins >= amount) {
            sgEconomy.withdrawBalanceAsInt(player, amount);
            return true;
        }
        return false;
    }

    @Override
    public void giveCurrency(Player player, int amount) {
        sgEconomy.depositBalanceAsInt(player, amount);
    }

    @Override
    public Component coinName(boolean plural) {
        return Component.translatable(plural ? "text.coins" : "text.coin");
    }
}