package listeners;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This Listener is a small one that makes set Text disappear on click
 * for the email user field.
 * @author charisannelim
 *
 */
public class InputHintListener implements FocusListener {
	
	private JTextField text;
	
	/**
	 * This constructor points the parameters as the current instance.
	 * @param text, the email predefined text
	 */
	public InputHintListener(JTextField text) {
		this.text = text;
	}
	/**
	 * Removes the text and sets the user typing text to black colour.
	 */
	@Override
	public void focusGained(FocusEvent e) {
		text.setText("");
		text.setForeground(Color.black);
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		
		
	}

}
