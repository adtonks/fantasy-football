package clientFunctions;

import clientObjects.LeaderBoard;
import clientObjects.Player;
import clientObjects.User;

// this class holds the s prefixed functions
// the sFunctions are wrappers for client requests for c prefixed functions
public abstract class Sfunctions {

	public static User sUserPull(String _username, String _password) {
		// returns null if incorrect password
		// check username validity using sUsernameExist first before calling
		String request = "sUserPull:" + _username + "," + _password + ";";
		System.out.println(request);
		return(null);
	}
	
	public static Player sGetPlayer(int _playerID) {
		// returns null if player cannot be found
		String request = "sGetPlayer:" + _playerID + ";";
		System.out.println(request);
		return(null);
	}
	
	
	public static void sNewUser(User _newUser) {
		// inputs _newUser into the CSV file on server
		String request = "sNewUser:" + _newUser.toCSVrow() + ";";
		System.out.println(request);
	}
	
	public static boolean sUserPush(User _inUser) {
		// attempts to update user_list.csv with _inUser
		// returns true on success
		// false if the week variable do not match (server has refreshed scores)
		String request = "sUserPush:" + _inUser.toCSVrow() + ";";
		System.out.println(request);
		return(true);
	}
	
	public static boolean sUsernameExist(String _username) {
		// returns true if username exists, false otherwise
		String request = "sUsernameExist:" + _username + ";";
		System.out.println(request);
		return(true);
	}
	
	public static boolean sGameIDExist(int _gameID) {
		// returns true if gameID exists, false otherwise
		String request = "sGameIDExist:" + _gameID + ";";
		System.out.println(request);
		return(true);
	}
	
	public static LeaderBoard sGetBoard(int _gameID) {
		// returns the leaderboard object for given gameID
		// use sGameIDExist first for safety
		String request = "sGetBoard:" + _gameID + ";";
		System.out.println(request);
		return(null);
	}
	
	public static int sGenGameID() {
		// generates a new unique gameID
		String request = "sGenGameID:" + ";";
		System.out.println(request);
		return(1005);
	}

}

