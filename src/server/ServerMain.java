package server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import clientObjects.LeaderBoard;
import clientObjects.Player;
import clientObjects.Positions;
import clientObjects.User;
import exceptions.PlayerNotFound;
import exceptions.ResultsReadError;
import exceptions.UserNotFound;

public abstract class ServerMain {

	public static void main(String[] args) throws ResultsReadError, UserNotFound, IOException {
		// this starts the server
		// initialize the dictionary to store player points
		int i;
		PlayerScoreDict ScoresDict = new PlayerScoreDict();
		User test_user;
		Player test_player;
		LeaderBoard leadPrev, leadNew;
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
		test_user = new User("adtonks");
		System.out.println(test_user.getEmail());
		test_player = new Player(63);
		System.out.println(test_player.getTeam());
		
		System.out.println("player 1 " + test_user.getPlayer(Positions.DF, 3).getName());
		System.out.println("player 2 " + test_user.getPlayer(Positions.SUB, 5).getName());
		test_user.substitute(test_user.getPlayer(Positions.DF, 3).getPlayerID(),
				test_user.getPlayer(Positions.SUB, 5).getPlayerID());
		System.out.println("player 1 " + test_user.getPlayer(Positions.DF, 3).getName());
		System.out.println("player 2 " + test_user.getPlayer(Positions.SUB, 5).getName());
		
		leadPrev = new LeaderBoard(1002);
		for(i=0; i<leadPrev.getBoardLen(); i++) {
			System.out.println(leadPrev.getUserPointsList().get(i).username);
			System.out.println(leadPrev.getUserPointsList().get(i).points);
		}
		
		try {
			update();
		} catch (PlayerNotFound e) {
			e.printStackTrace();
		}
		
		leadNew = new LeaderBoard(1002);
		for(i=0; i<leadNew.getBoardLen(); i++) {
			System.out.println(leadNew.getUserPointsList().get(i).username);
			System.out.println(leadNew.getUserPointsList().get(i).points);
		}
		
	}
	
	public Player cGetPlayer(int _playerID) {
		// returns null if player cannot be found
		try {
			return(new Player(_playerID));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return(null);
		} catch (ResultsReadError e) {
			e.printStackTrace();
			return(null);
		} catch (UserNotFound e) {
			return(null);
		}
	}
	
	private static void update() throws ResultsReadError, PlayerNotFound, IOException {
		PlayerScoreDict scoreDict = new PlayerScoreDict();
		Writer csvWriter;
		int i, j, wkPoints, wkNew;
		List<Integer> userPointsList = new ArrayList<Integer>();
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
		scoreDict.populateScores();
		while(csvReader.hasNext()) {
			for(i=0; i<5; i++)
				csvReader.next();
			wkPoints = csvReader.nextInt();
			csvReader.next();
			for(i=0; i<1; i++)
				wkPoints += scoreDict.findScore(csvReader.nextInt(), Positions.GK);
			for(i=0; i<4; i++)
				wkPoints += scoreDict.findScore(csvReader.nextInt(), Positions.DF);
			for(i=0; i<4; i++)
				wkPoints += scoreDict.findScore(csvReader.nextInt(), Positions.MF);
			for(i=0; i<2; i++)
				wkPoints += scoreDict.findScore(csvReader.nextInt(), Positions.FW);
			for(i=0; i<6; i++)
				csvReader.next();
			userPointsList.add(wkPoints);
		}
		csvReader.close();
		// now we need to print the new scores
		csvReader = new Scanner(new File("csv_tables/user_list.csv"));
		csvReader.useDelimiter(",|\\n");
		csvWriter = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream("csv_tables/user_list.csv.tmp"), "utf-8"));
		
		while(csvReader.hasNext()) {
			for(i=0; i<24; i++) {
				csvWriter.write(csvReader.next());
				if(i != 23)
					csvWriter.write(",");
				else
					csvWriter.write("\n");
			}
			for(i=0; i<userPointsList.size(); i++) {
				for(j=0; j<5; j++)
					csvWriter.write(csvReader.next() + ",");
				// write the new points
				csvWriter.write(userPointsList.get(i) + ",");
				csvReader.next();
				// write the new week
				wkNew = csvReader.nextInt() + 1;
				csvWriter.write(wkNew + ",");
				for(j=0; j<17; j++) {
					csvWriter.write(csvReader.next());
					if(j != 16) {
						csvWriter.write(",");
					} else {
						if(csvReader.hasNext())
							csvWriter.write("\n");
					}
				}
			}

		}
		csvWriter.close();
		csvReader.close();
		
		// now delete the old file and rename the temporary one
		File oldFile = new File("csv_tables/user_list.csv");
		oldFile.delete();
		File newFile = new File("csv_tables/user_list.csv.tmp");
		newFile.renameTo(oldFile);
		
	}

}
