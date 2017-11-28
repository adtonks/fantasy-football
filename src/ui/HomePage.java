package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
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

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;

import clientFunctions.Sfunctions;
import clientObjects.LeaderBoard;
import clientObjects.User;
import exceptions.IndexDoesNotExist;
import exceptions.ResultsReadError;
import exceptions.UserNotFound;
import listeners.ButtonListener;

/**
 * This class represents the HomePage that the users sees with the
 * Leaderboard, the upcoming Match details and user options such as to
 * view and switch their players, log out or refresh the leaderboard.
 * @author charisannelim
 *
 */
public class HomePage extends JPanel {
	
	private JPanel screens;
	private String username;
	private Font headerFont;
	private Font textFont;
	private BufferedImage bg = null;
	private LeaderBoard leaderboard;
	private int[] anotherarr;
	
/**
  * This constructor points the parameters as the current instance.
	 * @param screens, main JPanel with cardlayout
	 * @param headerFont, custom font
	 * @param textFont, custom font
 */
	public HomePage(JPanel screens, Font headerFont, Font textFont) {
		this.screens = screens;
		this.headerFont = headerFont;
		this.textFont = textFont;
	}

	/**
	 * This method adds all necessary components to the JFrame.
	 * @param User, user object
	 */
	public void create(User user_obj) {
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		Font sm0l = headerFont.deriveFont((float) 14);
		
		JLabel user = new JLabel("Welcome, " + user_obj.getUsername() + "  ");
		user.setForeground(Color.WHITE);
		user.setFont(headerFont);
		c.insets = new Insets(0, 20, 20, 20);
		c.gridwidth = 2;
	    c.gridx = 0;
	    c.gridy = 0;
	    this.add(user, c);
		
		JPanel games = new JPanel();
		games.setLayout(new BoxLayout(games, BoxLayout.Y_AXIS));
		games.setPreferredSize(new Dimension(400, 500));
		games.setOpaque(true);
		games.setBackground(new Color(104, 0, 0));
		games.setBorder(new EmptyBorder(20, 15, 20, 10));
		c.insets = new Insets(0, 0, 0, 40);
		c.gridwidth = 1;
		c.gridheight = 2;
		c.gridx = 0;
		c.gridy = 1;
		this.add(games, c);
		
		JPanel upcomingMatch = new JPanel();
		upcomingMatch.setLayout(new BorderLayout());
		upcomingMatch.setPreferredSize(new Dimension(400, 170));
		upcomingMatch.setBorder(new EmptyBorder(20, 20, 20, 20));
		c.insets = new Insets(0, 20, 30, 0);
		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridx = 1;
		c.gridy = 1;
		this.add(upcomingMatch, c);
		
		JPanel menu = new JPanel();
		menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
		menu.setPreferredSize(new Dimension(400, 300));
		menu.setOpaque(true);
		menu.setBackground(new Color(104, 0, 0));
		menu.setBorder(new EmptyBorder(20, 20, 20, 20));
		c.insets = new Insets(0, 20, 0, 0);
		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridx = 1;
		c.gridy = 2;
		this.add(menu, c);
		
		ButtonListener buttonListener = new ButtonListener(screens);

		//Ongoing Games Panel
		String groupname = "GAME" + Integer.toString(user_obj.getGameID());
		JLabel name = new JLabel(groupname);
		name.setForeground(Color.WHITE);
		name.setAlignmentX(CENTER_ALIGNMENT);
		name.setFont(headerFont);
		
		JLabel lb = new JLabel("LEADERBOARD");
		lb.setForeground(Color.WHITE);
		lb.setAlignmentX(CENTER_ALIGNMENT);
		lb.setFont(sm0l);
		
		String[] columnNames = {"RANK", "PLAYER", "POINTS"};
		
		leaderboard = Sfunctions.sGetBoard(user_obj.getGameID());
		
		LeaderboardTable leaderboard_table = new LeaderboardTable(user_obj, leaderboard, columnNames, textFont);    
		leaderboard_table.create();
		
		games.add(name);
		games.add(lb);
		games.add(Box.createRigidArea(new Dimension(0, 15)));
		games.add(leaderboard_table);
		
		//Upcoming Match Panel
		JLabel match = new JLabel("Upcoming Matches");
		match.setFont(headerFont);
		match.setHorizontalAlignment(JLabel.CENTER);
		
		//DEPENDING ON THE MATCH CSV it will get different images
		ImageIcon image1 = new ImageIcon(getClass().getResource("tampines-rovers-fc.png"));
		JLabel team1 = new JLabel("", image1, JLabel.CENTER);
		
		JLabel vs = new JLabel("VS");
		vs.setFont(headerFont);
		vs.setHorizontalAlignment(JLabel.CENTER);
		
		//DEPENDING ON THE MATCH CSV it will get different images
		ImageIcon image2 = new ImageIcon(getClass().getResource("warriors-fc.png"));
		JLabel team2 = new JLabel("", image2, JLabel.CENTER);
		
		//DEPENDING ON THE MATCH CSV it will get different times
		JLabel details = new JLabel("15 November 2017, 8.00pm");
		details.setFont(textFont);
		details.setHorizontalAlignment(JLabel.CENTER);
		
		upcomingMatch.add(match, BorderLayout.PAGE_START);
		upcomingMatch.add(team1, BorderLayout.LINE_START);
		upcomingMatch.add(vs, BorderLayout.CENTER);
		upcomingMatch.add(team2, BorderLayout.LINE_END);
		upcomingMatch.add(details, BorderLayout.PAGE_END);
		
		//Menu Panel
		
		//Labels depending on whether you are host or not
		JLabel host_msg = new JLabel("You have already drafted your team.  ");
		host_msg.setFont(textFont);
		host_msg.setAlignmentX(CENTER_ALIGNMENT);
		host_msg.setHorizontalAlignment(JLabel.CENTER);
		host_msg.setForeground(Color.white);
		
		//Host's Button
		JButton draft = new JButton("Start Draft");
		draft.setFont(sm0l);
		draft.setAlignmentX(CENTER_ALIGNMENT);
		draft.setAlignmentY(BOTTOM_ALIGNMENT);
		draft.setHorizontalAlignment(JLabel.CENTER);
		draft.setMaximumSize(new Dimension(200, 40));
		draft.addActionListener(buttonListener);
		draft.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		draft.setActionCommand("draft");
	
		
		JButton changeplayers = new JButton("View My Players");
		changeplayers.setActionCommand("changeplayers");
		changeplayers.addActionListener(buttonListener);
		changeplayers.setFont(sm0l);
		changeplayers.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		changeplayers.setAlignmentX(CENTER_ALIGNMENT);
		changeplayers.setMaximumSize(new Dimension(200, 40));

		JButton refresh = new JButton("Refresh Leaderboard");
		refresh.setActionCommand("refresh");
		refresh.addActionListener(new ActionListener() {
			
			 @Override
	            public void actionPerformed(ActionEvent e) {
				 games.removeAll();
				 games.add(name);
				 games.add(lb);
				 games.add(Box.createRigidArea(new Dimension(0, 15)));
				 
				 leaderboard = Sfunctions.sGetBoard(user_obj.getGameID());
					
				 LeaderboardTable leaderboard_table = new LeaderboardTable(user_obj, leaderboard, columnNames, textFont); 
				 leaderboard_table.create();
				 
				 games.add(leaderboard_table);
				 games.revalidate();
				 games.repaint();
			 }
			
		});
		refresh.setFont(sm0l);
		refresh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		refresh.setAlignmentX(CENTER_ALIGNMENT);
		refresh.setMaximumSize(new Dimension(200, 40));
		
		JButton help = new JButton("HELP");
		help.setActionCommand("help");
		help.addActionListener(buttonListener);
		help.setFont(sm0l);
		help.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		help.setAlignmentX(CENTER_ALIGNMENT);
		help.setMaximumSize(new Dimension(200, 40));
		
		JButton logout = new JButton("Log Out");
		logout.setActionCommand("logout");
		logout.addActionListener(buttonListener);
		logout.setFont(sm0l);
		logout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		logout.setAlignmentX(CENTER_ALIGNMENT);
		logout.setMaximumSize(new Dimension(200, 40));
		
		if (user_obj.hasDrafted()) {
			menu.add(host_msg);
			menu.add(Box.createRigidArea(new Dimension(0, 10)));
			menu.add(draft);
			draft.setEnabled(false);
		}
			
		else {
			menu.add(Box.createRigidArea(new Dimension(0, 10)));
			menu.add(draft);
		}
	
		menu.add(Box.createRigidArea(new Dimension(0, 10)));
		menu.add(changeplayers);
		menu.add(Box.createRigidArea(new Dimension(0, 10)));
		menu.add(refresh);
		menu.add(Box.createRigidArea(new Dimension(0, 10)));
		menu.add(help);
		menu.add(Box.createRigidArea(new Dimension(0, 10)));
		menu.add(logout);
		
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
