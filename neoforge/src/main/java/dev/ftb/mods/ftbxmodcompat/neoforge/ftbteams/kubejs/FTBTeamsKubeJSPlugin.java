package dev.ftb.mods.ftbxmodcompat.neoforge.ftbteams.kubejs;

import dev.ftb.mods.ftbteams.api.event.PlayerJoinedPartyTeamEvent;
import dev.ftb.mods.ftbteams.api.event.PlayerLeftPartyTeamEvent;
import dev.ftb.mods.ftbteams.api.neoforge.FTBTeamsEvent;
import dev.latvian.mods.kubejs.event.EventGroupRegistry;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.ScriptType;
import net.neoforged.neoforge.common.NeoForge;

public class FTBTeamsKubeJSPlugin implements KubeJSPlugin {
    @Override
    public void init() {
        NeoForge.EVENT_BUS.addListener(FTBTeamsEvent.PlayerJoinedPartyTeam.class, event -> onPlayerJoinedParty(event.getEventData()));
        NeoForge.EVENT_BUS.addListener(FTBTeamsEvent.PlayerLeftPartyTeam.class, event -> onPlayerLeftParty(event.getEventData()));
    }

    @Override
    public void registerEvents(EventGroupRegistry registry) {
        registry.register(FTBTeamsKubeJSEvents.EVENT_GROUP);
    }

    private static void onPlayerJoinedParty(PlayerJoinedPartyTeamEvent.Data event) {
        FTBTeamsKubeJSEvents.PLAYER_JOINED_PARTY.post(ScriptType.SERVER, new PlayerTeamKubeEvent(event.player(), event.team(), event.previousTeam()));
    }

    private static void onPlayerLeftParty(PlayerLeftPartyTeamEvent.Data event) {
        FTBTeamsKubeJSEvents.PLAYER_LEFT_PARTY.post(ScriptType.SERVER, new PlayerTeamKubeEvent(event.player(), event.playerTeam(), event.team()));
    }
}
