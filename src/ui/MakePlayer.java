package ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import clientObjects.Player;

public class MakePlayer extends JPanel {

	public MakePlayer(Font textFont, Player player) {

	 		ImageIcon image = new ImageIcon(player.getImgPath());			
		
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			this.setMaximumSize(new Dimension(200, 200));
			this.setBackground(new Color(0, 0, 0, 0));
			this.setOpaque(false);
			
			Font newFont = textFont.deriveFont((float) 12);
			
			//Picture
			JLabel pic = new JLabel("", image, JLabel.CENTER);
			pic.setAlignmentX(CENTER_ALIGNMENT);
			
			//Name and Position
			JLabel name_ = new JLabel(player.getName());
			name_.setFont(newFont);
			name_.setAlignmentX(CENTER_ALIGNMENT);
			name_.setOpaque(true);
			name_.setBackground(new Color(104, 0, 0));
			name_.setForeground(Color.white);
			
			JLabel position_ = new JLabel(player.getPrefPosition().name());
			position_.setFont(newFont);
			position_.setAlignmentX(CENTER_ALIGNMENT);
			position_.setOpaque(true);
			position_.setBackground(new Color(104, 0, 0));
			position_.setForeground(Color.white);
			
			this.add(pic);
			this.add(Box.createRigidArea(new Dimension(0, 3)));
			this.add(name_);
			this.add(Box.createRigidArea(new Dimension(0, 3)));
			this.add(position_);
		}
 
}
