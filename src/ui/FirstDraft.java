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
import clientObjects.User;
import exceptions.ResultsReadError;
import exceptions.UserNotFound;
import listeners.ButtonListener;
import listeners.DraftListener;

/**
 * This class represents the GUI of the drafting process where users come together to draft
 * and take turns choosing their players.
 * @author charisannelim
 *
 */
public class FirstDraft extends JPanel {
	
	private JPanel screens;
	private Font headerFont;
	private Font textFont;
	private ChangeLineup cl; 
	private BufferedImage bg = null;
	private int[] arr;
	private int[] bigarr;
	private boolean isDrafted = false;
	
	/**
	 * This class takes the array of all S league players in order.
	 * @param bigarr, array of IDs of all S league players
	 * @param screens, main JPanel with cardlayout
	 * @param headerFont, custom font
	 * @param textFont, custom font
	 */
	public FirstDraft(int[] bigarr, JPanel screens, Font headerFont, Font textFont) {
		this.screens = screens;
		this.headerFont = headerFont;
		this.textFont = textFont;
		this.bigarr = bigarr;
	}

	
	/** 
	 * This method adds all the necessary components and players to the panel
	 * and takes an array of players in the order they should be added
	 * to the panel. 
	 * @param user_obj, takes in current user's details
	 * @param bigarr, an array of all player IDs with the index representing order of adding
	 */
	public void create(User user_obj, int[] bigarr) {
		
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
		test.setPreferredSize(new Dimension(800,5500));
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
		player.setPreferredSize(new Dimension(600, 5500));
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
		chosen.setPreferredSize(new Dimension(150, 5500));
		chosen.setBorder(new EmptyBorder(20, 0, 20, 0));
		chosen.setBackground(new Color(104, 0, 0));
		c.insets = new Insets(0, 0, 0, 0);
		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridx = 1;
		c.gridy = 0;
		test.add(chosen, c);
		
		//Chosen Box
		
		JLabel myTeam = new JLabel("My Team");
		myTeam.setFont(headerFont);
		myTeam.setForeground(Color.WHITE);
		myTeam.setAlignmentX(CENTER_ALIGNMENT);
		
		chosen.add(myTeam);
		chosen.add(Box.createRigidArea(new Dimension(0, 10)));
		
		//Listener
		arr = new int[17];
		for (int i = 0; i < 17; i++) {
			arr[i] = -1;
		}
		DraftListener gl = new DraftListener(user_obj, arr, this, chosen, screens, headerFont, textFont);
		
		//ALBIREX NIIGATA	
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
		
		//1 to 23
		for (int i = 0; i < 23; i++) {
			try {
				if (bigarr[i] != 0) {
					albirexList.add(new MakePlayer(textFont, new Player(bigarr[i])));
				}
			} catch (FileNotFoundException | ResultsReadError | UserNotFound e) {
				e.printStackTrace();
			}
		}
		
		for (int i = 0; i < albirexList.size(); i++)
		{
			albirex_players.add(albirexList.get(i));
			albirexList.get(i).addMouseListener(gl);
			albirexList.get(i).setName(Integer.toString(albirexList.get(i).getID()));
		}
		
		//Balestier Khalsa
		JLabel balestier = new JLabel("Balestier Khalsa ");
		balestier.setAlignmentX(CENTER_ALIGNMENT);
		balestier.setFont(headerFont);
		balestier.setForeground(Color.white);
		
		JPanel balestier_players = new JPanel(new GridLayout(4, 6, 10, 10));
		balestier_players.setAlignmentX(CENTER_ALIGNMENT);
		balestier_players.setMaximumSize(new Dimension(600, 500));
		balestier_players.setOpaque(false);
		balestier_players.setBackground(new Color(0,0,0,0));
		
		List<MakePlayer> balestierList = new ArrayList<MakePlayer>();
		
		//24 to 43
		for (int i = 23; i < 43; i++) {
			try {
				if (bigarr[i] != 0) {
				balestierList.add(new MakePlayer(textFont, new Player(bigarr[i])));
				}
			} catch (FileNotFoundException | ResultsReadError | UserNotFound e) {
				e.printStackTrace();
			}
		}
		
		for (int i = 0; i < balestierList.size(); i++)
		{
			balestier_players.add(balestierList.get(i));
			balestierList.get(i).addMouseListener(gl);
			balestierList.get(i).setName(Integer.toString(balestierList.get(i).getID()));
		}

		//Brunei DPMM
		JLabel brunei = new JLabel("Brunei DPMM");
		brunei.setFont(headerFont);
		brunei.setAlignmentX(CENTER_ALIGNMENT);
		brunei.setForeground(Color.white);
		
		JPanel brunei_players = new JPanel(new GridLayout(4, 6, 10, 10));
		brunei_players.setAlignmentX(CENTER_ALIGNMENT);
		brunei_players.setMaximumSize(new Dimension(600, 500));
		brunei_players.setOpaque(false);
		brunei_players.setBackground(new Color(0,0,0,0));
		
		List<MakePlayer> bruneiList = new ArrayList<MakePlayer>();
		
		//44  to 68
		for (int i = 43; i < 68; i++) {
			try {
				if (bigarr[i] != 0) {
					bruneiList.add(new MakePlayer(textFont, new Player(bigarr[i])));
				}
			} catch (FileNotFoundException | ResultsReadError | UserNotFound e) {
				e.printStackTrace();
			}
		}
		
		for (int i = 0; i < bruneiList.size(); i++)
		{
			brunei_players.add(bruneiList.get(i));
			bruneiList.get(i).addMouseListener(gl);
			bruneiList.get(i).setName(Integer.toString(bruneiList.get(i).getID()));
		}
		
		//garena
		JLabel garena = new JLabel("Garena Young Lions");
		garena.setFont(headerFont);
		garena.setAlignmentX(CENTER_ALIGNMENT);
		garena.setForeground(Color.white);
				
		JPanel garena_players = new JPanel(new GridLayout(5, 6, 10, 10));
		garena_players.setAlignmentX(CENTER_ALIGNMENT);
		garena_players.setMaximumSize(new Dimension(600, 500));
		garena_players.setOpaque(false);
		garena_players.setBackground(new Color(0,0,0,0));
				
		List<MakePlayer> garenaList = new ArrayList<MakePlayer>();
				
		//69 to 95
		for (int i = 68; i < 95; i++) {
			try {
				if (bigarr[i] != 0) {
					garenaList.add(new MakePlayer(textFont, new Player(bigarr[i])));
				}
			} catch (FileNotFoundException | ResultsReadError | UserNotFound e) {
				e.printStackTrace();
			}
		}
				
		for (int i = 0; i < garenaList.size(); i++)
		{
			garena_players.add(garenaList.get(i));
			garenaList.get(i).addMouseListener(gl);
			garenaList.get(i).setName(Integer.toString(garenaList.get(i).getID()));
		}
		
		//geylang
		JLabel geylang = new JLabel("Geylang International FC");
		geylang.setFont(headerFont);
		geylang.setAlignmentX(CENTER_ALIGNMENT);
		geylang.setForeground(Color.white);
						
		JPanel geylang_players = new JPanel(new GridLayout(4, 6, 10, 10));
		geylang_players.setAlignmentX(CENTER_ALIGNMENT);
		geylang_players.setMaximumSize(new Dimension(600, 500));
		geylang_players.setOpaque(false);
		geylang_players.setBackground(new Color(0,0,0,0));
						
		List<MakePlayer> geylangList = new ArrayList<MakePlayer>();
					
		//95 to 115
		for (int i = 95; i < 115; i++) {
			try {
				if (bigarr[i] != 0) {
					geylangList.add(new MakePlayer(textFont, new Player(bigarr[i])));
				}
			} catch (FileNotFoundException | ResultsReadError | UserNotFound e) {
				e.printStackTrace();
			}
		}
						
		for (int i = 0; i < geylangList.size(); i++)
		{
			geylang_players.add(geylangList.get(i));
			geylangList.get(i).addMouseListener(gl);
			geylangList.get(i).setName(Integer.toString(geylangList.get(i).getID()));
		}
		
				//homeU
				JLabel homeU = new JLabel("Home United FC");
				homeU.setFont(headerFont);
				homeU.setAlignmentX(CENTER_ALIGNMENT);
				homeU.setForeground(Color.white);
						
				JPanel homeU_players = new JPanel(new GridLayout(4, 6, 10, 10));
				homeU_players.setAlignmentX(CENTER_ALIGNMENT);
				homeU_players.setMaximumSize(new Dimension(600, 500));
				homeU_players.setOpaque(false);
				homeU_players.setBackground(new Color(0,0,0,0));
						
				List<MakePlayer> homeUList = new ArrayList<MakePlayer>();
					
				//116 to 137
				for (int i = 115; i < 137; i++) {
					try {
						if (bigarr[i] != 0) {
							homeUList.add(new MakePlayer(textFont, new Player(bigarr[i])));
						}
					} catch (FileNotFoundException | ResultsReadError | UserNotFound e) {
						e.printStackTrace();
					}
				}
						
				for (int i = 0; i < homeUList.size(); i++)
				{
					homeU_players.add(homeUList.get(i));
					homeUList.get(i).addMouseListener(gl);
					homeUList.get(i).setName(Integer.toString(homeUList.get(i).getID()));
				}		

				//hougang
				JLabel hougang = new JLabel("Hougang United FC");
				hougang.setFont(headerFont);
				hougang.setAlignmentX(CENTER_ALIGNMENT);
				hougang.setForeground(Color.white);
						
				JPanel hougang_players = new JPanel(new GridLayout(4, 6, 10, 10));
				hougang_players.setAlignmentX(CENTER_ALIGNMENT);
				hougang_players.setMaximumSize(new Dimension(600, 500));
				hougang_players.setOpaque(false);
				hougang_players.setBackground(new Color(0,0,0,0));
						
				List<MakePlayer> hougangList = new ArrayList<MakePlayer>();
						
				//138 to 159
				for (int i = 137; i < 159; i++) {
					try {
						if (bigarr[i] != 0) {
							hougangList.add(new MakePlayer(textFont, new Player(bigarr[i])));
						}
					} catch (FileNotFoundException | ResultsReadError | UserNotFound e) {
						e.printStackTrace();
					}
				}
						
				for (int i = 0; i < hougangList.size(); i++)
				{
					hougang_players.add(hougangList.get(i));
					hougangList.get(i).addMouseListener(gl);
					hougangList.get(i).setName(Integer.toString(hougangList.get(i).getID()));
				}
				
				//tampines
				JLabel tampines = new JLabel("Tampines Rovers");
				tampines.setFont(headerFont);
				tampines.setAlignmentX(CENTER_ALIGNMENT);
				tampines.setForeground(Color.white);
						
				JPanel tampines_players = new JPanel(new GridLayout(4, 6, 10, 10));
				tampines_players.setAlignmentX(CENTER_ALIGNMENT);
				tampines_players.setMaximumSize(new Dimension(600, 500));
				tampines_players.setOpaque(false);
				tampines_players.setBackground(new Color(0,0,0,0));
						
				List<MakePlayer> tampinesList = new ArrayList<MakePlayer>();
						
				//160 to 183
				for (int i = 159; i < 183; i++) {
					try {
						if (bigarr[i] != 0) {
							tampinesList.add(new MakePlayer(textFont, new Player(bigarr[i])));
						}
					} catch (FileNotFoundException | ResultsReadError | UserNotFound e) {
						e.printStackTrace();
					}
				}
						
				for (int i = 0; i < tampinesList.size(); i++)
				{
					tampines_players.add(tampinesList.get(i));
					tampinesList.get(i).addMouseListener(gl);
					tampinesList.get(i).setName(Integer.toString(tampinesList.get(i).getID()));
				}	

				//warriors
				JLabel warriors = new JLabel("Warriors FC");
				warriors.setFont(headerFont);
				warriors.setAlignmentX(CENTER_ALIGNMENT);
				warriors.setForeground(Color.white);
						
				JPanel warriors_players = new JPanel(new GridLayout(4, 6, 10, 10));
				warriors_players.setAlignmentX(CENTER_ALIGNMENT);
				warriors_players.setMaximumSize(new Dimension(600, 500));
				warriors_players.setOpaque(false);
				warriors_players.setBackground(new Color(0,0,0,0));
						
				List<MakePlayer> warriorsList = new ArrayList<MakePlayer>();
					
				//184 to 203
				for (int i = 183; i < 203; i++) {
					try {
						if (bigarr[i] != 0) {
							warriorsList.add(new MakePlayer(textFont, new Player(bigarr[i])));
						}
					} catch (FileNotFoundException | ResultsReadError | UserNotFound e) {
						e.printStackTrace();
					}
				}
						
				for (int i = 0; i < warriorsList.size(); i++)
				{
					warriors_players.add(warriorsList.get(i));
					warriorsList.get(i).addMouseListener(gl);
					warriorsList.get(i).setName(Integer.toString(warriorsList.get(i).getID()));
				}
		
		player.add(albirex);
		player.add(Box.createRigidArea(new Dimension(0, 30)));
		player.add(albirex_players);
		player.add(Box.createRigidArea(new Dimension(0, 30)));
		player.add(balestier);
		player.add(Box.createRigidArea(new Dimension(0, 30)));
		player.add(balestier_players);
		player.add(Box.createRigidArea(new Dimension(0, 30)));
		player.add(brunei);
		player.add(Box.createRigidArea(new Dimension(0, 30)));
		player.add(brunei_players);
		player.add(Box.createRigidArea(new Dimension(0, 30)));
		player.add(garena);
		player.add(Box.createRigidArea(new Dimension(0, 30)));
		player.add(garena_players);
		player.add(Box.createRigidArea(new Dimension(0, 30)));
		player.add(geylang);
		player.add(Box.createRigidArea(new Dimension(0, 30)));
		player.add(geylang_players);
		player.add(Box.createRigidArea(new Dimension(0, 30)));
		player.add(homeU);
		player.add(Box.createRigidArea(new Dimension(0, 30)));
		player.add(homeU_players);
		player.add(Box.createRigidArea(new Dimension(0, 30)));
		player.add(hougang);
		player.add(Box.createRigidArea(new Dimension(0, 30)));
		player.add(hougang_players);
		player.add(Box.createRigidArea(new Dimension(0, 30)));
		player.add(tampines);
		player.add(Box.createRigidArea(new Dimension(0, 30)));
		player.add(tampines_players);
		player.add(Box.createRigidArea(new Dimension(0, 30)));
		player.add(warriors);
		player.add(Box.createRigidArea(new Dimension(0, 30)));
		player.add(warriors_players);
		
		
	}
	
	/**
	 * This getter returns the IDs of the user's chosen 17 players
	 * @return an array of 17 player IDs
	 */
	public int[] getArr() {
		return arr;
	}
	
	/**
	 * This setter sets the IDs of the user's chosen 17 players in an array
	 * @param newarr array of 17 player IDs
	 * @return array of 17 player IDs
	 */
	public int[] setArr(int[] newarr) {
		arr = new int[17];
		arr = newarr;
		return arr;
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
