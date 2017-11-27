package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import clientObjects.Player;
import exceptions.ResultsReadError;
import exceptions.UserNotFound;
import listeners.DraftListener;
import listeners.LineupListener;

/**
 * This class represents the starting line up of 11 players displayed
 * on a rough visual representation of the football field.
 * @author charisannelim
 *
 */
public class PitchView extends JPanel {
	
	private BufferedImage bg = null;
	private DraftListener dl;
	private int[] arr;
	private FirstDraft draft;
	private ChangeLineup cl;
	private LineupListener gl;
//	private User user_obj;

	/**
	 * This class takes a listener, an array of 17 player IDs, the JPanel it belongs to, custom font and the drafting screen
	 * @param gl, listener
	 * @param arr, array of 17 player IDs
	 * @param cl, the class it belongs to
	 * @param textFont, custom font
	 * @param draft, the screen for drafting
	 */
	public PitchView(LineupListener gl, int[] arr, ChangeLineup cl, Font textFont, FirstDraft draft) {
//	public PitchView(LineupListener gl, User user_obj, ChangeLineup cl, Font textFont, FirstDraft draft) {
//		this.user_obj = user_obj;

		this.gl = gl;
		this.arr = arr;
		this.cl = cl;
		
		//Layout of panel
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setMaximumSize(new Dimension(800, 550));
		this.setBorder(new EmptyBorder(20, 20, 20, 20));
		this.setAlignmentY(TOP_ALIGNMENT);
		this.setOpaque(false);
		this.setBackground(new Color(0,0,0,0));
		
		//Adding stuff to panel
		
		//Goalkeepers
		JLabel gk_title = new JLabel("Goalkeeper");
		gk_title.setFont(textFont);
		gk_title.setAlignmentX(CENTER_ALIGNMENT);
		gk_title.setOpaque(true);
		gk_title.setBackground(Color.green);
		gk_title.setMaximumSize(new Dimension(150, 30));
		gk_title.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel gk = new JPanel(new GridLayout(1, 4, 10, 10));
		gk.setMaximumSize(new Dimension(800, 150));
		gk.setOpaque(false);
		gk.setBackground(new Color(0,0,0,0));
		gk.setAlignmentX(CENTER_ALIGNMENT);
	

		//Defenders
		JLabel df_title = new JLabel("Defenders");
		df_title.setFont(textFont);
		df_title.setAlignmentX(CENTER_ALIGNMENT);
		df_title.setBackground(Color.green);
		df_title.setOpaque(true);
		df_title.setMaximumSize(new Dimension(150, 30));
		df_title.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel df = new JPanel(new GridLayout(1, 4, 10, 10));
		df.setMaximumSize(new Dimension(800, 150));
		df.setOpaque(false);
		df.setBackground(new Color(0,0,0,0));
		df.setAlignmentX(CENTER_ALIGNMENT);
		
		//Midfielders
		JLabel mf_title = new JLabel("Midfielders");
		mf_title.setFont(textFont);
		mf_title.setAlignmentX(CENTER_ALIGNMENT);
		mf_title.setBackground(Color.green);
		mf_title.setOpaque(true);
		mf_title.setMaximumSize(new Dimension(150, 30));
		mf_title.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel mf = new JPanel(new GridLayout(1, 4, 10, 10));
		mf.setMaximumSize(new Dimension(800, 150));
		mf.setOpaque(false);
		mf.setBackground(new Color(0,0,0,0));
		mf.setAlignmentX(CENTER_ALIGNMENT);
		
		//Strikers
		JLabel fw_title = new JLabel("Forwards");
		fw_title.setFont(textFont);
		fw_title.setAlignmentX(CENTER_ALIGNMENT);
		fw_title.setBackground(Color.green);
		fw_title.setOpaque(true);
		fw_title.setMaximumSize(new Dimension(150, 30));
		fw_title.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel fw = new JPanel(new GridLayout(1, 4, 10, 10));
		fw.setMaximumSize(new Dimension(800, 150));
		fw.setOpaque(false);
		fw.setBackground(new Color(0,0,0,0));
		fw.setAlignmentX(CENTER_ALIGNMENT);
		
		
		this.add(gk);
		this.add(gk_title);
		this.add(df);
		this.add(df_title);
		this.add(mf);
		this.add(mf_title);
		this.add(fw);
		this.add(fw_title);
		

		List<MakePlayer> startingList = new ArrayList<MakePlayer>();
		
		for (int i = 0; i < 11; i++) {
			try {
				startingList.add(new MakePlayer(textFont, new Player(arr[i])));
			} catch (FileNotFoundException | ResultsReadError | UserNotFound e) {
				e.printStackTrace();
			}
		}
		
		//Add array into database
//		for (int i = 0; i < 11; i++) {
//			try {
//				startingList.add(new MakePlayer(textFont, user_obj.getSUB(i)));
//			} catch (FileNotFoundException | ResultsReadError | UserNotFound e) {
//				e.printStackTrace();
//			}
//		}
		
		
		for (int i = 0; i < 11; i++) {
			startingList.get(i).addMouseListener(gl);
			startingList.get(i).setName(Integer.toString(startingList.get(i).getID()));
		}
		
		
		gk.add(startingList.get(0));

		df.add(startingList.get(1));
		df.add(startingList.get(2));
		df.add(startingList.get(3));
		df.add(startingList.get(4));
	
		mf.add(startingList.get(5));
		mf.add(startingList.get(6));
		mf.add(startingList.get(7));
		mf.add(startingList.get(8));
		
		fw.add(startingList.get(9));
		fw.add(startingList.get(10));
		
		
	
		
	}
	
	
	/** 
	 * This method paints the background of the panel with a resource image.
	 */
	@Override
	  protected void paintComponent(Graphics g) {
		
		try {
		    bg = ImageIO.read(getClass().getResource("pitch.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	    super.paintComponent(g);
	    g.drawImage(bg, 0, 0, null);
	}
	
	
}
