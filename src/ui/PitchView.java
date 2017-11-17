package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class PitchView extends JPanel {
	
	private BufferedImage bg = null;
	
	public PitchView(Font sm0l) {
		
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
		gk_title.setFont(sm0l);
		gk_title.setAlignmentX(CENTER_ALIGNMENT);
		gk_title.setOpaque(true);
		gk_title.setBackground(Color.green);
		gk_title.setMaximumSize(new Dimension(150, 40));
		gk_title.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel gk = new JPanel();
		gk.setLayout(new BoxLayout(gk, BoxLayout.X_AXIS));
		gk.setMaximumSize(new Dimension(800, 150));
		gk.setOpaque(false);
		gk.setBackground(new Color(0,0,0,0));
		gk.setAlignmentX(CENTER_ALIGNMENT);
		
		//Defenders
		JLabel df_title = new JLabel("Defenders");
		df_title.setFont(sm0l);
		df_title.setAlignmentX(CENTER_ALIGNMENT);
		df_title.setBackground(Color.green);
		df_title.setOpaque(true);
		df_title.setMaximumSize(new Dimension(150, 40));
		df_title.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel df = new JPanel();
		df.setLayout(new BoxLayout(df, BoxLayout.X_AXIS));
		df.setMaximumSize(new Dimension(800, 150));
		df.setOpaque(false);
		df.setBackground(new Color(0,0,0,0));
		df.setAlignmentX(CENTER_ALIGNMENT);
		
		//Midfielders
		JLabel mf_title = new JLabel("Midfielders");
		mf_title.setFont(sm0l);
		mf_title.setAlignmentX(CENTER_ALIGNMENT);
		mf_title.setBackground(Color.green);
		mf_title.setOpaque(true);
		mf_title.setMaximumSize(new Dimension(150, 40));
		mf_title.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel mf = new JPanel();
		mf.setLayout(new BoxLayout(mf, BoxLayout.X_AXIS));
		mf.setMaximumSize(new Dimension(800, 150));
		mf.setOpaque(false);
		mf.setBackground(new Color(0,0,0,0));
		mf.setAlignmentX(CENTER_ALIGNMENT);
		
		//Strikers
		JLabel fw_title = new JLabel("Forwards");
		fw_title.setFont(sm0l);
		fw_title.setAlignmentX(CENTER_ALIGNMENT);
		fw_title.setBackground(Color.green);
		fw_title.setOpaque(true);
		fw_title.setMaximumSize(new Dimension(150, 40));
		fw_title.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel fw = new JPanel();
		fw.setLayout(new BoxLayout(fw, BoxLayout.X_AXIS));
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
		
		
		
		
		/* ************** EXAMPLE ************** */
		ImageIcon image5 = new ImageIcon(getClass().getResource("test.png"));
		
		MakePlayer one = new MakePlayer(sm0l, image5, "Emmeric Ong","MF");
		MakePlayer two = new MakePlayer(sm0l, image5, "Emmeric Ong","MF");
		MakePlayer three = new MakePlayer(sm0l, image5, "Emmeric Ong","MF");
		MakePlayer four = new MakePlayer(sm0l, image5, "Basil Chan","GK");
		MakePlayer five = new MakePlayer(sm0l, image5, "Emmeric Ong","MF");
		MakePlayer seven = new MakePlayer(sm0l, image5, "Emmeric Ong","MF");
		MakePlayer eight = new MakePlayer(sm0l, image5, "Emmeric Ong","MF");
		MakePlayer nine = new MakePlayer(sm0l, image5, "Basil Chan","GK");
		MakePlayer six = new MakePlayer(sm0l, image5, "Emmeric Ong","MF");
		MakePlayer ten = new MakePlayer(sm0l, image5, "Emmeric Ong","MF");
		MakePlayer eleven = new MakePlayer(sm0l, image5, "Emmeric Ong","MF");
		
		gk.add(Box.createRigidArea(new Dimension(350, 0)));
		gk.add(one);
		
		df.add(Box.createRigidArea(new Dimension(80, 0)));
		df.add(two);
		df.add(Box.createRigidArea(new Dimension(80, 0)));
		df.add(three);
		df.add(Box.createRigidArea(new Dimension(80, 0)));
		df.add(four);
		df.add(Box.createRigidArea(new Dimension(80, 0)));
		df.add(five);
		
		mf.add(Box.createRigidArea(new Dimension(80, 0)));
		mf.add(eight);
		mf.add(Box.createRigidArea(new Dimension(80, 0)));
		mf.add(nine);
		mf.add(Box.createRigidArea(new Dimension(80, 0)));
		mf.add(six);
		mf.add(Box.createRigidArea(new Dimension(80, 0)));
		mf.add(seven);
		
		fw.add(Box.createRigidArea(new Dimension(200, 0)));
		fw.add(ten);
		fw.add(Box.createRigidArea(new Dimension(180, 0)));
		fw.add(eleven);
		
	}
	
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
