package clientObjects;

<<<<<<< HEAD
import java.io.Serializable;

public class LeaderBoard  implements Serializable{
	int gameID;
	// ordered array of leaders for the game
	String[] username;
	// points[i] is number of points for usernames[i]
	int[] points;
	
	LeaderBoard() {
=======
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import exceptions.ResultsReadError;
import exceptions.UserNotFound;
import server.UserPoints;

public class LeaderBoard {
	private final int gameID;
	// ordered list of (username, points) pairs in descending order
	private final List<UserPoints> userPointsList;
	private final int boardLen;

	public LeaderBoard(int _gameID) throws ResultsReadError, FileNotFoundException, UserNotFound {
		this.gameID = _gameID;
		this.userPointsList = new ArrayList<UserPoints>();
		String currUsername;
		int i;
		String[] csvHeader = new String[]
				{"username", "password", "email", "gameID", "isHost",
						"points", "week", "GK0", "DF0", "DF1", "DF2",
						"DF3", "MF0", "MF1", "MF2", "MF3", "FW0", "FW1",
						"SUB0", "SUB1", "SUB2", "SUB3", "SUB4", "SUB5"};
		Scanner csvReader = new Scanner(new File("csv_tables/user_list.csv"));
		csvReader.useDelimiter(",|\\n");
		// check that table headers are correct
		for(i=0; i<24; i++) {
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
			currUsername = csvReader.next();
			for(i=0; i<2; i++)
				csvReader.next();
			if(_gameID == csvReader.nextInt()) {
				csvReader.next();
				userPointsList.add(new UserPoints(currUsername, csvReader.nextInt()));
				for(i=0; i<18; i++)
					csvReader.next();
			} else {
				for(i=0; i<20; i++)
					csvReader.next();
			}
		}
		
		csvReader.close();
>>>>>>> 0e0f0f74e72bab5797094a6092ffe648a7af3e8d
		
		Collections.sort(userPointsList, new Comparator<UserPoints>() {
			public int compare(UserPoints up1, UserPoints up2) {
				return Integer.compare(up2.points, up1.points);
			}
		});
		
		this.boardLen = userPointsList.size();
		
	}

	public int getGameID() {
		return gameID;
	}

	public int getBoardLen() {
		return boardLen;
	}
	
	public List<UserPoints> getUserPointsList() {
		return userPointsList;
	}
}
