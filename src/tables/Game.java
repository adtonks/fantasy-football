import java.util.ArrayList;

public class Game {

public ArrayList<User> players;
public int currentWeek;
public User[] leaderBoard;
public User[] winners;
public String roomCode;
public User host;
public boolean ended;

public Game(User host, String roomCode) {
	this.host = host;
	this.roomCode = roomCode;
}


 
}
