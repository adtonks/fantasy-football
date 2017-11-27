package ui;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import listeners.ButtonListener;
import listeners.JoinValidGameListener;

/**
 * This class represents the screen the user sees when he or she first signs up
 * and chooses to join a game that has already been created.
 * @author charisannelim
 *
 */
public class JoinGame extends JPanel {
	
	private BufferedImage bg = null;
	private JPanel screens;
	private Font headerFont;
	private Font textFont;
	
	/**
	 * This constructor points the parameters as the current instance.
	 * @param screens, main JPanel with cardlayout
	 * @param headerFont, custom font
	 * @param textFont, custom font
	 */
	public JoinGame(JPanel screens, Font headerFont, Font textFont) {
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
		this.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		Font sm0l = headerFont.deriveFont((float) 14); 
		
		JLabel title = new JLabel("JOIN GAME");
		title.setForeground(Color.WHITE);
		title.setFont(headerFont);
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel name = new JLabel("Enter the group code that was shared with you::  ");
		name.setForeground(Color.WHITE);
		name.setFont(textFont);
		name.setAlignmentX(CENTER_ALIGNMENT);
		
		JTextField group_name = new JTextField();
		group_name.setMaximumSize(new Dimension(300, 40));
		group_name.setFont(headerFont);
		group_name.setForeground(Color.RED);
		group_name.setBackground(new Color(0, 0, 0, 0));
		group_name.setOpaque(false);
		group_name.setCaretColor(Color.WHITE);
		group_name.setHorizontalAlignment(JTextField.CENTER);
		group_name.setAlignmentX(CENTER_ALIGNMENT);
		
		JButton button = new JButton("Submit");
		button.setFont(sm0l);
		button.setAlignmentX(CENTER_ALIGNMENT);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JoinValidGameListener jl = new JoinValidGameListener(screens, textFont, this, group_name);
		button.setActionCommand("joinvalid");
		button.addActionListener(jl);
		
		this.add(title);
		this.add(Box.createRigidArea(new Dimension(0, 70)));
		this.add(name);
		this.add(Box.createRigidArea(new Dimension(0, 10)));
		this.add(group_name);
		this.add(Box.createRigidArea(new Dimension(0, 20)));
		this.add(button);
		this.add(Box.createRigidArea(new Dimension(0, 30)));
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
