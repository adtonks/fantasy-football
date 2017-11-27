package server;

import java.util.Scanner;

public class TextProcessor {

	public TextProcessor() {
		// TODO Auto-generated constructor stub
	}
	
	public static String (String request) {
		Scanner reader = new Scanner(request);
		reader.useDelimiter(",");
		String func = reader.next();
		reader.useDelimiter(";");
		String args = reader.next();
		Scanner argReader = new Scanner(argsReader);
		switch(func) {
		case "sUserPull":
			cUserPull(argReader.next(), argReader.next())
			
		}
	}

}
