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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import clientFunctions.Sfunctions;
import clientObjects.User;
import exceptions.IndexDoesNotExist;
import ui.ChangeLineup;
import ui.FirstDraft;
import ui.HomePage;
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
	private Font headerFont, textFont;
	private JPanel dest;
	private int id, counter = 0;
	private int[] arr;
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
	public DraftListener(User user_obj, int[] arr, FirstDraft draft, JPanel dest, JPanel screens, Font headerFont, Font textFont) {
		this.user_obj = user_obj;
		this.draft = draft;
		this.dest = dest;
		this.headerFont = headerFont;
		this.textFont = textFont;
		this.screens = screens;
		this.arr = arr;
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
			System.out.println(counter);
			if (counter > 17) {
				JLabel label = new JLabel("Too many players selected. Please save and exit.    ");
				label.setFont(textFont);
				JOptionPane.showMessageDialog(draft, label,
					  "Error",JOptionPane.ERROR_MESSAGE);
			}
			
			else {
			
			//Add player to team
			player.setVisible(false);
			dest.add(player);
			player.setMaximumSize(new Dimension (150, 100));
			player.setVisible(true);
			player.setEnabled(false);
			arr[counter-1] = id;
			System.out.println(arr[counter-1]);
			
				
			//Stop and save players
			if (counter == 17) {
					ButtonListener bl = new ButtonListener(screens);
					
					JLabel label2 = new JLabel("Awesome! You have your team. Please save and exit.    ");
					label2.setFont(textFont);
					JOptionPane.showMessageDialog(draft, label2,
							  "Save",JOptionPane.OK_OPTION);
						
					JButton save = new JButton("SAVE");
					save.setFont(headerFont);
					save.setMaximumSize(new Dimension(100, 35));
					save.setAlignmentX(Component.CENTER_ALIGNMENT);
					save.addActionListener(bl);
					save.setActionCommand("save");
					
					dest.add(Box.createRigidArea(new Dimension(0, 20)));
					dest.add(save);
					
					//returns an array of the 17 Player IDs
					user_obj.setDraftTrue();
					user_obj.insertArr(arr);
					try {
						System.out.println("GK is" + user_obj.getGK(0).getPlayerID());
					} catch (IndexDoesNotExist e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Sfunctions.sUserPush(user_obj);
					

					//refresh homepage, make draft button invalid
					 HomePage homepage = (HomePage) screens.getComponent(2);
					 homepage.removeAll();
					 homepage.create(user_obj);
					 homepage.revalidate();
					 homepage.repaint();
					 
					 //change lineup immediately
					ChangeLineup lineup = (ChangeLineup) screens.getComponent(7);
					lineup.removeAll();
					lineup.create(user_obj, arr);
					lineup.revalidate();
					lineup.repaint();

					
				}
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
