package dev.ftb.mods.ftbxmodcompat.ftbteams.kubejs;

import dev.ftb.mods.ftbteams.api.Team;

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
        return team.getShortName();
    }

    public boolean isPartyTeam() {
        return team.isPartyTeam();
    }
}
