package clientObjects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Draft implements Serializable{
	public ArrayList<Player> remainingPlayers;
	public int draftIndex;
	public ArrayList<User> draftOrder;
	public User[] players;

	public Draft(Player[] AllPlayers, User[] players) {
		ArrayList<Player> remainingPlayers = new ArrayList<Player>();
		ArrayList<Player> draftOrder = new ArrayList<Player>();
		
		for(int i = 0;i < AllPlayers.length;i++) {
			this.remainingPlayers.add(AllPlayers[i]);
		}
		this.players = players;
		draftIndex = 0;
		
	}
 
	public void createDraftOrder() {

		// random shuffling
		int index;
		User temp;
		Random random = new Random();
		for (int i = this.players.length - 1; i > 0; i--) {
			index = random.nextInt(i + 1);
			temp = this.players[index];
			this.players[index] = this.players[i];
			this.players[i] = temp;
		}

		//snake order draft creation
		boolean snakeOrder = true;
		for (int i = 0; i < players.length * 17; i+=17) {

			if (snakeOrder) {
				for (int j = 1; j <= players.length; j++) {
					this.draftOrder.add(players[j - 1]);
				}
			} else {
				for (int j = 1; j <= players.length; j++) {
					this.draftOrder.add(players[players.length - 1]);
				}
			}
			snakeOrder = !snakeOrder;
		}

	}
	
	public void footballPlayerDrafted(Player draftedPlayer){
		
		this.remainingPlayers.remove(draftedPlayer);
		draftIndex++; 
	}
	
	

}
