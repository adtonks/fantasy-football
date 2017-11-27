package server;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;

import clientObjects.LeaderBoard;
import clientObjects.Player;
import clientObjects.User;
import exceptions.PlayerNotFound;
import exceptions.ResultsReadError;
import exceptions.UserNotFound;

public abstract class Cfunctions {

	static User cUserPull(String _username, String _password) throws FileNotFoundException, ResultsReadError, UserNotFound {
		// returns null if incorrect password
		User userRet = new User(_username);
		if(userRet.getPassword().equals(_password))
			return(userRet);
		else
			return(null);
	}
	
	static Player cGetPlayer(int _playerID) {
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
	
	static void cNewUser(User _newUser) throws ResultsReadError, IOException {
		Writer csvWriter;
		int i;
		String heading;
		String[] csvHeader = new String[]
				{"username", "password", "email", "gameID", "isHost",
						"points", "week", "GK0", "DF0", "DF1", "DF2",
						"DF3", "MF0", "MF1", "MF2", "MF3", "FW0", "FW1",
						"SUB0", "SUB1", "SUB2", "SUB3", "SUB4", "SUB5"};
		Scanner csvReader = new Scanner(new File("csv_tables/user_list.csv"));
		csvWriter = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream("csv_tables/user_list.csv.tmp"), "utf-8"));
		csvReader.useDelimiter(",|\\n");
		
		// check that table headers are correct
		for(i=0; i<24; i++) {
			// read first line of headers
			heading = csvReader.next();
			if(!csvHeader[i].equals(heading)) {
				csvWriter.close();
				csvReader.close();
				System.out.println("CSV file formatted incorrectly");
				throw new ResultsReadError();
			}
			csvWriter.write(heading);
			if(i != 23)
				csvWriter.write(",");
		}
		csvWriter.write("\n");
		
		while(csvReader.hasNext()) {
			for(i=0; i<24; i++) {
				csvWriter.write(csvReader.next());
				if(i != 23)
					csvWriter.write(",");
				else
					csvWriter.write("\n");
			}
		}
		
		csvWriter.write(_newUser.toCSVrow());

		csvWriter.close();
		csvReader.close();
		
		// now delete the old file and rename the temporary one
		File oldFile = new File("csv_tables/user_list.csv");
		oldFile.delete();
		File newFile = new File("csv_tables/user_list.csv.tmp");
		newFile.renameTo(oldFile);
		
	}
	
	static boolean cUserPush(User _inUser) throws ResultsReadError, IOException {
		Writer csvWriter;
		int i, inUserWk;
		String inUsername, currUsername;
		boolean userFound = false;
		String heading;
		String[] csvHeader = new String[]
				{"username", "password", "email", "gameID", "isHost",
						"points", "week", "GK0", "DF0", "DF1", "DF2",
						"DF3", "MF0", "MF1", "MF2", "MF3", "FW0", "FW1",
						"SUB0", "SUB1", "SUB2", "SUB3", "SUB4", "SUB5"};
		Scanner csvReader = new Scanner(new File("csv_tables/user_list.csv"));
		csvWriter = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream("csv_tables/user_list.csv.tmp"), "utf-8"));
		csvReader.useDelimiter(",|\\n");
		
		// check that table headers are correct
		for(i=0; i<24; i++) {
			// read first line of headers
			heading = csvReader.next();
			if(!csvHeader[i].equals(heading)) {
				csvWriter.close();
				csvReader.close();
				System.out.println("CSV file formatted incorrectly");
				throw new ResultsReadError();
			}
			csvWriter.write(heading);
			if(i != 23)
				csvWriter.write(",");
		}
		csvWriter.write("\n");
		
		inUsername = _inUser.getUsername();
		inUserWk = _inUser.getWeek();
		while(csvReader.hasNext()) {
			currUsername = csvReader.next();
			// check if usernames match
			if(currUsername.equals(inUsername)) {
				for(i=0; i<5; i++)
					csvReader.next();
				// check if weeks match
				if(csvReader.nextInt() == inUserWk) {
					userFound = true;
					csvWriter.write(_inUser.toCSVrow());
					for(i=0; i<17; i++)
						csvReader.next();
				} else {
					csvWriter.close();
					csvReader.close();
					System.out.println("Player not up-to-date (pull first)");
					return(false);
				}	
			} else {
				csvWriter.write(currUsername + ",");
				for(i=0; i<23; i++) {
					csvWriter.write(csvReader.next());
					if(i != 22)
						csvWriter.write(",");
				}
			}
			if(csvReader.hasNext())
				csvWriter.write("\n");
		}
		
		csvWriter.close();
		csvReader.close();
		
		if(!userFound) {
			return(false);
		} else {
			// now delete the old file and rename the temporary one
			File oldFile = new File("csv_tables/user_list.csv");
			oldFile.delete();
			File newFile = new File("csv_tables/user_list.csv.tmp");
			newFile.renameTo(oldFile);
			return(true);
		}
	}
	
	static boolean cUsernameExist(String _username) throws ResultsReadError, PlayerNotFound, IOException {
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
		
		// begin checking usernames
		while(csvReader.hasNext()) {
			if(csvReader.next().equals(_username)) {
				csvReader.close();
				return(true);
			}
			for(i=0; i<23; i++)
				csvReader.next();
		}
		// username was not found
		csvReader.close();
		return(false);
	}
	
	static boolean cGameIDExist(int _gameID) throws ResultsReadError, PlayerNotFound, IOException {
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
		
		// begin checking gameIDs
		while(csvReader.hasNext()) {
			for(i=0; i<3; i++)
				csvReader.next();
			if(csvReader.nextInt() == _gameID) {
				csvReader.close();
				return(true);
			}
			for(i=0; i<20; i++)
				csvReader.next();
		}
		// gameID was not found
		csvReader.close();
		return(false);
	}
	
	static LeaderBoard cGetBoard(int _gameID) throws FileNotFoundException, ResultsReadError, UserNotFound {
		return(new LeaderBoard(_gameID));
	}
	
	static int cGenGameID() throws ResultsReadError, PlayerNotFound, IOException {
		int currGameID = 1000;
		while(Cfunctions.cGameIDExist(currGameID))
			currGameID++;
		return(currGameID);
	}
}
