package ui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import listeners.SnaptoGridListener;

public class MakePlayer extends JPanel {
	
//	private String name;
//	private ImageIcon image;
//	private Font textFont;
//	private String position;
	
	public MakePlayer(Font textFont, ImageIcon image, String name, String position) {
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		Font newFont = textFont.deriveFont((float) 12);
		
		//Picture
		JLabel pic = new JLabel("", image, JLabel.CENTER);
		pic.setAlignmentX(CENTER_ALIGNMENT);
		
		//Name and Position
		JLabel name_ = new JLabel(name);
		name_.setFont(newFont);
		name_.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel position_ = new JLabel(position);
		position_.setFont(newFont);
		position_.setAlignmentX(CENTER_ALIGNMENT);
		
		this.add(pic);
		this.add(Box.createRigidArea(new Dimension(0, 3)));
		this.add(name_);
		this.add(Box.createRigidArea(new Dimension(0, 3)));
		this.add(position_);
	}
	

}
