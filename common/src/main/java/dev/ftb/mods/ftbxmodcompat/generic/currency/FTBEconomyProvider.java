package dev.ftb.mods.ftbxmodcompat.generic.currency;

import dev.ftb.mods.ftbeconomy.api.FTBEconomyAPI;
import dev.ftb.mods.ftbeconomy.api.account.PlayerAccount;
import dev.ftb.mods.ftbeconomy.api.resource.TradeResources;
import dev.ftb.mods.ftblibrary.integration.currency.CurrencyProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public enum FTBEconomyProvider implements CurrencyProvider {
    INSTANCE;

    @Override
    public String getName() {
        return "FTB Economy";
    }

    @Override
    public long getTotalCurrency(Player player) {
        return player instanceof ServerPlayer sp ?
                getAccount(sp).getBalance() :
                FTBEconomyAPI.clientApi().getPlayerBalance();
    }

    @Override
    public boolean takeCurrency(Player player, long amount) {
        return player instanceof ServerPlayer sp && getAccount(sp).withdrawFunds(amount);
    }

    @Override
    public void giveCurrency(Player player, long amount) {
        if (player instanceof ServerPlayer sp) {
            getAccount(sp).depositFunds(amount);
        }
    }

    @Override
    public Component coinName(boolean plural) {
        return TradeResources.ftbCoin().getDescription();
    }

    private static PlayerAccount getAccount(ServerPlayer sp) {
        return FTBEconomyAPI.api().getAccountManager().getOrCreatePlayerAccount(sp);
    }
}
