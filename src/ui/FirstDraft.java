package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import clientObjects.Player;
import exceptions.ResultsReadError;
import exceptions.UserNotFound;
import listeners.ButtonListener;
import listeners.SnaptoGridListener;

public class FirstDraft extends JPanel {
	
	private JPanel screens;
	private Font headerFont;
	private Font textFont;
	private BufferedImage bg = null;
	
	public FirstDraft(JPanel screens, Font headerFont, Font textFont) {
		this.screens = screens;
		this.headerFont = headerFont;
		this.textFont = textFont;
	}

	public void create() {
		
		//Create Main Panel
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.setBorder(new EmptyBorder(15, 10, 0, 10));
		this.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel user = new JLabel("DRAFT");
		user.setForeground(Color.WHITE);
		user.setFont(headerFont);
		user.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel test = new JPanel();
		test.setLayout(new GridBagLayout());
		test.setPreferredSize(new Dimension(800,2000));
		test.setOpaque(false);
		test.setBackground(new Color(0,0,0,0));
		
		//Scrolling
		JScrollPane scrollFrame = new JScrollPane(test);
		test.setAutoscrolls(true);
		scrollFrame.setPreferredSize(new Dimension(800,550));
		scrollFrame.getViewport().setOpaque(false);
	    scrollFrame.setBorder(null);
	    scrollFrame.setOpaque(false);
		
		this.add(user);
		this.add(Box.createRigidArea(new Dimension(0, 10)));
		this.add(scrollFrame);
				
		//Create Player and Chosen Team components
		GridBagConstraints c = new GridBagConstraints();

		JPanel player = new JPanel();
		player.setLayout(new BoxLayout(player, BoxLayout.Y_AXIS));
		player.setPreferredSize(new Dimension(600, 2000));
		player.setBorder(new EmptyBorder(20, 10, 20, 10));
		player.setAlignmentY(TOP_ALIGNMENT);
		player.setOpaque(false);
		player.setBackground(new Color(0,0,0,0));
		c.insets = new Insets(0, 40, 0, 0);
		c.gridx = 0;
		c.gridy = 0;
		test.add(player, c);
		
		JPanel chosen = new JPanel();
		chosen.setLayout(new BoxLayout(chosen, BoxLayout.Y_AXIS));
		chosen.setAlignmentX(LEFT_ALIGNMENT);
		chosen.setPreferredSize(new Dimension(150, 2000));
		chosen.setBorder(new EmptyBorder(20, 0, 20, 0));
		chosen.setBackground(new Color(104, 0, 0));
		c.insets = new Insets(0, 0, 0, 0);
		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridx = 1;
		c.gridy = 0;
		test.add(chosen, c);
		
		//ADD ALL THE TEAMS!!!!		
		JLabel albirex = new JLabel("Albirex Niigata FC(S)  ");
		albirex.setFont(headerFont);
		albirex.setAlignmentX(CENTER_ALIGNMENT);
		albirex.setForeground(Color.white);

		JPanel albirex_players = new JPanel(new GridLayout(4, 6, 10, 0));
		albirex_players.setMaximumSize(new Dimension(600, 500));
		albirex_players.setAlignmentX(CENTER_ALIGNMENT);
		albirex_players.setOpaque(false);
		albirex_players.setBackground(new Color(0,0,0,0));
		
		List<MakePlayer> albirexList = new ArrayList<MakePlayer>();
		
		for (int i = 1; i <= 23; i++) {
			try {
				albirexList.add(new MakePlayer(textFont, new Player(i)));
			} catch (FileNotFoundException | ResultsReadError | UserNotFound e) {
				e.printStackTrace();
			}
		}
		
		for (int i = 0; i < 23; i++)
		{
			albirex_players.add(albirexList.get(i));
		}
		
		JLabel team2 = new JLabel("Geylang International FC");
		team2.setAlignmentX(CENTER_ALIGNMENT);
		team2.setFont(headerFont);
		team2.setForeground(Color.white);
		
		
		JPanel team2_players = new JPanel(new GridLayout(2, 5, 10, 10));
		team2_players.setAlignmentX(CENTER_ALIGNMENT);
		team2_players.setMaximumSize(new Dimension(500, 300));
		team2_players.setOpaque(false);
		team2_players.setBackground(new Color(0,0,0,0));

		JLabel team3 = new JLabel("Hougang United FC");
		team3.setFont(headerFont);
		team3.setAlignmentX(CENTER_ALIGNMENT);
		team3.setForeground(Color.white);
		
		JPanel team3_players = new JPanel(new GridLayout(2, 6, 10, 10));
		team3_players.setAlignmentX(CENTER_ALIGNMENT);
		team3_players.setMaximumSize(new Dimension(500, 300));
		team3_players.setOpaque(false);
		team3_players.setBackground(new Color(0,0,0,0));
		
		player.add(albirex);
		player.add(Box.createRigidArea(new Dimension(0, 30)));
		player.add(albirex_players);
		player.add(Box.createRigidArea(new Dimension(0, 30)));
		player.add(team2);
		player.add(Box.createRigidArea(new Dimension(0, 30)));
		player.add(team2_players);
		player.add(Box.createRigidArea(new Dimension(0, 30)));
		player.add(team3);
		player.add(Box.createRigidArea(new Dimension(0, 30)));
		player.add(team3_players);
		
		//Chosen Box
		
		JLabel myTeam = new JLabel("My Team");
		myTeam.setFont(headerFont);
		myTeam.setForeground(Color.WHITE);
		myTeam.setAlignmentX(CENTER_ALIGNMENT);
		
		chosen.add(myTeam);
		chosen.add(Box.createRigidArea(new Dimension(0, 10)));
		
		//Listener
		SnaptoGridListener gl = new SnaptoGridListener(chosen, screens, headerFont);
		
		for (Component h : albirex_players.getComponents()) {
		    if (h instanceof MakePlayer) { 
		       ((MakePlayer)h).addMouseListener(gl);
		    }
		}
		
		for (Component h : team2_players.getComponents()) {
		    if (h instanceof MakePlayer) { 
		       ((MakePlayer)h).addMouseListener(gl);
		    }
		}
		
		for (Component h : team3_players.getComponents()) {
		    if (h instanceof MakePlayer) { 
		       ((MakePlayer)h).addMouseListener(gl);
		    }
		}
		
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
