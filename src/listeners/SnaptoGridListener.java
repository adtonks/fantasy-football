package listeners;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import ui.MakePlayer;

public class SnaptoGridListener implements MouseListener, MouseMotionListener {

	private JPanel screens;
	private Font headerFont;
	private JPanel dest;
	private int counter = 0;
	
	public SnaptoGridListener(JPanel dest, JPanel screens, Font headerFont) {
		this.dest = dest;
		this.headerFont = headerFont;
		this.screens = screens;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Object source = e.getSource();
		if (source instanceof MakePlayer) {
			MakePlayer player = (MakePlayer) source;
			
			player.setVisible(false);
			dest.add(player);
			dest.add(Box.createRigidArea(new Dimension(0, 20)));
			player.setMaximumSize(new Dimension (100, 150));
			player.setVisible(true);
			counter++;
			System.out.println(counter);
			
			if (counter == 7) {
			ButtonListener bl = new ButtonListener(screens);
				
			JButton save = new JButton("SAVE");
			save.setFont(headerFont);
			save.setMaximumSize(new Dimension(100, 35));
			save.setAlignmentX(Component.CENTER_ALIGNMENT);
			save.addActionListener(bl);
			save.setActionCommand("save");
						
			dest.add(save);
			}
		}
		

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
