package clientFunctions;

import clientObjects.CSVline;
import clientObjects.LeaderBoard;
import clientObjects.Player;
import clientObjects.User;
import exceptions.ResultsReadError;
import server.TextProcessor;

// this class holds the s prefixed functions
// the sFunctions are wrappers for client requests for c prefixed functions
public abstract class Sfunctions {

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
	
	
	public static void sNewUser(User _newUser) {
		// inputs _newUser into the CSV file on server
		String request = "sNewUser:" + _newUser.toCSVrow() + ";";
		String reply = TextProcessor.parseReq(request);
		return;
	}
	
	public static boolean sUserPush(User _inUser) {
		// attempts to update user_list.csv with _inUser
		// returns true on success
		// false if the week variable do not match (server has refreshed scores)
		String request = "sUserPush:" + _inUser.toCSVrow() + ";";
		String reply = TextProcessor.parseReq(request);
		return(reply.equals("SERVER_TRU"));
	}
	
	public static boolean sUsernameExist(String _username) {
		// returns true if username exists, false otherwise
		String request = "sUsernameExist:" + _username + ";";
		String reply = TextProcessor.parseReq(request);
		return(reply.equals("SERVER_TRU"));
	}
	
	public static boolean sGameIDExist(int _gameID) {
		// returns true if gameID exists, false otherwise
		String request = "sGameIDExist:" + _gameID + ";";
		String reply = TextProcessor.parseReq(request);
		return(reply.equals("SERVER_TRU"));
	}
	
	public static LeaderBoard sGetBoard(int _gameID) {
		// returns the leaderboard object for given gameID
		// use sGameIDExist first for safety
		String request = "sGetBoard:" + _gameID + ";";
		String reply = TextProcessor.parseReq(request);
		return(new LeaderBoard(new CSVline(reply)));
	}
	
	public static int sGenGameID() {
		// generates a new unique gameID
		String request = "sGenGameID:" + ";";
		String reply = TextProcessor.parseReq(request);
		return(Integer.valueOf(reply));
	}

}

