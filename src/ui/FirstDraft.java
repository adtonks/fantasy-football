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
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

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
		player.setBorder(new EmptyBorder(20, 20, 20, 20));
		//player.setAlignmentX(CENTER_ALIGNMENT);
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
		chosen.setBorder(new EmptyBorder(20, 20, 20, 20));
		chosen.setBackground(new Color(104, 0, 0));
		c.insets = new Insets(0, 0, 0, 0);
		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridx = 1;
		c.gridy = 0;
		test.add(chosen, c);
		
		//Add Players to Players Component
		ImageIcon image = new ImageIcon(getClass().getResource("ali_hudzaifi.png"));
		ImageIcon image2 = new ImageIcon(getClass().getResource("ridhuan_barudin.png"));
		ImageIcon image3 = new ImageIcon(getClass().getResource("anders_aplin.png"));
		ImageIcon image4 = new ImageIcon(getClass().getResource("syazwan_buhari.png"));
		ImageIcon image5 = new ImageIcon(getClass().getResource("emmeric_ong.png"));
		ImageIcon image6 = new ImageIcon(getClass().getResource("basil_chan.png"));
		ImageIcon image7 = new ImageIcon(getClass().getResource("test.png"));
		
		JLabel team1 = new JLabel("Albirex Niigata FC(S)  ");
		team1.setFont(headerFont);
		team1.setAlignmentX(CENTER_ALIGNMENT);
		team1.setForeground(Color.white);

		JPanel team1_players = new JPanel(new GridLayout(2, 5, 10, 10));
		team1_players.setMaximumSize(new Dimension(500, 300));
		team1_players.setAlignmentX(CENTER_ALIGNMENT);
		team1_players.setOpaque(false);
		team1_players.setBackground(new Color(0,0,0,0));

		MakePlayer one = new MakePlayer(textFont, image, "Ali Huzaifi","MF");
		MakePlayer two = new MakePlayer(textFont, image, "Ali Huzaifi","MF");
		MakePlayer three = new MakePlayer(textFont, image, "Ali Huzaifi","MF");
		MakePlayer four = new MakePlayer(textFont, image, "Ali Huzaifi","MF");
		MakePlayer five = new MakePlayer(textFont, image, "Ali Huzaifi","DF");
		MakePlayer six = new MakePlayer(textFont, image, "Ali Huzaifi","DF");
		MakePlayer seven = new MakePlayer(textFont, image, "Ali Huzaifi","DF");
		MakePlayer eight = new MakePlayer(textFont, image, "Ali Huzaifi","DF");
		MakePlayer nine = new MakePlayer(textFont, image2, "Ridhuan Barudin","GK");
		MakePlayer ten = new MakePlayer(textFont, image2, "Ridhuan Barudin","GK");
		
		team1_players.add(one);
		team1_players.add(two);
		team1_players.add(three);
		team1_players.add(four);
		team1_players.add(five);
		team1_players.add(six);
		team1_players.add(seven);
		team1_players.add(eight);
		team1_players.add(nine);
		team1_players.add(ten);
		
		JLabel team2 = new JLabel("Geylang International FC");
		team2.setAlignmentX(CENTER_ALIGNMENT);
		team2.setFont(headerFont);
		team2.setForeground(Color.white);
		
		
		JPanel team2_players = new JPanel(new GridLayout(2, 5, 10, 10));
		team2_players.setAlignmentX(CENTER_ALIGNMENT);
		team2_players.setMaximumSize(new Dimension(500, 300));
		team2_players.setOpaque(false);
		team2_players.setBackground(new Color(0,0,0,0));

		MakePlayer one2 = new MakePlayer(textFont, image3, "Anders Aplin","MF");
		MakePlayer two2 = new MakePlayer(textFont, image3, "Anders Aplin","MF");
		MakePlayer three2 = new MakePlayer(textFont, image3, "Anders Aplin","MF");
		MakePlayer four2 = new MakePlayer(textFont, image3, "Anders Aplin","MF");
		MakePlayer five2 = new MakePlayer(textFont, image3, "Anders Aplin","MF");
		MakePlayer six2 = new MakePlayer(textFont, image3, "Anders Aplin","MF");
		MakePlayer seven2 = new MakePlayer(textFont, image3, "Anders Aplin","MF");
		MakePlayer eight2 = new MakePlayer(textFont, image3, "Anders Aplin","MF");
		MakePlayer nine2 = new MakePlayer(textFont, image4, "Syazwan Buhari","GK");
		MakePlayer ten2 = new MakePlayer(textFont, image4, "Syazwan Buhari","GK");
		
		team2_players.add(one2);
		team2_players.add(two2);
		team2_players.add(three2);
		team2_players.add(four2);
		team2_players.add(five2);
		team2_players.add(six2);
		team2_players.add(seven2);
		team2_players.add(eight2);
		team2_players.add(nine2);
		team2_players.add(ten2);

		JLabel team3 = new JLabel("Hougang United FC");
		team3.setFont(headerFont);
		team3.setAlignmentX(CENTER_ALIGNMENT);
		team3.setForeground(Color.white);
		
		JPanel team3_players = new JPanel(new GridLayout(2, 6, 10, 10));
		team3_players.setAlignmentX(CENTER_ALIGNMENT);
		team3_players.setMaximumSize(new Dimension(500, 300));
		team3_players.setOpaque(false);
		team3_players.setBackground(new Color(0,0,0,0));

		MakePlayer one3 = new MakePlayer(textFont, image7, "Emmeric Ong","MF");
		MakePlayer two3 = new MakePlayer(textFont, image7, "Emmeric Ong","MF");
		MakePlayer three3 = new MakePlayer(textFont, image7, "Emmeric Ong","MF");
		MakePlayer four3 = new MakePlayer(textFont, image7, "Emmeric Ong","MF");
		MakePlayer five3 = new MakePlayer(textFont, image7, "Emmeric Ong","MF");
		MakePlayer six3 = new MakePlayer(textFont, image7, "Emmeric Ong","MF");
		MakePlayer seven3 = new MakePlayer(textFont, image7, "Emmeric Ong","MF");
		MakePlayer eight3 = new MakePlayer(textFont, image7, "Emmeric Ong","MF");
		MakePlayer nine3 = new MakePlayer(textFont, image7, "Basil Chan","GK");
		MakePlayer ten3 = new MakePlayer(textFont, image7, "Basil Chan","GK");
		
		team3_players.add(one3);
		team3_players.add(two3);
		team3_players.add(three3);
		team3_players.add(four3);
		team3_players.add(five3);
		team3_players.add(six3);
		team3_players.add(seven3);
		team3_players.add(eight3);
		team3_players.add(nine3);
		team3_players.add(ten3);
		
		player.add(team1);
		player.add(Box.createRigidArea(new Dimension(0, 30)));
		player.add(team1_players);
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
		
		for (Component h : team1_players.getComponents()) {
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
