package server;

import java.io.IOException;

import exceptions.PlayerNotFound;
import exceptions.ResultsReadError;

public class GameIDgenerator {
	// highest gameID assigned
	private int currGameID;
	
	GameIDgenerator(int _currGameID) {
		this.currGameID = _currGameID;
	}
	
	public int getGameID() throws ResultsReadError, PlayerNotFound, IOException {
		while(ServerMain.cGameIDExist(currGameID))
			currGameID++;
		return(currGameID++);
	}
}
