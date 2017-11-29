package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import clientObjects.Player;
import clientObjects.User;
import exceptions.ResultsReadError;
import exceptions.UserNotFound;
import listeners.ButtonListener;
import listeners.LineupListener;

/**
 * This class represents the panel where users can switch around their player positions.
 * @author charisannelim
 *
 */
public class ChangeLineup extends JPanel {
	private JPanel screens;
	private Font headerFont;
	private Font textFont;
	private BufferedImage bg = null;
	private FirstDraft draft;
	private User user_obj;
	
	/**
	 * This constructor points the parameters as the current instance.
	 * @param draft, drafting screen
	 * @param screens, the main JPanel with cardlayout
	 * @param headerFont, the custom font
	 * @param textFont, the custom font
	 */
	public ChangeLineup(FirstDraft draft, JPanel screens, Font headerFont, Font textFont) {
		this.draft = draft;
		this.screens = screens;
		this.headerFont = headerFont;
		this.textFont = textFont;
	}
	
	/** 
	 * This method adds all the necessary components and players to the panel
	 * and takes an array of players in the order they should be added
	 * to the panel. 
	 * @param lineup_arr, an array of player IDs with the index representing order of adding
	 */
	public void create(User user_obj, int[] lineup_arr) {	
		Font smol = headerFont.deriveFont((float) 14);
		
		//Create Main Panel
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(new EmptyBorder(15, 10, 0, 10));
		
		JLabel user = new JLabel("MY PLAYERS", JLabel.CENTER);
		user.setForeground(Color.WHITE);
		user.setFont(headerFont);
		user.setAlignmentX(CENTER_ALIGNMENT);
		
		
		JPanel pitch = new JPanel();
		pitch.setLayout(new BoxLayout(pitch, BoxLayout.X_AXIS));
		pitch.setMaximumSize(new Dimension(950,550));
		pitch.setOpaque(false);
		pitch.setBackground(new Color(0,0,0,0));
		
		//Pitch View and Team
		LineupListener gl = new LineupListener(user_obj, this, lineup_arr, textFont);		
		PitchView view = new PitchView(gl, lineup_arr, this, textFont, draft);
			
		JPanel team = new JPanel();
		team.setLayout(new BoxLayout(team, BoxLayout.Y_AXIS));
		team.setMaximumSize(new Dimension(150, 550));
		team.setBorder(new EmptyBorder(0, 20, 10, 20));
		team.setBackground(new Color(104, 0, 0));
		team.setAlignmentY(TOP_ALIGNMENT);
		
		pitch.add(view);
		pitch.add(Box.createRigidArea(new Dimension(10, 0)));
		pitch.add(team);
		
		//Team Stuff
		Font sm0l = headerFont.deriveFont((float) 14);
		JLabel myTeam = new JLabel("Substitutes");
		myTeam.setFont(sm0l);
		myTeam.setForeground(Color.WHITE);
		myTeam.setAlignmentX(CENTER_ALIGNMENT);
		
		if (lineup_arr[0] != -1) {
		
		List<MakePlayer> startingList = new ArrayList<MakePlayer>();
		
		for (int i = 11; i < 17; i++) {
			try {
				startingList.add(new MakePlayer(textFont, new Player(lineup_arr[i])));
			} catch (FileNotFoundException | ResultsReadError | UserNotFound e) {
				e.printStackTrace();
			}
		}
		
		for (int i = 0; i < 6; i++) {
			team.add(startingList.get(i));
			startingList.get(i).addMouseListener(gl);
			startingList.get(i).setName(Integer.toString(startingList.get(i).getID()));
		}
		
	}
		
		//Save Button
		JButton save = new JButton("Save Lineup");
		save.setFont(smol);
		save.setAlignmentX(CENTER_ALIGNMENT);
		
		ButtonListener bl = new ButtonListener(screens);
		save.setActionCommand("home");
		save.addActionListener(bl);
		save.addActionListener(gl);

	    //Add everything to main panel
		this.add(user);
		this.add(Box.createRigidArea(new Dimension(0, 20)));
		this.add(pitch);
		this.add(Box.createRigidArea(new Dimension(0, 15)));
		this.add(save);
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
