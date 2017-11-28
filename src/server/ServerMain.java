package server;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

import javax.swing.JFrame;

import clientObjects.LeaderBoard;
import clientObjects.Positions;
import clientObjects.User;
import exceptions.PlayerNotFound;
import exceptions.ResultsReadError;
import exceptions.UserNotFound;
import ui.MainFrame;

public abstract class ServerMain {

	public static void main(String[] args) throws ResultsReadError, UserNotFound, IOException {
		// this starts the server
		// run the interface for server side operations
		Lock csvLock = new Lock();
		ServerGUI gui = new ServerGUI(csvLock);
		gui.create();
		makeFrame(gui);
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
	
	public void addComponentToPane(Container pane) {
		pane.add(comp)
	}
	
	/**
	 * This method sets the frame parameters and arguments,
	 * and makes the actual frame of the entire application.
	 */
	  private static void makeFrame(ServerGUI gui) {
		//Create and set up window
		JFrame mainFrame = new JFrame("S League Fantasy Football");
		mainFrame.setMinimumSize(new Dimension(1050, 700));
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(new BorderLayout());
		mainFrame.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent windowEvent){
			System.exit(0);
		}        
		}); 
		
		//Create and set up content pane
		ServerMain frame = new ServerMain();
		frame.addComponentToPane(mainFrame.getContentPane());
		
		//Display the window
		mainFrame.pack(); 
	    mainFrame.setVisible(true); 
	    
	  }
	

}
