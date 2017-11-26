package clientObjects;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import exceptions.IndexDoesNotExist;
import exceptions.ResultsReadError;
import exceptions.UserNotFound;

// this object is read from and written to the user list CSV file
public class User {
	private final String username;
	private final String password;
	private final String email;
	private final int gameID;
	private final boolean isHost;
	private final int points;
	private final int week;
	private final List<Player> GKs; // 1 player
	private final List<Player> DFs; // 4 players
	private final List<Player> MFs; // 4 players
	private final List<Player> FWs; // 2 players
	private final List<Player> SUBs; // 6 players

	/* search through the CSV to find user info and initialize object */
	/* only called by server */
	public User(String _username) throws ResultsReadError,
	FileNotFoundException,UserNotFound {
		int i;
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
			this.isHost = csvReader.nextInt() == 1;
			this.points = csvReader.nextInt();
			this.week = csvReader.nextInt();
			for(i=0; i<1; i++)
				GKs.add(new Player(csvReader.nextInt()));
			for(i=0; i<4; i++)
				DFs.add(new Player(csvReader.nextInt()));
			for(i=0; i<4; i++)
				MFs.add(new Player(csvReader.nextInt()));
			for(i=0; i<2; i++)
				FWs.add(new Player(csvReader.nextInt()));
			for(i=0; i<6; i++)
				SUBs.add(new Player(csvReader.nextInt()));					
		} catch(NoSuchElementException e) {
			System.out.println("User information incomplete");
			csvReader.close();
			throw new ResultsReadError();
		}
		csvReader.close();
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
		this.GKs = new ArrayList<Player>();
		this.DFs = new ArrayList<Player>();
		this.MFs = new ArrayList<Player>();
		this.FWs = new ArrayList<Player>();
		this.SUBs = new ArrayList<Player>();
	}
	
	public int getPoints() {
		return points;
	}

	public int getWeek() {
		return week;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public int getGameID() {
		return gameID;
	}

	public boolean isHost() {
		return isHost;
	}
	
	public Player getGK(int index) throws IndexDoesNotExist {
		if((index<0) || (0<index))
			throw new IndexDoesNotExist();
		return(this.GKs.get(index));
	}
	
	public Player getDF(int index) throws IndexDoesNotExist {
		if((index<0) || (3<index))
			throw new IndexDoesNotExist();
		return(this.DFs.get(index));
	}
	
	public Player getMF(int index) throws IndexDoesNotExist {
		if((index<0) || (3<index))
			throw new IndexDoesNotExist();
		return(this.MFs.get(index));
	}
	
	public Player getFW(int index) throws IndexDoesNotExist {
		if((index<0) || (1<index))
			throw new IndexDoesNotExist();
		return(this.FWs.get(index));
	}
	
	public Player getSUB(int index) throws IndexDoesNotExist {
		if((index<0) || (5<index))
			throw new IndexDoesNotExist();
		return(this.SUBs.get(index));
	}
	
	public Player findPlayerByID(int playerID) {
		// returns the player if player ID exists, null otherwise
		int i;
		for(i=0; i<1; i++) {
			if(this.GKs.get(i).getPlayerID() == playerID)
				return(this.GKs.get(i));
		}	
		for(i=0; i<4; i++) {
			if(this.DFs.get(i).getPlayerID() == playerID)
				return(this.DFs.get(i));
		}		
		for(i=0; i<4; i++) {
			if(this.MFs.get(i).getPlayerID() == playerID)
				return(this.MFs.get(i));
		}
		for(i=0; i<2; i++) {
			if(this.FWs.get(i).getPlayerID() == playerID)
				return(this.FWs.get(i));
		}
		for(i=0; i<6; i++) {
			if(this.SUBs.get(i).getPlayerID() == playerID)
				return(this.SUBs.get(i));
		}
		return(null);
	}
	
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
				if(in_index == 0)
					this.GKs.set(i, newPlayer);
				else
					in_index = 0;
		}	
		for(i=0; i<4; i++) {
			if(this.DFs.get(i).getPlayerID() == playerID2)
				index = 0;
			if(this.DFs.get(i).getPlayerID() == playerID1)
				if(in_index == 0)
					this.DFs.set(i, newPlayer);
				else
					in_index = 0;
		}		
		for(i=0; i<4; i++) {
			if(this.MFs.get(i).getPlayerID() == playerID2)
				index = 0;
			if(this.MFs.get(i).getPlayerID() == playerID1)
				if(in_index == 0)
					this.MFs.set(i, newPlayer);
				else
					in_index = 0;
		}
		for(i=0; i<2; i++) {
			if(this.FWs.get(i).getPlayerID() == playerID2)
				index = 0;
			if(this.FWs.get(i).getPlayerID() == playerID1)
				if(in_index == 0)
					this.FWs.set(i, newPlayer);
				else
					in_index = 0;
		}
		for(i=0; i<6; i++) {
			if(this.SUBs.get(i).getPlayerID() == playerID2)
				index = 0;
			if(this.SUBs.get(i).getPlayerID() == playerID1)
				if(in_index == 0)
					this.SUBs.set(i, newPlayer);
				else
					in_index = 0;
		}
		return(index);
	}
	
	public void substitute(int playerID1, int playerID2) {
		Player player1 = findPlayerByID(playerID1);
		Player player2 = findPlayerByID(playerID2);
		System.out.println("substitute");
		System.out.println(player1.getName());
		System.out.println(player2.getName());
		insertPlayerIntoID(player2, playerID1, playerID2,
				insertPlayerIntoID(player1, playerID2, playerID1, 0));
		System.out.println(player1.getName());
		System.out.println("substitute");
		/* insertPlayerIntoID(player1, playerID2); */
	}
	
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
	
}
