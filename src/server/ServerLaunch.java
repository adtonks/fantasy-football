package server;

import java.awt.BorderLayout;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class is the main GUI Frame which implements a JPanel with a CardLayout.
 * @author charisannelim
 *
 */
public class ServerLaunch {
	private JPanel screens;
	private Font headerFont;
	private Font textFont;
	private int[] arr, anotherarr;

	/**
	 * This method creates the various screens, each with its own class, and
	 * adds all these screens as cards to the main pane.
	 * @param pane, the main content pane
	 */
	public void addComponentToPane(Container pane, Lock csvLock) {
		
		//Register Custom Fonts
		try {
			  headerFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("BIG JOHN.otf"));
			  headerFont = headerFont.deriveFont(0, 20);
			  
			  textFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("CircularStd-Bold.otf"));
			  textFont = textFont.deriveFont(0, 14);
			  
		      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("BIG JOHN.otf")));
		     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("CircularStd-Bold.otf")));
			} catch (IOException|FontFormatException e) {
		     e.printStackTrace();
			}
		
		
	
		
		
		
		//Add screens
		ServerGUI gui = new ServerGUI(csvLock);
		gui.create();
		
		pane.add(gui);
	}

/**
 * This method sets the frame parameters and arguments,
 * and makes the actual frame of the entire application.
 */
  private static void makeFrame(Lock csvLock) {
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
	ServerLaunch frame = new ServerLaunch();
	frame.addComponentToPane(mainFrame.getContentPane(), csvLock);
	
	//Display the window
	mainFrame.pack(); 
    mainFrame.setVisible(true); 
    
  }

  @SuppressWarnings("resource")
public static void main(String[] args) {
	  	Lock csvLock = new Lock();
	  	makeFrame(csvLock);
		String input, reply;
		Socket clientSocket;
		BufferedWriter socketWr = null;
		ServerSocket myServer = null;
		try {
			myServer = new ServerSocket(8888);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		while(true) {
			try {
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
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
  }
  
  
}
