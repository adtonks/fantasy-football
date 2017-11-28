package clientObjects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import exceptions.IndexDoesNotExist;
import exceptions.PlayerNotFound;
import exceptions.ResultsReadError;
import exceptions.UserNotFound;

/**
 * This class, the User object, is red from and written to the user list CSV file and is a
 * representation of the users playing the fantasy football game.
 * @author adamtonks
 *
 */
// this object is read from and written to the user list CSV file
public class User  implements Serializable {
	private final String username;
	private final String password;
	private final String email;
	private final int gameID;
	private boolean hasDrafted;
	private final int points;
	private final int week;
	private final List<Player> GKs; // 1 player
	private final List<Player> DFs; // 4 players
	private final List<Player> MFs; // 4 players
	private final List<Player> FWs; // 2 players
	private final List<Player> SUBs; // 6 players

	/**
	 * A User takes in a username and returns the user object
	 * @param _username
	 * @throws ResultsReadError, results are not read correctly
	 * @throws FileNotFoundException, file is not found
	 * @throws UserNotFound, user is not found
	 * 
	 */
	
	//search through the CSV to find user info and initialize object 
	// only called by server 
	public User(String _username) throws ResultsReadError,
	FileNotFoundException,UserNotFound {
		int i, ID;
		String[] csvHeader = new String[]
				{"username", "password", "email", "gameID", "isHost",
						"points", "week", "GK0", "DF0", "DF1", "DF2",
						"DF3", "MF0", "MF1", "MF2", "MF3", "FW0", "FW1",
						"SUB0", "SUB1", "SUB2", "SUB3", "SUB4", "SUB5"};
		Scanner csvReader = new Scanner(new File("csv_tables/user_list.csv"));
		csvReader.useDelimiter(",|\\n");
		// check that table headers are correct
		for(i=0; i<24; i++) {
			// read first line of headers
			if(!csvHeader[i].equals(csvReader.next())) {
				csvReader.close();
				System.out.println("CSV file formatted incorrectly");
				throw new ResultsReadError();
			}

		}
		if(!csvReader.hasNext()) {
			csvReader.close();
			System.out.println("CSV file empty");
			throw new ResultsReadError();
		}
		this.GKs = new ArrayList<Player>();
		this.DFs = new ArrayList<Player>();
		this.MFs = new ArrayList<Player>();
		this.FWs = new ArrayList<Player>();
		this.SUBs = new ArrayList<Player>();
		try {
			while(!csvReader.hasNext(_username)) {
				for(i=0; i<24; i++)
					csvReader.next();
			}
		} catch(NoSuchElementException e) {
			System.out.println("User not found");
			csvReader.close();
			throw new UserNotFound();
		}
		// throw exception if complete player info is not present
		try {
			this.username = csvReader.next();
			this.password = csvReader.next();
			this.email = csvReader.next();
			this.gameID = csvReader.nextInt();
			this.hasDrafted = (csvReader.nextInt() == 1);
			this.points = csvReader.nextInt();
			this.week = csvReader.nextInt();
			for(i=0; i<1; i++) {
				ID = csvReader.nextInt();
				if(ID != -1)
					GKs.add(new Player(ID));
				else
					GKs.add(null);
			}
			for(i=0; i<4; i++) {
				ID = csvReader.nextInt();
				if(ID != -1)
					DFs.add(new Player(ID));
				else
					DFs.add(null);
			}
			for(i=0; i<4; i++) {
				ID = csvReader.nextInt();
				if(ID != -1)
					MFs.add(new Player(ID));
				else
					MFs.add(null);
			}
			for(i=0; i<2; i++) {
				ID = csvReader.nextInt();
				if(ID != -1)
					FWs.add(new Player(ID));
				else
					FWs.add(null);
			}
			for(i=0; i<6; i++) {
				ID = csvReader.nextInt();
				if(ID != -1)
					SUBs.add(new Player(ID));
				else
					SUBs.add(null);	
			}
		} catch(NoSuchElementException e) {
			System.out.println("User information incomplete");
			csvReader.close();
			throw new ResultsReadError();
		}
		csvReader.close();
	}
	
