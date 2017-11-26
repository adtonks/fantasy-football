package tables;

public class Player {
	int playerID;
	String name;
	String nationality;
	String team;
	Positions prefPosition;
	String imgPath;
	
	public Player(int _playerID) {
		// search through the CSV to find player info
		// initialize object according to info in CSV
	}
	
	public void writePlayer() {
		// update this user in the CSV file
	}
	
	public boolean compareTo(Player otherPlayer){
		// compare to another football player
		if (otherPlayer.name == this.name){
			return true;
		}
		return false;
	}
		
}
