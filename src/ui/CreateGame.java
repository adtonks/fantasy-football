package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import clientFunctions.Sfunctions;
import clientObjects.User;
import listeners.ButtonListener;
import listeners.SignUpListener;

/**
 * This class represents the screen where users just signed up and are choosing
 * to create a new game with a unique game ID they can share
 * with their friends.
 * @author charisannelim
 *
 */
public class CreateGame extends JPanel {
	
	private BufferedImage bg = null;
	private JPanel screens;
	private Font headerFont;
	private Font textFont;
	private int[] arr, anotherarr;
	
/**
 * This constructor points the parameters as the current instance.
	 * @param screens, main JPanel with cardlayout
	 * @param headerFont, custom font
	 * @param textFont, custom font

 */
	public CreateGame(JPanel screens, Font headerFont, Font textFont) {
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
		
		ButtonListener bl = new ButtonListener(screens);
		Font sm0l = headerFont.deriveFont((float) 14); 
		
		JLabel title = new JLabel("CREATE NEW GAME");
		title.setForeground(Color.WHITE);
		title.setFont(headerFont);
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel name = new JLabel("Group Name  ");
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
		
		//Generated Code for New Game
		JLabel share = new JLabel("Your group has been successfully created.  ");
		share.setForeground(Color.WHITE);
		share.setFont(textFont);
		share.setAlignmentX(CENTER_ALIGNMENT);
		share.setVisible(false);
		
		JLabel share2 = new JLabel("Share this code with your friends so they can join your game!!!!!");
		share2.setForeground(Color.WHITE);
		share2.setFont(textFont);
		share2.setAlignmentX(CENTER_ALIGNMENT);
		share2.setVisible(false);
		
		JTextField code = new JTextField(5);
		code.setMaximumSize(new Dimension(70, 40));
		code.setFont(headerFont);
		code.setForeground(Color.RED);
		code.setBackground(new Color(0, 0, 0, 0));
		code.setOpaque(false);
		code.setAlignmentX(CENTER_ALIGNMENT);
		code.setVisible(false);
		
		JButton home = new JButton("Go to Home Page");
		home.setFont(sm0l);
		home.setAlignmentX(CENTER_ALIGNMENT);
		home.setVisible(false);
		home.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		home.setActionCommand("home");
		home.addActionListener(bl);
		
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int gameID = Sfunctions.sGenGameID();
				share.setVisible(true);
				share2.setVisible(true);
				code.setText(Integer.toString(gameID));
				code.setVisible(true);
				button.setVisible(false);
				home.setVisible(true);
				
				//Create new User object
				User test = new User(SignUpListener.getUsername(),
									 SignUpListener.getPW(),
									 SignUpListener.getEmail(),
									 gameID);
				Sfunctions.sNewUser(test);
				
				HomePage homepage = (HomePage) screens.getComponent(2);
				 homepage.removeAll();
				 homepage.create(test);
				 homepage.revalidate();
				 homepage.repaint();
				
				arr = new int[203];
				for (int i = 0; i < arr.length; i++) {
					arr[i] = i+1;
				}
				
				FirstDraft firstdraft = (FirstDraft) screens.getComponent(5);
				firstdraft.removeAll();
				firstdraft.create(test, arr);
				firstdraft.revalidate();
				firstdraft.repaint();
				
				//When it is here, user is new so no players selected
				anotherarr = new int[17];
				for (int i = 0; i < anotherarr.length; i++) {
					anotherarr[i] = -1;
				}
				
				ChangeLineup lineup = (ChangeLineup) screens.getComponent(7);
				lineup.removeAll();
				lineup.create(test, anotherarr);
				lineup.revalidate();
				lineup.repaint();
				
			}
	    });
		
		this.add(title);
		this.add(Box.createRigidArea(new Dimension(0, 70)));
		this.add(name);
		this.add(Box.createRigidArea(new Dimension(0, 10)));
		this.add(group_name);
		this.add(Box.createRigidArea(new Dimension(0, 20)));
		this.add(button);
		this.add(Box.createRigidArea(new Dimension(0, 30)));
		this.add(share);
		this.add(share2);
		this.add(Box.createRigidArea(new Dimension(0, 20)));
		this.add(code);
		this.add(Box.createRigidArea(new Dimension(0, 20)));
		this.add(home);
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
