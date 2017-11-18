package tables;

import java.util.Map;

public class PlayerScoreDict {
	// dictionary that maps playerID to set of scores
	Map<String, PlayerScores> resultsDict;
	
	PlayerScoreDict() {
		
	}
	
	void populateScores() {
		// populate the dictionary by reading from results CSV file
	}
	
	int findScore(String playerID, Positions position) {
		// return the score of player, for relevant position
		return 0;
	}
}
