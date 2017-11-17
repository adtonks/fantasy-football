package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import listeners.ButtonListener;

public class Help extends JPanel {
	private JPanel screens;
	private Font headerFont;
	private Font textFont;
	private BufferedImage bg = null;
	
	public Help(JPanel screens, Font headerFont, Font textFont) {
		this.screens = screens;
		this.headerFont = headerFont;
		this.textFont = textFont;
	}
	
	public void create() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(new EmptyBorder(60, 30, 30, 30));
		this.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		ButtonListener bl = new ButtonListener(screens);
		Font sm0l = headerFont.deriveFont((float) 14); 
		
		JLabel title = new JLabel("HELP");
		title.setForeground(Color.WHITE);
		title.setFont(headerFont);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		String t = "HOW TO PLAY:\n"
				+ "Step 1\n"
				+ "Create a new game, or join a game with your friends. You probably would have done this step already.\n\n"
				+ "Step 2\n"
				+ "Build your fantasy football team of 17 players during the draft.\n\n"
				+ "Step 3\n"
				+ "Earn points when your players make moves during the actual season.\n\n"
				+ "Step 4\n"
				+ "Strategise before every match and change your starting lineup to maximise your points!\n\n"
				+ "Step 5\n"
				+ "Win the game.";
		
		JTextPane instructions = new JTextPane();
		instructions.setMaximumSize(new Dimension(900, 550));
		instructions.setFont(textFont);
		instructions.setText(t);
		instructions.setForeground(Color.WHITE);
		instructions.setOpaque(false);
		instructions.setBackground(new Color(0, 0, 0, 0));
		
		JButton back = new JButton("Back");
		back.setFont(sm0l);
		back.setActionCommand("home");
		back.addActionListener(bl);
		back.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//Insert FAQs and instructions
		
		this.add(title);
		this.add(Box.createRigidArea(new Dimension(0, 50)));
		this.add(instructions);
		this.add(Box.createRigidArea(new Dimension(0, 50)));
		this.add(back);
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
