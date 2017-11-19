package tables;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import exceptions.PlayerNotFound;
import exceptions.ResultsReadError;

public class PlayerScoreDict {
	// dictionary that maps playerID to set of scores
	private Map<Integer, PlayerScores> resultsDict;
	
	public PlayerScoreDict() {
		// initialize the dictionary using a hash table
		this.resultsDict = new HashMap<Integer,PlayerScores>();
	}
	
	public void populateScores() throws ResultsReadError, FileNotFoundException {
		// populate the dictionary by reading from results CSV file
		// the CSV files we read are simple, so avoid using external library
		int i = 0;
		int[] playerInfo = new int[5];
		int GKpts, DFpts, MFpts, FWpts;
		String[] csvHeader = new String[]
				{"playerID", "goals", "assists", "tackles", "saves"};
		Scanner csvReader = new Scanner(new File("csv_tables/player_results.csv"));
		csvReader.useDelimiter(",");
		// check that table headers are correct
		for(i=0; i<5; i++) {
			// read first line of headers
			if(!csvHeader[i].equals(csvReader.next())) {
				csvReader.close();
				System.out.println("CSV file formatted incorrectly");
				throw new ResultsReadError();
			}
		}
		if(!csvReader.hasNext()) {
			csvReader.close();
			System.out.println("CSV file empty");
			throw new ResultsReadError();
		}
		while(csvReader.hasNext()) {
			for(i=0; i<5; i++) {
				// throw exception if complete player info is not present
				if(csvReader.hasNext()) {
					playerInfo[i] = csvReader.nextInt();
					System.out.println("Player information incomplete");
				} else {
					csvReader.close();
					throw new ResultsReadError();
				}
				// calculate the scores for the player
				GKpts = 20*playerInfo[1] + 5*playerInfo[2] +
						2*playerInfo[3] + 2*playerInfo[4];
				DFpts = 5*playerInfo[1] + 5*playerInfo[2] +
						2*playerInfo[3] + 0*playerInfo[4];
				MFpts = 6*playerInfo[1] + 6*playerInfo[2] +
						1*playerInfo[3] + 0*playerInfo[4];
				FWpts = 10*playerInfo[1] + 4*playerInfo[2] +
						1*playerInfo[3] + 0*playerInfo[4];
				// input data into dictionary
				this.resultsDict.put(playerInfo[0],
						new PlayerScores(GKpts, DFpts, MFpts, FWpts));
			}
		}
		csvReader.close();
	}
	
	public int findScore(int _playerID, Positions _position) throws PlayerNotFound {
		// return the score of player, for relevant position
		PlayerScores scores = this.resultsDict.get(_playerID);
		if(scores == null)
			throw new PlayerNotFound();
		switch(_position) {
		case GK:
			return(scores.GKpts);
		case DF:
			return(scores.DFpts);
		case MF:
			return(scores.MFpts);
		case FW:
			return(scores.FWpts);
		default:
			// could occur if points for SUB are requested
			return(0);
		}
	}
		
}
