package listeners;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ui.StartScreen;

public class LogInListener implements ActionListener {
	
	private JTextField user;
	private JTextField pw;
	private StartScreen panel;
	private JPanel screens;
	private Font textFont;

	public LogInListener(JPanel screens, Font textFont, StartScreen panel, JTextField user, JTextField pw) {
		this.panel = panel;
		this.user = user;
		this.pw = pw;
		this.screens = screens;
		this.textFont = textFont;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		CardLayout cl = (CardLayout) screens.getLayout();
		if (e.getActionCommand() == "login") {
			
			String user_ = user.getText();
			String pw_ = pw.getText();
			
			//check database
			 if (user_.equals("charis") && pw_.equals("password")) {
				 cl.show(screens, "HOME");
			 }
			 else {
				 JLabel label = new JLabel("Incorrect username or password. Please try again!!!");
				 label.setFont(textFont);
				 JOptionPane.showMessageDialog(panel, label,
						  "Error",JOptionPane.ERROR_MESSAGE);
				}
			
			
		}


	}

}
