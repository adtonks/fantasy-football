package server;

import java.io.FileNotFoundException;

import clientObjects.Player;
import clientObjects.Positions;
import clientObjects.User;
import exceptions.PlayerNotFound;
import exceptions.ResultsReadError;
import exceptions.UserNotFound;

public abstract class ServerMain {

	public static void main(String[] args) throws FileNotFoundException, ResultsReadError, UserNotFound {
		// this starts the server
		// initialize the dictionary to store player points
		PlayerScoreDict ScoresDict = new PlayerScoreDict();
		User test_user;
		Player test_player;
		try {
			ScoresDict.populateScores();
		} catch (FileNotFoundException | ResultsReadError e) {
			e.printStackTrace();
		}
		try {
			System.out.println("Score of Menda as DF is " +
					ScoresDict.findScore(2, Positions.DF));
			System.out.println("Score of Tanaka as MF is " +
					ScoresDict.findScore(5, Positions.MF));
		} catch (PlayerNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test_user = new User("adtonks");
		System.out.println(test_user.getEmail());
		test_player = new Player(63);
		System.out.println(test_player.getTeam());
		System.out.println(test_user.getPlayer(Positions.MF, 2).getName());
		
	}

}
