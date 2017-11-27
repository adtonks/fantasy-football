package listeners;

import clientFunctions.Sfunctions;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ui.StartScreen;

/**
 * This class is a Listener class that listens and gets the user input
 * from the sign-up fields.
 * @author charisannelim
 *
 */
public class SignUpListener implements ActionListener {

	private JTextField user;
	private JTextField pw;
	private JTextField email;
	private JPanel screens;
	private StartScreen panel;
	private Font textFont;
	
	private static String password;
	private static String emailadd;
	private static String username;

	/**
	 * This constructor points the parameters as the current instance.
	 * @param screens, the main JPanel
	 * @param textFont, the custom Font
	 * @param panel, the start screen itself
	 * @param user, the JTextField for username
	 * @param pw, the JTextField for password
	 * @param email, the JTextField for email address
	 */
	public SignUpListener(JPanel screens, Font textFont, StartScreen panel, JTextField user, JTextField pw, JTextField email) {
		this.user = user;
		this.pw = pw;
		this.email = email;
		this.screens = screens;
		this.textFont = textFont;
		this.panel = panel;
	}
	
	/**
	 * Text in JTextFields are parsed and compared to
	 * that of those in the database. If it matches, the user
	 * cannot sign up for a new account. If it doesn't, it will be added successfully
	 * and the user will be led to the following page.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		CardLayout cl = (CardLayout) screens.getLayout();
		if (e.getActionCommand() == "signup") {
			
			String user_ = user.getText();
			String pw_ = pw.getText();
			String email_ = email.getText();
			
			//check database for existing usernames
			//if (Sfunctions.sUsernameExist(user_) == false) {
			 if (user_.equals("adam")) {
				 cl.show(screens, "NEW");
				 //store password
				 setPW(pw_);
				 //store email
				 setEmail(email_);
			 }
			 else {
				 JLabel label = new JLabel("This username has already been taken...");
				 label.setFont(textFont);
				 JOptionPane.showMessageDialog(panel, label,
						  "Error",JOptionPane.ERROR_MESSAGE);
				}
			
		}
		
	}
	
	public static void setUsername(String user) {
		username = user;
	}
	
	public static String getUsername() {
		return username;
	}
	
	public static void setPW(String pw) {
		password = pw;
	}
	
	public static String getPW() {
		return password;
	}
	
	public static void setEmail(String email) {
		emailadd = email;
	}
	
	public static String getEmail() {
		return emailadd;
	}

}
