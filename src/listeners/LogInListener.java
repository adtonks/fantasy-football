package listeners;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clientFunctions.Sfunctions;
import clientObjects.User;
import exceptions.IndexDoesNotExist;
import ui.ChangeLineup;
import ui.FirstDraft;
import ui.HomePage;
import ui.StartScreen;

/**
 * This class is a Listener class that listens and gets the user input
 * from the log-in fields.
 * @author charisannelim
 *
 */
public class LogInListener implements ActionListener {
	
	private JTextField user;
	private JTextField pw;
	private StartScreen panel;
	private JPanel screens;
	private Font textFont;
	private User user_obj;
	private int[] arr, anotherarr;

	/**
	 * This constructor points the parameters as the current instance.
	 * @param screens, the main JPanel
	 * @param textFont, the custom Font
	 * @param panel, the start screen itself
	 * @param user, the JTextField for username
	 * @param pw, the JTextField for password
	 */
	public LogInListener(JPanel screens, Font textFont, StartScreen panel, JTextField user, JTextField pw) {
		this.panel = panel;
		this.user = user;
		this.pw = pw;
		this.screens = screens;
		this.textFont = textFont;
	}
	
	/**
	 * Text in JTextFields are parsed and compared to
	 * that of those in the database. If both the username
	 * and the password matches, the user will be led to the following page.
	 * If not, an error message will pop up.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		CardLayout cl = (CardLayout) screens.getLayout();
		if (e.getActionCommand() == "login") {
			
			String user_ = user.getText();
			String pw_ = pw.getText();
			
			//check database if user name exists first, if yes then pull.
			if (Sfunctions.sUsernameExist(user_)) {
				 user_obj = Sfunctions.sUserPull(user_, pw_);
				 if (user_obj != null) {
					 
					 HomePage homepage = (HomePage) screens.getComponent(2);
					 homepage.removeAll();
					 homepage.create(user_obj);
					 homepage.revalidate();
					 homepage.repaint();
					 
					arr = new int[203];
					for (int i = 0; i < arr.length; i++) {
						arr[i] = i+1;
					}
						
					FirstDraft firstdraft = (FirstDraft) screens.getComponent(5);
					firstdraft.removeAll();
					firstdraft.create(user_obj, arr);
					firstdraft.revalidate();
					firstdraft.repaint();
					
					anotherarr = new int[17];
					
					//REMOVE THIS CHANGE IT TO GET THE ARR FROM USER
					for (int i = 0; i < 17; i ++) {
						anotherarr[i] = -1;
					}
					
					 //change lineup immediately
					ChangeLineup lineup = (ChangeLineup) screens.getComponent(7);
					lineup.removeAll();
					lineup.create(user_obj, anotherarr);
					lineup.revalidate();
					lineup.repaint();
					
					cl.show(screens, "HOME");
				 }
				 else {
					 JLabel label = new JLabel("Incorrect username or password. Please try again!!!");
					 label.setFont(textFont);
					 JOptionPane.showMessageDialog(panel, label,
							  "Error",JOptionPane.ERROR_MESSAGE);
				 }
				
			}
			else {
				JLabel label = new JLabel("This user does not exist. Please try again!!!!!");
				 label.setFont(textFont);
				 JOptionPane.showMessageDialog(panel, label,
						  "Error",JOptionPane.ERROR_MESSAGE);
			}
			
		}


	}

}
