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
		// run the interface for server side operations
		Lock csvLock = new Lock();
		ServerGUI gui = new ServerGUI(csvLock);
		gui.create();
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
			// don't allow CSV to be modified by both client and server
			synchronized(csvLock) {
				reply = TextProcessor.parseReq(input);
			}
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
	

}
