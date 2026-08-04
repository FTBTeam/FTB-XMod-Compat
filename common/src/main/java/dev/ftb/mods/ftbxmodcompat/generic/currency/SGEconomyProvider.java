package dev.ftb.mods.ftbxmodcompat.generic.currency;

import dev.ftb.mods.ftblibrary.integration.currency.CurrencyProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.sirgrantd.sg_economy.api.SGEconomyApi;

public enum SGEconomyProvider implements CurrencyProvider {
    INSTANCE;

    @Override
    public String getName() {
        return "SG Economy";
    }

    @Override
    public int getTotalCurrency(Player player) {
        return (int) SGEconomyApi.getBalance(player);
    }

    @Override
    public boolean takeCurrency(Player player, int amount) {
        int coins = getTotalCurrency(player);
        if (coins >= amount) {
            SGEconomyApi.withdrawBalance(player, amount);
            return true;
        }
        return false;
    }

    @Override
    public void giveCurrency(Player player, int amount) {
        SGEconomyApi.depositBalance(player, amount);
    }

    @Override
    public Component coinName(boolean plural) {
        return Component.translatable(plural ? "text.coins" : "text.coin");
    }
}
