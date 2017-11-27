package clientFunctions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import clientObjects.CSVline;
import clientObjects.LeaderBoard;
import clientObjects.Player;
import clientObjects.User;
import exceptions.ResultsReadError;
import server.TextProcessor;

/**
 * this class holds the s prefixed functions, which are wrappers 
 * for client requests for c prefixed functions
 * @author adamtonks
 *
 */
public abstract class Sfunctions {

	/**
	 * sUserPull takes a username and password and returns a valid user object
	 * @param _username, username
	 * @param _password, password
	 * @return User object
	 */
	public static User sUserPull(String _username, String _password) {
		// returns null if incorrect password
		// check username validity using sUsernameExist first before calling
		String request = "sUserPull:" + _username + "," + _password + ";";
		String reply = TextProcessor.parseReq(request);
		try {
			return(new User(new CSVline(reply)));
		} catch (ResultsReadError e) {
			System.out.println("Format error");
			e.printStackTrace();
			return(null);
		}
	}
	
	/**
	 * sGetPlayer inputs a player ID and returns a Player object
	 * @param _playerID, player ID
	 * @return Player object, null if player cannot be found
	 */
	public static Player sGetPlayer(int _playerID) {
		// returns null if player cannot be found
		String request = "sGetPlayer:" + _playerID + ";";
		String reply = TextProcessor.parseReq(request);
		try {
			return(new Player(new CSVline(reply)));
		} catch (ResultsReadError e) {
			System.out.println("Format error");
			e.printStackTrace();
			return(null);
		}
	}
	
	/**
	 * sNewUser inputs a User object and passes it along to the server to be inserted
	 * into the database as a new user.
	 * @param _newUser, User object
	 */
	public static void sNewUser(User _newUser) {
		// inputs _newUser into the CSV file on server
		String request = "sNewUser:" + _newUser.toCSVrow() + ";";
		String reply = TextProcessor.parseReq(request);
		return;
	}
	
	/**
	 * sUserPush takes a User object as an argument and actually inserts it
	 * into the csv file. 
	 * @param _inUser
	 * @return boolean value, depending if it managed to update or not
	 */
	public static boolean sUserPush(User _inUser) {
		// attempts to update user_list.csv with _inUser
		// returns true on success
		// false if the week variable do not match (server has refreshed scores)
		String request = "sUserPush:" + _inUser.toCSVrow() + ";";
		String reply = TextProcessor.parseReq(request);
		return(reply.equals("SERVER_TRU"));
	}
	
	/**
	 * sUsernameExist takes a username string as an argument and checks if such a user exists
	 * @param _username
	 * @return true if yes, no if it does not exist
	 */
	public static boolean sUsernameExist(String _username) {
		try {
		Socket mySocket = new Socket("127.0.0.1", 8080);
		PrintWriter socketWr = new PrintWriter(mySocket.getOutputStream());
		BufferedReader socketRd = new BufferedReader(new InputStreamReader(
				mySocket.getInputStream()));
		String reply;
		
		// returns true if username exists, false otherwise
		String request = "sUsernameExist:" + _username + ";";
		System.out.println("Client sends: " + request);
		socketWr.write(request);
		socketWr.close();
		System.out.println("yes");
		reply = socketRd.lines().collect(Collectors.joining("\n"));
		System.out.println("Client receives: " + reply);
		System.out.println("hello");
		mySocket.close();
		return(false);
		} catch (Exception e) {
			return(false);
		}
	}
	
	/**
	 * sGameIDExist takes a gameID as an argument and checks if such a game exists
	 * @param _gameID
	 * @return true if yes, no if it does not exist
	 */
	public static boolean sGameIDExist(int _gameID) {
		// returns true if gameID exists, false otherwise
		String request = "sGameIDExist:" + _gameID + ";";
		String reply = TextProcessor.parseReq(request);
		return(reply.equals("SERVER_TRU"));
	}
	
	/**
	 * sGetBoard takes a gameID as an argument and returns the LeaderBoard scores, rankings
	 * and such for the given game
	 * @param _gameID
	 * @return Leaderboard object
	 */
	public static LeaderBoard sGetBoard(int _gameID) {
		// returns the leaderboard object for given gameID
		// use sGameIDExist first for safety
		String request = "sGetBoard:" + _gameID + ";";
		String reply = TextProcessor.parseReq(request);
		return(new LeaderBoard(new CSVline(reply)));
	}
	
	/**
	 * sGenGameID() generates a unique ID that people can use to join games with their friends.
	 * @return a random int ID
	 */
	public static int sGenGameID() {
		// generates a new unique gameID
		String request = "sGenGameID:" + ";";
		String reply = TextProcessor.parseReq(request);
		return(Integer.valueOf(reply));
	}

}

