package clientFunctions;

import java.io.FileNotFoundException;

import clientObjects.LeaderBoard;
import clientObjects.Player;
import clientObjects.User;
import exceptions.ResultsReadError;
import exceptions.UserNotFound;

public class clientTest_Adam {

	public static void main(String[] args) throws FileNotFoundException, ResultsReadError, UserNotFound {
		int i;
		Player testP = new Player(23);
		User testU1 = new User("adtonks");
		User testU2 = new User("adtonks", "hello", "dog@chat.com", 1002, false);
		System.out.println(Sfunctions.sUserPull("bill", "qwerty").getEmail());
		System.out.println(Sfunctions.sGetPlayer(30).getNationality());
		Sfunctions.sNewUser(testU1);
		testU1.substitute(2, 12);
		System.out.println(Sfunctions.sUserPush(testU1));
		System.out.println(Sfunctions.sUsernameExist("john"));
		System.out.println(Sfunctions.sGameIDExist(1002));
		LeaderBoard testLB = Sfunctions.sGetBoard(1002);
		for(i=0; i<testLB.getBoardLen(); i++) {
			System.out.println(testLB.getUserPointsList().get(i).username);
			System.out.println(testLB.getUserPointsList().get(i).points);
		}
		System.out.println(Sfunctions.sGenGameID());
	}

}
