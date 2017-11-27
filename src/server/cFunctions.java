package server;

import java.io.FileNotFoundException;

import clientObjects.Player;
import clientObjects.User;
import exceptions.ResultsReadError;
import exceptions.UserNotFound;

public abstract class cFunctions {

	public User cUserPull(String _username, String _password) throws FileNotFoundException, ResultsReadError, UserNotFound {
		// returns null if incorrect password
		User userRet = new User(_username);
		if(userRet.getPassword().equals(_password))
			return(userRet);
		else
			return(null);
	}
	
	public Player cGetPlayer(int _playerID) {
		// returns null if player cannot be found
		try {
			return(new Player(_playerID));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return(null);
		} catch (ResultsReadError e) {
			e.printStackTrace();
			return(null);
		} catch (UserNotFound e) {
			return(null);
		}
	}
}
