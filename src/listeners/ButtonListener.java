package listeners;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class ButtonListener implements ActionListener {
	
	private JPanel screens;
	
	public ButtonListener(JPanel screens) {
		this.screens = screens;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		CardLayout cl = (CardLayout) screens.getLayout();
		
		if (e.getActionCommand() == "logout") {
			cl.show(screens, "HI");
		}
		
		if (e.getActionCommand() == "join") {
			cl.show(screens, "JOIN");
		}
		
		if (e.getActionCommand() == "create") {
			cl.show(screens, "CREATE");
		}
		
		if (e.getActionCommand() == "home") {
			cl.show(screens, "HOME");
		}
		
		if (e.getActionCommand() == "draft") {
			cl.show(screens, "FIRST DRAFT");
		}
		
		if (e.getActionCommand() == "save") {
			cl.show(screens, "HOME");
		}
		
		if (e.getActionCommand() == "changeplayers") {
			cl.show(screens, "LINEUP");
		}
		
		if (e.getActionCommand() == "help") {
			cl.show(screens, "HELP");
		}
		
		
	}
	
}
