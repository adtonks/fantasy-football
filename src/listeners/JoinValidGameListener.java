package listeners;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ui.JoinGame;

public class JoinValidGameListener implements ActionListener {

	private JTextField code;
	private JoinGame panel;
	private JPanel screens;
	private Font textFont;

	public JoinValidGameListener(JPanel screens, Font textFont, JoinGame panel, JTextField code) {
		this.panel = panel;
		this.code = code;
		this.screens = screens;
		this.textFont = textFont;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		CardLayout cl = (CardLayout) screens.getLayout();
		if (e.getActionCommand() == "joinvalid") {
			
			String code_ = code.getText();
			
			//check game IDs
			 if (code_.equals("4zj2qd")) {
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
