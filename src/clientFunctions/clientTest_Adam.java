package clientFunctions;

import java.io.FileNotFoundException;

import clientObjects.Player;
import clientObjects.User;
import exceptions.ResultsReadError;
import exceptions.UserNotFound;

public class clientTest_Adam {

	public static void main(String[] args) throws FileNotFoundException, ResultsReadError, UserNotFound {
		Player testP = new Player(23);
		User testU1 = new User("adtonks");
		User testU2 = new User("adtonks", "hello", "dog@chat.com", 1002, false);
		Sfunctions.sUserPull("adtonks", "pinkdog");
		Sfunctions.sGetPlayer(34);
		Sfunctions.sNewUser(testU2);
		Sfunctions.sUserPush(testU1);
		Sfunctions.sUsernameExist("billy");
		Sfunctions.sGameIDExist(1005);
	}

}
