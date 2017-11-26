package tables;

import java.util.ArrayList;
import java.util.List;

// this object is read from and written to the user list CSV file
public class User {
	String username;
	String password;
	String email;
	String gameID;
	int points;
	List<Player> GK; // 1 player
	List<Player> DF; // 4 players
	List<Player> MF; // 4 players
	List<Player> FW; // 2 players
	List<Player> SUB; // 6 players

	public User(int _playerID) {
		// search through the CSV to find user info
		// initialize object according to info in CSV
	}
	
	public User(String username, String password, String email) {
		//User object created when a user signs up
		this.username = username;
		this.password = password;
		this.email = email;
		//implement store to CSV file
		
	}
	
	public void writeUser() {
		// update this user in the CSV file
	}
	

}
