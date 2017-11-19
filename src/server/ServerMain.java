package server;

import java.io.FileNotFoundException;

import exceptions.PlayerNotFound;
import exceptions.ResultsReadError;
import tables.PlayerScoreDict;
import tables.Positions;

public abstract class ServerMain {

	public static void main(String[] args) {
		// this starts the server
		// initialise the dictionary to store player points
		PlayerScoreDict ScoresDict = new PlayerScoreDict();
		try {
			ScoresDict.populateScores();
		} catch (FileNotFoundException | ResultsReadError e) {
			e.printStackTrace();
		}
		try {
			System.out.println("Score of Menda as DF is " +
					ScoresDict.findScore(2, Positions.DF));
			System.out.println("Score of Tanaka as MF is " +
					ScoresDict.findScore(5, Positions.MF));
		} catch (PlayerNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
