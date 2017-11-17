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
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import listeners.ButtonListener;

public class ChangeLineup extends JPanel {
	private JPanel screens;
	private Font headerFont;
	private Font textFont;
	private BufferedImage bg = null;
	
	public ChangeLineup(JPanel screens, Font headerFont, Font textFont) {
		this.screens = screens;
		this.headerFont = headerFont;
		this.textFont = textFont;
	}
	
	public void create() {
		
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
		PitchView view = new PitchView(smol);
		
		JPanel team = new JPanel();
		team.setLayout(new BoxLayout(team, BoxLayout.Y_AXIS));
		team.setMaximumSize(new Dimension(150, 550));
		team.setBorder(new EmptyBorder(10, 20, 10, 20));
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
		
//		team.add(myTeam);
//		team.add(Box.createRigidArea(new Dimension(0, 10)));
		
		/* EXAMPLE */
		ImageIcon image5 = new ImageIcon(getClass().getResource("test.png"));
		MakePlayer twelve = new MakePlayer(sm0l, image5, "Basil Chan","GK");
		MakePlayer thirteen = new MakePlayer(sm0l, image5, "Emmeric Ong","MF");
		MakePlayer fourteen = new MakePlayer(sm0l, image5, "Emmeric Ong","MF");
		MakePlayer fifteen = new MakePlayer(sm0l, image5, "Emmeric Ong","MF");
		MakePlayer sixteen = new MakePlayer(sm0l, image5, "Basil Chan","GK");
		MakePlayer seventeen = new MakePlayer(sm0l, image5, "Emmeric Ong","MF");
		
		team.add(twelve);
		team.add(thirteen);
		team.add(fourteen);
		team.add(fifteen);
		team.add(sixteen);
		team.add(seventeen);
		
		//Save Button
		JButton save = new JButton("Save Lineup");
		save.setFont(smol);
		save.setAlignmentX(CENTER_ALIGNMENT);
		
		ButtonListener bl = new ButtonListener(screens);
		save.setActionCommand("home");
		save.addActionListener(bl);

	    //Add everything to main panel
		this.add(user);
		this.add(Box.createRigidArea(new Dimension(0, 20)));
		this.add(pitch);
		this.add(Box.createRigidArea(new Dimension(0, 15)));
		this.add(save);
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
