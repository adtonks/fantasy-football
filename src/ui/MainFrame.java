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
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class MainFrame {
	private JPanel screens;
	private Font headerFont;
	private Font textFont;

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
		userHome.create();
		
		CreateGame createGame = new CreateGame(screens, headerFont, textFont);
		createGame.create();
		
		JoinGame joinGame = new JoinGame();
		joinGame.create();
		
		FirstDraft firstDraft = new FirstDraft(screens, headerFont, textFont);
		firstDraft.create();
		
		
		//Add screens
		screens.add(startScreen, "HI");
		screens.add(newAccount, "NEW");
		screens.add(userHome, "HOME");
		screens.add(createGame, "CREATE");
		screens.add(joinGame, "JOIN");
		screens.add(firstDraft, "FIRST DRAFT");
		
		pane.add(screens);
	}

  private static void makeFrame() {
	//Create and set up window
	JFrame mainFrame = new JFrame("S League Fantasy Football");
	mainFrame.setMinimumSize(new Dimension(800, 600));
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
  
  //Change default font
//  public static void setUIFont(final Font font){
//	  FontUIResource uiFont  =new FontUIResource (font);
//	  UIManager.put("Label.font", uiFont);
//	  UIManager.put("TabbedPane.font", uiFont);
//	  UIManager.put("TextField.font", uiFont);
//	  UIManager.put("PasswordField.font",uiFont);
//	  UIManager.put("Button.font",uiFont);
//	  UIManager.put("RadioButton.font",uiFont);
//	  UIManager.put("CheckBox.font",uiFont);
//	  UIManager.put("ComboBox.font",uiFont);
//	  UIManager.put("Menu.font",uiFont);
//	  UIManager.put("List.font",uiFont);
//	  UIManager.put("ListBox.font",uiFont);
//	  UIManager.put("MenuItem.font",uiFont);
//	  UIManager.put("Panel.font",uiFont);
//	  UIManager.put("TitledBorder.font",uiFont);
//	}
	 
  
  public static void main(String[] args) {
	  makeFrame();
  }
  
  
}
