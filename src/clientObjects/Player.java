package clientObjects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import exceptions.ResultsReadError;
import exceptions.UserNotFound;

public class Player implements Serializable {
	// this is a read-only object
	private final int playerID;
	private final String name;
	private final String nationality;
	private final String team;
	// note that prefPosition is an enum
	private final Positions prefPosition;
	private final String imgPath;
	
	public Player(int _playerID) throws ResultsReadError,
	FileNotFoundException,	UserNotFound {
		// search through the CSV to find player info
		// initialize object according to info in CSV
		int i, nextInt;
		String[] csvHeader = new String[]
				{"playerID", "name", "nationality", "team",
						"prefPosition", "img_path"};
		
		Scanner csvReader = new Scanner(new File("csv_tables/player_array.csv"));
		csvReader.useDelimiter(",|\\n");
		// check that table headers are correct
		for(i=0; i<6; i++) {
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
		// find the player
		try {
			nextInt = csvReader.nextInt();
			while(_playerID != nextInt) {
				for(i=0; i<5; i++)
					csvReader.next();
				nextInt = csvReader.nextInt();
			}
		} catch(NoSuchElementException e) {
			System.out.println("Player not found");
			csvReader.close();
			throw new UserNotFound();
		}
		// throw exception if complete player info is not present
		try {
			this.playerID = _playerID;
			this.name = csvReader.next();
			this.nationality = csvReader.next();
			this.team = csvReader.next();
			this.prefPosition = Positions.valueOf(csvReader.next());
			this.imgPath = csvReader.next();				
		} catch(NoSuchElementException e) {
			System.out.println("Player information incomplete");
			csvReader.close();
			throw new ResultsReadError();
		}
		csvReader.close();
	}
	
	// construct User object from csv line
	public Player(CSVline input) throws ResultsReadError {
		Scanner csvReader = new Scanner(input.string);
		int i;
		csvReader.useDelimiter(",");				
		// throw exception if complete player info is not present
			try {
				this.playerID = csvReader.nextInt();
				this.name = csvReader.next();
				this.nationality = csvReader.next();
				this.team = csvReader.next();
				this.prefPosition = Positions.valueOf(csvReader.next());
				this.imgPath = csvReader.next();				
			} catch(NoSuchElementException e) {
				System.out.println("Player information incomplete");
				csvReader.close();
				throw new ResultsReadError();
			}
		csvReader.close();
	}
	
	public boolean compareTo(Player otherPlayer){
		// compare to another football player
		if (otherPlayer.getName() == this.getName()){
			return true;
		}
		return false;
	}

	public int getPlayerID() {
		return playerID;
	}

	public String getName() {
		return name;
	}

	public String getNationality() {
		return nationality;
	}

	public String getTeam() {
		return team;
	}

	public Positions getPrefPosition() {
		return prefPosition;
	}

	public String getImgPath() {
		return imgPath;
	}
	
	public String toCSVrow() {
		return(this.playerID + "," + this.name + "," + this.nationality + "," +
				this.team + "," + this.prefPosition + "," + this.imgPath);
	}
		
}
