package listeners;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputHintListener implements FocusListener {
	
	private JTextField text;
	
	public InputHintListener(JTextField text) {
		this.text = text;
	}

	@Override
	public void focusGained(FocusEvent e) {
		text.setText("");
		text.setForeground(Color.black);
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		
		
	}

}
