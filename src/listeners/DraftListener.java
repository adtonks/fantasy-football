package listeners;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import clientObjects.User;
import ui.ChangeLineup;
import ui.FirstDraft;
import ui.MakePlayer;
import ui.PitchView;

/**
 * This Listener observes which players the user clicks on from the pool of available
 * players and moves the player to the Chosen panel.
 * @author charisannelim
 *
 */
public class DraftListener implements  MouseListener, MouseMotionListener {

	private JPanel screens;
	private Font headerFont;
	private JPanel dest;
	private int id, counter = 0;
	private int[] bigarr, arr;
	private FirstDraft draft;
	private User user_obj;
	
	/**
	 * This constructor points the parameters as the current instance.
	 * @param user_obj, takes in the current user's details
	 * @param bigarr, ID array of all players in S League
	 * @param draft, the drafting screen
	 * @param dest, the chosen players panel
	 * @param screens, the main JPanel
	 * @param headerFont, the custom font
	 */
	public DraftListener(User user_obj, int[] bigarr, FirstDraft draft, JPanel dest, JPanel screens, Font headerFont) {
		this.user_obj = user_obj;
		this.draft = draft;
		this.dest = dest;
		this.headerFont = headerFont;
		this.screens = screens;
		this.bigarr = bigarr;
	}
	
	/**
	 * On a click, the method takes that MakePlayer, which is a representation of a PLayer
	 * and removes it from the pool and adds it to the chosen player Panel instead. When the user
	 * chooses 17 players, he or she will be prompted to save their line up and
	 * proceed to the homepage. 
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		
		
		Object sth = e.getSource();
		if (sth instanceof MakePlayer) {
			MakePlayer player = (MakePlayer) e.getSource();
			System.out.println("Player "+ player.getName()); 
			id = Integer.parseInt(player.getName());
		
			//increment team counter
			counter++;
			if (counter > 17) {
				JOptionPane.showMessageDialog(draft, "Too many players selected. Please save and exit.",
					  "Error",JOptionPane.ERROR_MESSAGE);
			}
			System.out.println(counter);
			
			
			player.setVisible(false);
			dest.add(player);
			player.setMaximumSize(new Dimension (150, 100));
			player.setVisible(true);
			player.setEnabled(false);
			arr[counter-1] = id;
			System.out.println(arr[counter-1]);
			
			//stops user from pressing anything
			draft.setEnabled(false);
			JOptionPane.showMessageDialog(draft, "Too many players selected. Please save and exit.",
					  "In Process",JOptionPane.OK_OPTION);
			
			//mark player as taken in array maybe no need
			for (int i = 0; i < 203; i++) {
				if (player.getID() == bigarr[i]) {
					bigarr[i] = 0;
					break;
				}
			}

			//send message that this player has been taken out with my player that has been taken out
			
			//sends draft requests repeatedly until the one i get back returns a
			//draft object that has all the remaining players
			
			//map it to an array with zeroes representing those that are not in the remaining players.
			
			//after which I need to repaint everything with the new array
			draft.removeAll();
			draft.create(user_obj, bigarr);
			draft.revalidate();
			draft.repaint();
			draft.setEnabled(true);
			
				
	
				if (counter == 17) {
					ButtonListener bl = new ButtonListener(screens);
						
					JButton save = new JButton("SAVE");
					save.setFont(headerFont);
					save.setMaximumSize(new Dimension(100, 35));
					save.setAlignmentX(Component.CENTER_ALIGNMENT);
					save.addActionListener(bl);
					save.setActionCommand("save");
					
					dest.add(Box.createRigidArea(new Dimension(0, 20)));
					dest.add(save);
					
					draft.setDrafted(true);
					//returns an array of the 17 Player IDs
					user_obj.insertArr(arr);
					
					//
					
			}
		}
	}		


	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
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


}
