package dev.ftb.mods.ftbxmodcompat.ftbquests.kubejs;

import dev.ftb.mods.ftbquests.quest.QuestFile;
import dev.ftb.mods.ftbquests.quest.TeamData;

public class FTBQuestsKubeJSTeamDataWrapper extends FTBQuestsKubeJSTeamData {
	private final TeamData teamData;

	public FTBQuestsKubeJSTeamDataWrapper(TeamData d) {
		teamData = d;
	}

	public QuestFile getFile() {
		return teamData.getFile();
	}

	public TeamData getData() {
		return teamData;
	}
}