	/**
	 * Constructs the User object from the CSV line
	 * @param input, the CSV line
	 * @throws ResultsReadError, results are not read correctly
	 */
	// construct User object from csv line
	public User(CSVline input) throws ResultsReadError {
		Scanner csvReader = new Scanner(input.string);
		int i, ID;
		csvReader.useDelimiter(",");

		this.GKs = new ArrayList<Player>();
		this.DFs = new ArrayList<Player>();
		this.MFs = new ArrayList<Player>();
		this.FWs = new ArrayList<Player>();
		this.SUBs = new ArrayList<Player>();
		// throw exception if complete player info is not present
		try {
			this.username = csvReader.next();
			this.password = csvReader.next();
			this.email = csvReader.next();
			this.gameID = csvReader.nextInt();
			this.hasDrafted = (csvReader.nextInt() == 1);
			this.points = csvReader.nextInt();
			this.week = csvReader.nextInt();
			for(i=0; i<1; i++) {
				ID = csvReader.nextInt();
				if(ID != -1)
					GKs.add(new Player(ID));
				else
					GKs.add(null);
			}
			for(i=0; i<4; i++) {
				ID = csvReader.nextInt();
				if(ID != -1)
					DFs.add(new Player(ID));
				else
					DFs.add(null);
			}
			for(i=0; i<4; i++) {
				ID = csvReader.nextInt();
				if(ID != -1)
					MFs.add(new Player(ID));
				else
					MFs.add(null);
			}
			for(i=0; i<2; i++) {
				ID = csvReader.nextInt();
				if(ID != -1)
					FWs.add(new Player(ID));
				else
					FWs.add(null);
			}
			for(i=0; i<6; i++) {
				ID = csvReader.nextInt();
				if(ID != -1)
					SUBs.add(new Player(ID));
				else
					SUBs.add(null);
			}				
		} catch(NoSuchElementException | FileNotFoundException | ResultsReadError | UserNotFound e) {
			System.out.println("User information incomplete");
			csvReader.close();
			throw new ResultsReadError();
		}
		csvReader.close();
	}
	
	/**
	 * Constructs a new user object for insertion into CSV by server
	 * @param _username, name of user
	 * @param _password, password
	 * @param _email, email address
	 * @param _gameID, unique game ID
	 * @param _isHost, whether the user created the game or not
	 */
	/* construct a new player object, for insertion into CSV by server */
	public User(String _username, String _password, String _email,
			int _gameID) {
		int i;
		this.username = _username;
		this.password = _password;
		this.email = _email;
		this.gameID = _gameID;
		this.hasDrafted = false;
		this.points = 0;
		this.week = 0;
		this.GKs = new ArrayList<Player>();
		this.DFs = new ArrayList<Player>();
		this.MFs = new ArrayList<Player>();
		this.FWs = new ArrayList<Player>();
		this.SUBs = new ArrayList<Player>();
		for(i=0; i<1; i++)
			GKs.add(null);
		for(i=0; i<4; i++)
			DFs.add(null);
		for(i=0; i<4; i++)
			MFs.add(null);
		for(i=0; i<2; i++)
			FWs.add(null);
		for(i=0; i<6; i++)
			SUBs.add(null);	
	}
	
	/**
	 * Getter for Points
	 * @return points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * Getter for the week
	 * @return week number
	 */
	public int getWeek() {
		return week;
	}

	/**
	 * Getter for the username
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Getter for the user's password
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Getter for the email
	 * @return String with email
	 */
	public String getEmail() {
		return email;
	}

	/** 
	 * Getter for the unique game ID
	 * @return game ID
	 */
	public int getGameID() {
		return gameID;
	}

	/** 
	 * Getter for whether the user has drafted
	 * @return boolean value
	 */
	public boolean hasDrafted() {
		return hasDrafted;
	}
	
	/** 
	 * Getter for whether the user has drafted
	 * @return boolean value
	 */
	public void setDraftTrue() {
		this.hasDrafted = true;
	}
	
	/**
	 * Gets the player in the goal keeper position
	 * @param index, zero for goalie
	 * @return Player object
	 * @throws IndexDoesNotExist, there is no goalie
	 */
	public Player getGK(int index) throws IndexDoesNotExist {
		if((index<0) || (0<index))
			throw new IndexDoesNotExist();
		return(this.GKs.get(index));
	}
	
	/**
	 * Gets the players in defender positions
	 * @param index, 4 defenders
	 * @return Player object
	 * @throws IndexDoesNotExist, there are no defenders
	 */
	public Player getDF(int index) throws IndexDoesNotExist {
		if((index<0) || (3<index))
			throw new IndexDoesNotExist();
		return(this.DFs.get(index));
	}
	
