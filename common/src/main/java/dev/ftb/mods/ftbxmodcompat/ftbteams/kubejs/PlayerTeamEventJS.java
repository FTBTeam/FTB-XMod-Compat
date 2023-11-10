package dev.ftb.mods.ftbxmodcompat.ftbteams.kubejs;

import dev.ftb.mods.ftbteams.api.Team;
import dev.latvian.mods.kubejs.player.PlayerEventJS;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class PlayerTeamEventJS extends PlayerEventJS {
    private final ServerPlayer player;
    private final Team currentTeam;
    private final Team prevTeam;

    public PlayerTeamEventJS(ServerPlayer player, Team currentTeam, Team prevTeam) {
        this.player = player;
        this.currentTeam = currentTeam;
        this.prevTeam = prevTeam;
    }

    @Override
    public Player getEntity() {
        return player;
    }

    public KJSTeamWrapper getCurrentTeam() {
        return new KJSTeamWrapper(currentTeam);
    }

    public KJSTeamWrapper getPrevTeam() {
        return new KJSTeamWrapper(prevTeam);
    }
}
