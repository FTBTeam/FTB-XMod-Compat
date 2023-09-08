package dev.ftb.mods.ftbxmodcompat.ftbquests.kubejs;

import dev.ftb.mods.ftbquests.quest.BaseQuestFile;
import dev.ftb.mods.ftbquests.quest.TeamData;

public class FTBQuestsKubeJSTeamDataWrapper extends FTBQuestsKubeJSTeamData {
	private final TeamData teamData;

	public FTBQuestsKubeJSTeamDataWrapper(TeamData d) {
		teamData = d;
	}

	public BaseQuestFile getFile() {
		return teamData.getFile();
	}

	public TeamData getData() {
		return teamData;
	}
}