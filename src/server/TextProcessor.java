package server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import clientObjects.CSVline;
import clientObjects.User;
import exceptions.PlayerNotFound;
import exceptions.ResultsReadError;
import exceptions.UserNotFound;

/**
 * This class processes server and client String requests and makes them do the necessary actions
 * @author charisannelim
 *
 */
public class TextProcessor {

	public TextProcessor() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * A parser for the requests
	 * @param request, a String
	 * @return instruction, a String
	 */
	public static String parseReq(String request) {
		Scanner reader = new Scanner(request);
		reader.useDelimiter(":");
		String func = reader.next();
		reader.useDelimiter(":|;");
		String args = reader.next();
		Scanner argReader = new Scanner(args);
		User userArg;

		switch(func) {
		case "sUserPull":
			try {
				argReader.useDelimiter(",");
				User sendUser = Cfunctions.cUserPull(
						argReader.next(), argReader.next());
				if(sendUser != null)
					return(sendUser.toCSVrow());
				else
					return("SERVER_ERR");
			} catch (FileNotFoundException | ResultsReadError | UserNotFound e) {
				e.printStackTrace();
				return("SERVER_ERR");
			}
		case "sGetPlayer":
			return(Cfunctions.cGetPlayer(
					Integer.valueOf(argReader.next())).toCSVrow());
		case "sNewUser":
			try {
				// convert User text into User object
				userArg = new User(new CSVline(argReader.next()));
				Cfunctions.cNewUser(userArg);
				return("SERVER_SUC");
			} catch (ResultsReadError | IOException e) {
				e.printStackTrace();
				return("SERVER_ERR");
			}
		case "sUserPush":
			try {
				// convert User text into User object
				userArg = new User(new CSVline(argReader.next()));
				// return true only if insertion was made (weeks are equal)
				if(Cfunctions.cUserPush(userArg))
					return("SERVER_TRU");
				else
					return("SERVER_FAL");
			} catch (ResultsReadError | IOException e) {
				e.printStackTrace();
				return("SERVER_ERR");
			}
		case "sUsernameExist":
			try {
				if(Cfunctions.cUsernameExist(argReader.next()))
					return("SERVER_TRU");
				else
					return("SERVER_FAL");
			} catch (ResultsReadError | IOException | PlayerNotFound e) {
				e.printStackTrace();
				return("SERVER_ERR");
			}
		case "sGameIDExist":
			try {
				if(Cfunctions.cGameIDExist(argReader.nextInt()))
					return("SERVER_TRU");
				else
					return("SERVER_FAL");
			} catch (ResultsReadError | PlayerNotFound | IOException e) {
				e.printStackTrace();
				return("SERVER_ERR");
			}
		case "sGetBoard":
			try {
				return(Cfunctions.cGetBoard(argReader.nextInt()).toCSVrow());
			} catch (FileNotFoundException | ResultsReadError | UserNotFound e) {
				e.printStackTrace();
				return("SERVER_ERR");
			}
		case "sGenGameID":
			try {
				return(String.valueOf(Cfunctions.cGenGameID()));
			} catch (ResultsReadError | PlayerNotFound | IOException e) {
				e.printStackTrace();
				return("SERVER_ERR");
			}
		default:
			
		}
		return(args);
		
		
	}

}