	/**
	 * Gets the players in midfielder positions
	 * @param index, 4 midfielder
	 * @return Player object
	 * @throws IndexDoesNotExist, there are no midfielders
	 */
	public Player getMF(int index) throws IndexDoesNotExist {
		if((index<0) || (3<index))
			throw new IndexDoesNotExist();
		return(this.MFs.get(index));
	}
	
	/**
	 * Gets the players in forward positions
	 * @param index, 4 forward
	 * @return Player object
	 * @throws IndexDoesNotExist, there are no forward
	 */
	public Player getFW(int index) throws IndexDoesNotExist {
		if((index<0) || (1<index))
			throw new IndexDoesNotExist();
		return(this.FWs.get(index));
	}
	
	/**
	 * Gets the players in substitutes positions
	 * @param index, 4 defenders
	 * @return Player object
	 * @throws IndexDoesNotExist, there are no substitutes
	 */
	public Player getSUB(int index) throws IndexDoesNotExist {
		if((index<0) || (5<index))
			throw new IndexDoesNotExist();
		return(this.SUBs.get(index));
	}
	
	/**
	 * Method to find the player by ID
	 * @param playerID, int
	 * @return Player object
	 * @throws PlayerNotFound, player does not exist
	 */
	public Player findPlayerByID(int playerID) throws PlayerNotFound {
		// returns the player if player ID exists, exception otherwise
		int i;
		for(i=0; i<1; i++) {
			if(this.GKs.get(i) != null)
				if(this.GKs.get(i).getPlayerID() == playerID)
					return(this.GKs.get(i));
		}	
		for(i=0; i<4; i++) {
			if(this.DFs.get(i) != null)
				if(this.DFs.get(i).getPlayerID() == playerID)
					return(this.DFs.get(i));
		}		
		for(i=0; i<4; i++) {
			if(this.MFs.get(i) != null)
				if(this.MFs.get(i).getPlayerID() == playerID)
					return(this.MFs.get(i));
		}
		for(i=0; i<2; i++) {
			if(this.FWs.get(i) != null)
				if(this.FWs.get(i).getPlayerID() == playerID)
					return(this.FWs.get(i));
		}
		for(i=0; i<6; i++) {
			if(this.SUBs.get(i) != null)
				if(this.SUBs.get(i).getPlayerID() == playerID)
					return(this.SUBs.get(i));
		}
		throw new PlayerNotFound();
	}
	
	/**
	 * Method to insert the player into the ID
	 * @param newPlayer, player object
	 * @param playerID1, the player ID of one player
	 * @param playerID2, player ID of the second
	 * @param in_index, insert first player at index of second
	 * @return
	 */
	private int insertPlayerIntoID(Player newPlayer, int playerID1,
			int playerID2, int in_index) {
		// returns the player if playerID1 exists, null otherwise
		int i;
		// is player1 before or after player2?
		int index = 1;
		for(i=0; i<1; i++) {
			if(this.GKs.get(i).getPlayerID() == playerID2)
				index = 0;
			if(this.GKs.get(i).getPlayerID() == playerID1)
				if(in_index == 0) {
					this.GKs.set(i, newPlayer);
					return(index);
				} else {
					in_index = 0;
				}
		}	
		for(i=0; i<4; i++) {
			if(this.DFs.get(i).getPlayerID() == playerID2)
				index = 0;
			if(this.DFs.get(i).getPlayerID() == playerID1)
				if(in_index == 0) {
					this.DFs.set(i, newPlayer);
					return(index);
				} else {
					in_index = 0;
				}
		}		
		for(i=0; i<4; i++) {
			if(this.MFs.get(i).getPlayerID() == playerID2)
				index = 0;
			if(this.MFs.get(i).getPlayerID() == playerID1)
				if(in_index == 0) {
					this.MFs.set(i, newPlayer);
					return(index);
				} else {
					in_index = 0;
				}
		}
		for(i=0; i<2; i++) {
			if(this.FWs.get(i).getPlayerID() == playerID2)
				index = 0;
			if(this.FWs.get(i).getPlayerID() == playerID1)
				if(in_index == 0) {
					this.FWs.set(i, newPlayer);
					return(index);
				} else {
					in_index = 0;
				}
		}
		for(i=0; i<6; i++) {
			if(this.SUBs.get(i).getPlayerID() == playerID2)
				index = 0;
			if(this.SUBs.get(i).getPlayerID() == playerID1)
				if(in_index == 0) {
					this.SUBs.set(i, newPlayer);
					return(index);
				} else {
					in_index = 0;
				}
		}
		return(index);
	}
	
