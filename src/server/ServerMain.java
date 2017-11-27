package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import clientObjects.LeaderBoard;
import clientObjects.Positions;
import clientObjects.User;
import exceptions.PlayerNotFound;
import exceptions.ResultsReadError;
import exceptions.UserNotFound;

public abstract class ServerMain {

	public static void main(String[] args) throws ResultsReadError, UserNotFound, IOException {
		// this starts the server
		String input, reply;
		Socket clientSocket;
		BufferedWriter socketWr;
		ServerSocket myServer = new ServerSocket(8888);
		while(true) {
			System.out.println("Waiting for client");
			clientSocket = myServer.accept();
			System.out.println("Client received");
			BufferedReader socketRd = new BufferedReader(
					new InputStreamReader(
							clientSocket.getInputStream()));
			input = socketRd.readLine();
			System.out.println("Received: " + input);
			reply = TextProcessor.parseReq(input);
			System.out.println("Sending: " + reply);
			
			System.out.println("Writing to socket");
			socketWr = new BufferedWriter(
					new OutputStreamWriter(
							clientSocket.getOutputStream()));
			socketWr.write(reply);
			socketWr.write("\r\n");
			System.out.println("Written: " + reply);
			socketWr.flush();
		}
		
	}
	
	private static void update() throws ResultsReadError, PlayerNotFound, IOException {
		PlayerScoreDict scoreDict = new PlayerScoreDict();
		Writer csvWriter;
		int i, j, wkPoints, wkNew;
		List<Integer> userPointsList = new ArrayList<Integer>();
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
		scoreDict.populateScores();
		while(csvReader.hasNext()) {
			for(i=0; i<5; i++)
				csvReader.next();
			wkPoints = csvReader.nextInt();
			csvReader.next();
			for(i=0; i<1; i++)
				wkPoints += scoreDict.findScore(csvReader.nextInt(), Positions.GK);
			for(i=0; i<4; i++)
				wkPoints += scoreDict.findScore(csvReader.nextInt(), Positions.DF);
			for(i=0; i<4; i++)
				wkPoints += scoreDict.findScore(csvReader.nextInt(), Positions.MF);
			for(i=0; i<2; i++)
				wkPoints += scoreDict.findScore(csvReader.nextInt(), Positions.FW);
			for(i=0; i<6; i++)
				csvReader.next();
			userPointsList.add(wkPoints);
		}
		csvReader.close();
		// now we need to print the new scores
		csvReader = new Scanner(new File("csv_tables/user_list.csv"));
		csvReader.useDelimiter(",|\\n");
		csvWriter = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream("csv_tables/user_list.csv.tmp"), "utf-8"));
		
		for(i=0; i<24; i++) {
			csvWriter.write(csvReader.next());
			if(i != 23)
				csvWriter.write(",");
			else
				csvWriter.write("\n");
		}
		for(i=0; i<userPointsList.size(); i++) {
			for(j=0; j<5; j++)
				csvWriter.write(csvReader.next() + ",");
			// write the new points
			csvWriter.write(userPointsList.get(i) + ",");
			csvReader.next();
			// write the new week
			wkNew = csvReader.nextInt() + 1;
			csvWriter.write(wkNew + ",");
			for(j=0; j<17; j++) {
				csvWriter.write(csvReader.next());
				if(j != 16) {
					csvWriter.write(",");
				} else {
					if(csvReader.hasNext())
						csvWriter.write("\n");
				}
			}
		}

		csvWriter.close();
		csvReader.close();
		
		// now delete the old file and rename the temporary one
		File oldFile = new File("csv_tables/user_list.csv");
		oldFile.delete();
		File newFile = new File("csv_tables/user_list.csv.tmp");
		newFile.renameTo(oldFile);
		
	}
	
	// end the season by setting all weeks to -1
	private static void endSeason() throws ResultsReadError, PlayerNotFound, IOException {
		Scanner csvReader;
		Writer csvWriter;
		int i, j;
		
		// modify table by simultaneous reader and writer objects
		csvReader = new Scanner(new File("csv_tables/user_list.csv"));
		csvReader.useDelimiter(",|\\n");
		csvWriter = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream("csv_tables/user_list.csv.tmp"), "utf-8"));
		String heading;
		String[] csvHeader = new String[]
				{"username", "password", "email", "gameID", "isHost",
						"points", "week", "GK0", "DF0", "DF1", "DF2",
						"DF3", "MF0", "MF1", "MF2", "MF3", "FW0", "FW1",
						"SUB0", "SUB1", "SUB2", "SUB3", "SUB4", "SUB5"};
		
		// check that table headers are correct
		for(i=0; i<24; i++) {
			// read first line of headers
			heading = csvReader.next();
			if(!csvHeader[i].equals(heading)) {
				csvWriter.close();
				csvReader.close();
				System.out.println("CSV file formatted incorrectly");
				throw new ResultsReadError();
			}
			csvWriter.write(heading);
			if(i != 23)
				csvWriter.write(",");
		}
		csvWriter.write("\n");
		
		while(csvReader.hasNext()) {
			for(j=0; j<6; j++)
				csvWriter.write(csvReader.next() + ",");
			// write the new week
			csvReader.next();
			csvWriter.write("-1,");
			for(j=0; j<17; j++) {
				csvWriter.write(csvReader.next());
				if(j != 16) {
					csvWriter.write(",");
				} else {
					if(csvReader.hasNext())
						csvWriter.write("\n");
				}
			}
		}
			
		csvWriter.close();
		csvReader.close();
		
		// now delete the old file and rename the temporary one
		File oldFile = new File("csv_tables/user_list.csv");
		oldFile.delete();
		File newFile = new File("csv_tables/user_list.csv.tmp");
		newFile.renameTo(oldFile);
		
	}

}
