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

/**
 * C Prefixed functions that the client requests for, but are found on the server side
 * @author adtonks
 *
 */
public abstract class Cfunctions {
	
	/**
	 * cUserPull takes a username and password and returns a valid user object
	 * @param _username, username
	 * @param _password, password
	 * @return User object
	 * @throws FileNotFoundException, error
	 * @throws ResultsReadError, error
	 * @throws UserNotFound, error
	 */
	static User cUserPull(String _username, String _password) throws FileNotFoundException, ResultsReadError, UserNotFound {
		// returns null if incorrect password
		User userRet = new User(_username);
		if(userRet.getPassword().equals(_password))
			return(userRet);
		else
			return(null);
	}
	
	/**
	 * cGetPlayer inputs a player ID and returns a Player object
	 * @param _playerID, player ID
	 * @return Player object, null if player cannot be found
	 */
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
	
	/**
	 * cNewUser inputs a User object and passes it along to the server to be inserted
	 * into the database as a new user.
	 * @param _newUser, User object
	 * @throws ResultsReadError, results are not read correctly
	 * @throws IOException
	 */
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
	
	/**
	 * cUserPush takes a User object as an argument and actually inserts it
	 * into the csv file. 
	 * @param _inUser, the User object
	 * @return boolean value, depending if it managed to update or not
	 */
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
	
	/**
	 * cUsernameExist takes a username string as an argument and checks if such a user exists
	 * @param _username
	 * @return true if yes, no if it does not exist
	 */
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
	
	/**
	 * cGameIDExist takes a gameID as an argument and checks if such a game exists
	 * @param _gameID
	 * @return true if yes, no if it does not exist
	 */
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
	
	/**
	 * cGetBoard takes a gameID as an argument and returns the LeaderBoard scores, rankings
	 * and such for the given game
	 * @param _gameID
	 * @return Leaderboard object
	 */
	static LeaderBoard cGetBoard(int _gameID) throws FileNotFoundException, ResultsReadError, UserNotFound {
		return(new LeaderBoard(_gameID));
	}
	
	/**
	 * sGenGameID() generates a unique ID that people can use to join games with their friends.
	 * @return a random int ID
	 */
	static int cGenGameID() throws ResultsReadError, PlayerNotFound, IOException {
		int currGameID = 1000;
		while(Cfunctions.cGameIDExist(currGameID))
			currGameID++;
		return(currGameID);
	}
}
