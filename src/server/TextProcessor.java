package server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import clientObjects.User;
import exceptions.ResultsReadError;
import exceptions.UserNotFound;

public class TextProcessor {

	public TextProcessor() {
		// TODO Auto-generated constructor stub
	}
	
	public static String parseReq(String request) {
		Scanner reader = new Scanner(request);
		reader.useDelimiter(":");
		String func = reader.next();
		reader.useDelimiter(":|;");
		String args = reader.next();
		Scanner argReader = new Scanner(args);
		argReader.useDelimiter(",");
		User userArg;

		switch(func) {
		case "sUserPull":
			try {
				return(Cfunctions.cUserPull(
						argReader.next(), argReader.next()).toCSVrow());
			} catch (FileNotFoundException | ResultsReadError | UserNotFound e) {
				e.printStackTrace();
				return("SERVER_ERR");
			}
		case "sGetPlayer":
			return(Cfunctions.cGetPlayer(
					Integer.valueOf(argReader.next())).toCSVrow());
		case "sNewUser":
			// convert User text into User object
			try {
				userArg = new User("adtonks");
				Cfunctions.cNewUser(userArg);
				return("SERVER_SUC");
			} catch (ResultsReadError | IOException | UserNotFound e) {
				e.printStackTrace();
				return("SERVER_ERR");
			}
		default:
			
		}
		return(args);
		
		
	}

}
