package clientFunctions;

import clientObjects.LeaderBoard;
import clientObjects.Player;
import clientObjects.User;

// this class holds the s prefixed functions
// the sFunctions are wrappers for client requests for c prefixed functions
public class Sfunctions {

	public static User sUserPull(String _username, String _password) {
		// returns null if incorrect password
		// check username validity using sUsernameExist first before calling
		return(null);
	}
	
	public static Player sGetPlayer(int _playerID) {
		// returns null if player cannot be found
		return(null);
	}
	
	
	public static void sNewUser(User _newUser) {
		// inputs _newUser into the CSV file on server		
	}
	
	public static boolean sUserPush(User _inUser) {
		// attempts to update user_list.csv with _inUser
		// returns true on success
		// false if the week variable do not match (server has refreshed scores)
		return(true);
	}
	
	public static boolean sUsernameExist(String _username) {
		// returns true if username exists, false otherwise
		return(true);
	}
	
	public static boolean sGameIDExist(int _gameID) {
		// returns true if gameID exists, false otherwise
		return(true);
	}
	
	public static LeaderBoard sGetBoard(int _gameID) {
		// returns the leaderboard object for given gameID
		// use sGameIDExist first for safety
		return(null);
	}
	
	public static int sGenGameID() {
		// generates a new unique gameID
		return(1005);
	}

}

