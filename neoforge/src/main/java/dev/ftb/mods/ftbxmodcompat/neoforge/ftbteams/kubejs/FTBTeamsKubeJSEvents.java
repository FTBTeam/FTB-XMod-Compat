package dev.ftb.mods.ftbxmodcompat.neoforge.ftbteams.kubejs;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;

public interface FTBTeamsKubeJSEvents {
    EventGroup EVENT_GROUP = EventGroup.of("FTBTeamsEvents");

    EventHandler PLAYER_JOINED_PARTY = EVENT_GROUP.server("playerJoinedParty", () -> PlayerTeamKubeEvent.class);
    EventHandler PLAYER_LEFT_PARTY = EVENT_GROUP.server("playerLeftParty", () -> PlayerTeamKubeEvent.class);
}
