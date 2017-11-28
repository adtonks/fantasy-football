package ui;

import java.awt.BorderLayout;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class is the main GUI Frame which implements a JPanel with a CardLayout.
 * @author charisannelim
 *
 */
public class MainFrame {
	private JPanel screens;
	private Font headerFont;
	private Font textFont;
	private int[] arr, anotherarr;

	/**
	 * This method creates the various screens, each with its own class, and
	 * adds all these screens as cards to the main pane.
	 * @param pane, the main content pane
	 */
	public void addComponentToPane(Container pane) {
		
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
		
		screens = new JPanel(new CardLayout());
		
		//Create the screens
		StartScreen startScreen = new StartScreen(screens, headerFont, textFont);
		startScreen.create();
		
		NewAccount newAccount = new NewAccount(screens, headerFont, textFont);
		newAccount.create();
		
		HomePage userHome = new HomePage(screens, headerFont, textFont);
//		userHome.create();
		
		CreateGame createGame = new CreateGame(screens, headerFont, textFont);
		createGame.create();
		
		JoinGame joinGame = new JoinGame(screens, headerFont, textFont);
		joinGame.create();
		

		FirstDraft firstDraft = new FirstDraft(arr, screens, headerFont, textFont);
//		firstDraft.create(arr);
		
		Help help = new Help(screens, headerFont, textFont);
		help.create();
		
		
		ChangeLineup lineup = new ChangeLineup(firstDraft, screens, headerFont, textFont);
//		lineup.create(anotherarr);
		
		
		
		
		//Add screens
		screens.add(startScreen, "HI");
		screens.add(newAccount, "NEW");
		screens.add(userHome, "HOME");
		screens.add(createGame, "CREATE");
		screens.add(joinGame, "JOIN");
		screens.add(firstDraft, "FIRST DRAFT");
		screens.add(help, "HELP");
		screens.add(lineup, "LINEUP");
		
		pane.add(screens);
	}

/**
 * This method sets the frame parameters and arguments,
 * and makes the actual frame of the entire application.
 */
  private static void makeFrame() {
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
	MainFrame frame = new MainFrame();
	frame.addComponentToPane(mainFrame.getContentPane());
	
	//Display the window
	mainFrame.pack(); 
    mainFrame.setVisible(true); 
    
  }

  public static void main(String[] args) {
	  makeFrame();
	  System.out.println("bla bla");
  }
  
  
}
