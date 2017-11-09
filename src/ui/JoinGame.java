package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JoinGame extends JPanel {
	
	private BufferedImage bg = null;
	
	public void create() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JLabel title = new JLabel("JOIN GAME");
		title.setForeground(Color.WHITE);
		JLabel prompt = new JLabel("Key in your code:");
		prompt.setForeground(Color.WHITE);
		JTextField code = new JTextField(5);
		JButton button = new JButton("Submit");
		
		this.add(title);
		this.add(prompt);
		this.add(code);
		this.add(button);
	}
	
	@Override
	  protected void paintComponent(Graphics g) {
		
		try {
		    bg = ImageIO.read(getClass().getResource("bg.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	    super.paintComponent(g);
	    g.drawImage(bg, 0, 0, null);
	}
}
