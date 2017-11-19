package tables;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Exceptions.ResultsReadError;

public class PlayerScoreDict {
	// dictionary that maps playerID to set of scores
	Map<Integer, PlayerScores> resultsDict;
	
	PlayerScoreDict() {
		resultsDict = new HashMap<Integer,PlayerScores>();
	}
	
	void populateScores() throws ResultsReadError, FileNotFoundException {
		// populate the dictionary by reading from results CSV file
		int i = 0;
		int playerID;
		int goals
		Scanner csvReader = new Scanner(new File("./player_results.csv"));
		csvReader.useDelimiter(",");
		if(!csvReader.hasNext())
			throw new ResultsReadError();
		while(csvReader.hasNext()) {
			for(i=0; i<5; i++) {
				csvReader.next();
			}
		}
	}
	
	int findScore(String playerID, Positions position) {
		// return the score of player, for relevant position
		return 0;
	}
}
