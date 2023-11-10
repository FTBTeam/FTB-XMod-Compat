package dev.ftb.mods.ftbxmodcompat.ftbteams.kubejs;

import dev.ftb.mods.ftbteams.data.PartyTeam;
import dev.ftb.mods.ftbteams.data.Team;

import java.util.UUID;

public class KJSTeamWrapper {
    private final Team team;

    public KJSTeamWrapper(Team team) {
        this.team = team;
    }

    public UUID getId() {
        return team.getId();
    }

    public String getName() {
        return team.getDisplayName();
    }

    public boolean isPartyTeam() {
        return team instanceof PartyTeam;
    }
}