	/**
	 * Swop player positions
	 * @param playerID1, player one
	 * @param playerID2, player two
	 */
	public void substitute(int playerID1, int playerID2) {
		Player player1 = null;
		Player player2 = null;
		try {
			player1 = findPlayerByID(playerID1);
			player2 = findPlayerByID(playerID2);
		} catch (PlayerNotFound e) {
			System.out.println("Players not found");
			e.printStackTrace();
		}
		insertPlayerIntoID(player2, playerID1, playerID2,
				insertPlayerIntoID(player1, playerID2, playerID1, 0));
	}
	
	/**
	 * Get the player by his preferred position and index
	 * @param position, preferred position
	 * @param index, index of player
	 * @return the Player object
	 */
	public Player getPlayer(Positions position, int index) {
		switch(position) {
		case GK:
			return(this.GKs.get(index));
		case DF:
			return(this.DFs.get(index));
		case MF:
			return(this.MFs.get(index));
		case FW:
			return(this.FWs.get(index));
		case SUB:
			return(this.SUBs.get(index));
		default:
			return(null);	
		}
	}
	
	/**
	 * Converts all the user details into a CSV line
	 * @return one whole string with all the data
	 */
	public String toCSVrow() {
		return(this.username + "," + this.password + "," + this.email + "," +
				this.gameID + "," + (this.hasDrafted?1:0) + "," +
				this.points + "," + this.week + "," +
				((this.GKs.get(0)==null)?-1:this.GKs.get(0).getPlayerID()) + "," +
				((this.DFs.get(0)==null)?-1:this.DFs.get(0).getPlayerID()) + "," +
				((this.DFs.get(1)==null)?-1:this.DFs.get(1).getPlayerID()) + "," +
				((this.DFs.get(2)==null)?-1:this.DFs.get(2).getPlayerID()) + "," +
				((this.DFs.get(3)==null)?-1:this.DFs.get(3).getPlayerID()) + "," +
				((this.MFs.get(0)==null)?-1:this.MFs.get(0).getPlayerID()) + "," +
				((this.MFs.get(1)==null)?-1:this.MFs.get(1).getPlayerID()) + "," +
				((this.MFs.get(2)==null)?-1:this.MFs.get(2).getPlayerID()) + "," +
				((this.MFs.get(3)==null)?-1:this.MFs.get(3).getPlayerID()) + "," + 
				((this.FWs.get(0)==null)?-1:this.FWs.get(0).getPlayerID()) + "," +
				((this.FWs.get(1)==null)?-1:this.FWs.get(1).getPlayerID()) + "," +
				((this.SUBs.get(0)==null)?-1:this.SUBs.get(0).getPlayerID()) + "," +
				((this.SUBs.get(1)==null)?-1:this.SUBs.get(1).getPlayerID()) + "," +
				((this.SUBs.get(2)==null)?-1:this.SUBs.get(2).getPlayerID()) + "," +
				((this.SUBs.get(3)==null)?-1:this.SUBs.get(3).getPlayerID()) + "," +
				((this.SUBs.get(4)==null)?-1:this.SUBs.get(4).getPlayerID()) + "," +
				((this.SUBs.get(5)==null)?-1:this.SUBs.get(5).getPlayerID()));
	}
	
	/**
	 * Takes an array of player IDs that are ordered and sets them accordingly in the user object
	 * @param playerIDs, unique player IDs
	 */
	public void insertArr(int[] playerIDs) {
		// returns the player if playerID1 exists, null otherwise
		int i;
		try {
			for(i=0; i<1; i++)
				this.GKs.set(i, new Player(playerIDs[i]));
			for(i=0; i<4; i++)
				this.DFs.set(i, new Player(playerIDs[i+1]));
			for(i=0; i<4; i++)
				this.MFs.set(i, new Player(playerIDs[i+5]));
			for(i=0; i<2; i++)
				this.FWs.set(i, new Player(playerIDs[i+9]));
			for(i=0; i<6; i++)
				this.SUBs.set(i, new Player(playerIDs[i+11]));
		} catch (FileNotFoundException | ResultsReadError | UserNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	
}
