package server;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import listeners.ButtonListener;

/**
 * This class is for the server GUI
 * @author charisannelim
 *
 */
public class ServerGUI extends JPanel {
	
	private JPanel screens;
	private Font headerFont;
	private Font textFont;
	private String username;
	private BufferedImage bg = null;
	private Lock csvLock;
	
/**
 	 * This constructor points the parameters as the current instance.
	 * @param screens, main JPanel with cardlayout
	 * @param headerFont, custom font
	 * @param textFont, custom font

 */
	public ServerGUI(Lock _csvLock) {
		//Register Custom Fonts
		try {
			this.headerFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("BIG JOHN.otf"));
			this.headerFont = headerFont.deriveFont(0, 20);
			  
			this.textFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("CircularStd-Bold.otf"));
			this.textFont = textFont.deriveFont(0, 14);
				  
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("BIG JOHN.otf")));
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("CircularStd-Bold.otf")));
		} catch (IOException|FontFormatException e) {
			e.printStackTrace();
		}
		this.csvLock = _csvLock;
	}
	
	/**
	 * This method adds all necessary components to the JPanel.
	 */
	public void create() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(new EmptyBorder(60, 30, 30, 30));
		this.setAlignmentX(CENTER_ALIGNMENT);
		
		ServerButtonListener serverButtons = new ServerButtonListener(csvLock);
		
		JLabel user = new JLabel("S.League Fantasy Football Server");
		user.setForeground(Color.WHITE);
		user.setFont(headerFont);
		user.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel none = new JLabel("");
		none.setForeground(Color.WHITE);
		none.setFont(textFont);
		none.setAlignmentX(CENTER_ALIGNMENT);
	    
	    
		JButton join = new JButton("Update with New Results");
		join.setActionCommand("update");
		join.addActionListener(serverButtons);
		join.setMaximumSize(new Dimension(300, 50));
		join.setFont(headerFont);
		join.setAlignmentX(CENTER_ALIGNMENT);
		join.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		
		JButton create = new JButton("End Season");
		create.setActionCommand("end");
		create.addActionListener(serverButtons);
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
		    bg = ImageIO.read(getClass().getResource("bg.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	    super.paintComponent(g);
	    g.drawImage(bg, 0, 0, null);
	}
}
