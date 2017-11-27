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
import ui.ChangeLineup;
import ui.FirstDraft;
import ui.HomePage;
import ui.JoinGame;

/**
 * This Listener ensures that the game a new user would like to join
 * is one that is valid and existing.
 * @author charisannelim
 *
 */
public class JoinValidGameListener implements ActionListener {

	private JTextField code;
	private JoinGame panel;
	private JPanel screens;
	private Font textFont;
	private int[] arr, anotherarr;
	
	/**
	 * This constructor points the parameters as the current instance.
	 * @param screens, the main JPanel
	 * @param textFont, the custom font
	 * @param panel, the current panel
	 * @param code, the join game code
	 */
	public JoinValidGameListener(JPanel screens, Font textFont, JoinGame panel, JTextField code) {
		this.panel = panel;
		this.code = code;
		this.screens = screens;
		this.textFont = textFont;
	}
	
	/**
	 * Depending on whether the code is a valid GAME ID or not, 
	 * pressing the button will either pop up a an error dialog box
	 * or lead the user to the homepage.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		CardLayout cl = (CardLayout) screens.getLayout();
		if (e.getActionCommand() == "joinvalid") {
			
			String code_ = code.getText();
			
			//check game IDs
			if (Sfunctions.sGameIDExist(Integer.parseInt(code_))) {
//			 if (code_.equals("1234")) {
				 
				//Create new User object
				User test = new User(SignUpListener.getUsername(),
										 SignUpListener.getPW(),
										 SignUpListener.getEmail(),
										 Integer.parseInt(code_),
										 true);
				Sfunctions.sNewUser(test);
				
				HomePage homepage = (HomePage) screens.getComponent(2);
				homepage.create(test);
				
				arr = new int[203];
				for (int i = 0; i < arr.length; i++) {
					arr[i] = i+1;
				}
				
				FirstDraft firstdraft = (FirstDraft) screens.getComponent(5);
				firstdraft.create(test, arr);
				
				anotherarr = new int[17];
				for (int i = 0; i < anotherarr.length; i++) {
					anotherarr[i] = i+1;
				}
				
				ChangeLineup lineup = (ChangeLineup) screens.getComponent(7);
				lineup.create(test, anotherarr);
				
				cl.show(screens, "HOME");
				 
			 }
			 else {
				 JLabel label = new JLabel("Invalid code. Please try again!!!");
				 label.setFont(textFont);
				 JOptionPane.showMessageDialog(panel, label,
						  "Error",JOptionPane.ERROR_MESSAGE);
				}
			
			
		}


	}

}
