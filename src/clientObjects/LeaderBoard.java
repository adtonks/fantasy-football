package clientObjects;

import java.io.Serializable;

public class LeaderBoard  implements Serializable{
	int gameID;
	// ordered array of leaders for the game
	String[] username;
	// points[i] is number of points for usernames[i]
	int[] points;
	
	LeaderBoard() {
		
	}
}
