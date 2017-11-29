package ui;

import java.awt.Color;
import java.awt.Cursor;
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
import javax.swing.border.EmptyBorder;

import clientObjects.User;
import listeners.ButtonListener;
import listeners.SignUpListener;

/**
 * This class represents the screen where the user has just signed up
 * and can choose whether to create a new game or join one that
 * has already been created.
 * @author charisannelim
 *
 */
public class NewAccount extends JPanel {
	
	private JPanel screens;
	private Font headerFont;
	private Font textFont;
	private String username;
	private BufferedImage bg = null;
	
/**
 	 * This constructor points the parameters as the current instance.
	 * @param screens, main JPanel with cardlayout
	 * @param headerFont, custom font
	 * @param textFont, custom font

 */
	public NewAccount(JPanel screens, Font headerFont, Font textFont) {
		this.screens = screens;
		this.headerFont = headerFont;
		this.textFont = textFont;
	}
	
	/**
	 * This method adds all necessary components to the JPanel.
	 */
	public void create() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(new EmptyBorder(60, 30, 30, 30));
		this.setAlignmentX(CENTER_ALIGNMENT);
		
		ButtonListener bl = new ButtonListener(screens);
		
		
		username = SignUpListener.getUsername();
		JLabel user = new JLabel("Welcome, " + username + "  ");
		user.setForeground(Color.WHITE);
		user.setFont(headerFont);
		user.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel none = new JLabel("You have no existing games yet.  ");
		none.setForeground(Color.WHITE);
		none.setFont(textFont);
		none.setAlignmentX(CENTER_ALIGNMENT);
	    
	    
		JButton join = new JButton("Join Game");
		join.setActionCommand("join");
		join.addActionListener(bl);
		join.setMaximumSize(new Dimension(300, 50));
		join.setFont(headerFont);
		join.setAlignmentX(CENTER_ALIGNMENT);
		join.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		
		JButton create = new JButton("Create New Game");
		create.setActionCommand("create");
		create.addActionListener(bl);
		create.setMaximumSize(new Dimension(300, 50));
		create.setFont(headerFont);
		create.setAlignmentX(CENTER_ALIGNMENT);
		create.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		
		this.add(user);
		this.add(Box.createRigidArea(new Dimension(0, 100)));
		this.add(none);
		this.add(Box.createRigidArea(new Dimension(0, 30)));
		this.add(join);
		this.add(Box.createRigidArea(new Dimension(0, 15)));
		this.add(create);
	}
	
	
	/** 
	 * This method paints the background of the panel with a resource image.
	 */
	@Override
	  protected void paintComponent(Graphics g) {
		
		try {
		    bg = ImageIO.read(getClass().getResource("bga.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	    super.paintComponent(g);
	    g.drawImage(bg, 0, 0, null);
	}
}
