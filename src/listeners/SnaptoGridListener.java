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
import javax.swing.SwingConstants;

import ui.MakePlayer;

public class SnaptoGridListener implements MouseListener, MouseMotionListener {

	private JPanel screens;
	private Font headerFont;
	private JPanel thing;
	private JPanel dest;
	
	public SnaptoGridListener(JPanel thing, JPanel dest, JPanel screens, Font headerFont) {
		this.thing = thing;
		this.dest = dest;
		this.headerFont = headerFont;
		this.screens = screens;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		thing.setVisible(false);
		dest.add(thing);
		dest.add(Box.createRigidArea(new Dimension(0, 20)));
		thing.setMaximumSize(new Dimension (100, 150));
		thing.setVisible(true);
		
		ButtonListener bl = new ButtonListener(screens);
			
		JButton save = new JButton("SAVE");
		save.setFont(headerFont);
		save.setMaximumSize(new Dimension(100, 35));
		save.setAlignmentX(Component.CENTER_ALIGNMENT);
		save.addActionListener(bl);
		save.setActionCommand("save");
					
		dest.add(save, 12);

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
