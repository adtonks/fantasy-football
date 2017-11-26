package clientObjects;

import java.util.ArrayList;
import java.util.List;

// this object is read from and written to the user list CSV file
public class User {
	String username;
	String password;
	String email;
	int gameID;
	boolean isHost;
	int points;
	int week;
	List<Player> GKs; // 1 player
	List<Player> DFs; // 4 players
	List<Player> MFs; // 4 players
	List<Player> FWs; // 2 players
	List<Player> SUBs; // 6 players

	/* search through the CSV to find user info and initialize object */
	/* only called by server */
	public User(int _playerID) {
		
	}
	
	/* construct a new player object, for insertion into CSV by server */
	public User(String _username, String _password, String _email,
			int _gameID, boolean _isHost) {
		this.username = _username;
		this.password = _password;
		this.email = _email;
		this.gameID = _gameID;
		this.isHost = _isHost;
		this.points = 0;
		this.week = 0;
		GKs = null;
		DFs = null;
		MFs = null;
		FWs = null;
		SUBs = null;
	}
	
}
