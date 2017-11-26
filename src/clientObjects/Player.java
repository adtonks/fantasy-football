package clientObjects;

public class Player {
	int playerID;
	String name;
	String nationality;
	String team;
	// note that prefPosition is an enum
	Positions prefPosition;
	String imgPath;
	
	public Player(int _playerID) {
		// search through the CSV to find player info
		// initialize object according to info in CSV
	}
	
	public boolean compareTo(Player otherPlayer){
		// compare to another football player
		if (otherPlayer.name == this.name){
			return true;
		}
		return false;
	}
		
}
