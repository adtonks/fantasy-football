/**
* Represents the draft stage of a game 
* @author Logan Ye
* @version 1.0, November 27th
*/

package clientObjects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Draft implements Serializable{
	/**
	* Stores the remaining players in the draft
	*/
	public ArrayList<Player> remainingPlayers;
	/**
	* Tracks the index of the current player that should be drafting
	*/
	public int draftIndex;
	/**
	* Stores the order of the draft by user
	*/
	public ArrayList<User> draftOrder;
	/**
	* Stores the users who will be drafting
	*/
	public User[] players;

	/**
	* Constructor for the Draft class
	* @param AllPlayers all the football players in the league
	* @param all the users who will be playing in this game of fantasy S-League
	* @param 
	* @return 
	* @throws 
	*/
	public Draft(Player[] AllPlayers, User[] players, User current) {
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
	
	public boolean checkifDrafting(String Username) {
		if(draftOrder.get(draftIndex).getUsername().equals(Username)){
			return true;
		}
		else {
			return false;
		}
	}
	
	public static Draft draftPull(int gameID) {
		return(null);
	}
	
	

}
