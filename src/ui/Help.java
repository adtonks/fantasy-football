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

/**
 * This class represents the Help screen that offers instructions and talks
 * about what this game is about.
 * @author charisannelim
 *
 */
public class Help extends JPanel {
	private JPanel screens;
	private Font headerFont;
	private Font textFont;
	private BufferedImage bg = null;
	
	/**
	  * This constructor points the parameters as the current instance.
	 * @param screens, main JPanel with cardlayout
	 * @param headerFont, custom font
	 * @param textFont, custom font
	 */
	
	public Help(JPanel screens, Font headerFont, Font textFont) {
		this.screens = screens;
		this.headerFont = headerFont;
		this.textFont = textFont;
	}
	
	/**
	 * This method adds all the necessary components to the JPanel.
	 */
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
		
		
		String t = "WHAT IS THIS ABOUT?\n\n"
				+ "Welcome to S-League's Fantasy Football! This is the first of its kind in Singapore.\n\n"
				+ "HOW DO I PLAY THIS GAME?\n\n"
				+ "STEP 1:\n"
				+ "To be reading this, you either created a new game or joined an existing game. If you did the former, you are the host of that game.\n\n"
				+ "STEP 2:\n"
				+ "Wait for your friends to join! When a maximum of 10 people have joined your game, set a time and place for you guys to come together to start drafting!\n"
				+ "Only the host of the game can start the draft.\n\n"
				+ "STEP 3:\n"
				+ "During the draft, choose 17 players of any position and football club. The order of choosing is randomized.\n\n"
				+ "STEP 4:\n"
				+ "Earn points when your players make good moves such as tackles, assists and saves during the actual season.\n\n"
				+ "STEP 5:\n"
				+ "Strategise before every match and change your starting lineup to maximise your points!\n\n"
				+ "Step 6\n"
				+ "Win the game.\n\n"
				+ "WOW I LUV DIS SO MUCH WHO DEVELOPED IT?\n\n"
				+ "Logan Ye, Adam Tonks and Charis Anne Lim. :)\n";
		
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
	
	/** 
	 * This method paints the background of the panel with a resource image.
	 */
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
