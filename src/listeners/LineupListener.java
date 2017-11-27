package listeners;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import ui.ChangeLineup;
import ui.MakePlayer;

/**
 * This Listener listens for user clicks on two players they would like to switch
 * and makes the switch accordingly by repainting the panel with a new order of players
 * in an array.
 * @author charisannelim
 *
 */
public class LineupListener implements MouseListener, ActionListener {
	
	private ChangeLineup cl;
	private int clicked = 0;
	private int store1 = 0, store2 = 0;
	private int[] arr;
	
	/**
	 * This constructor points the parameters as the current instance.
	 * @param cl, the current JPanel that needs to be repainted
	 * @param arr, the array of 17 players that is ordered
	 */
	public LineupListener(ChangeLineup cl, int[] arr) {
		this.cl = cl;
		this.arr = arr;
	}
	
	/**
	 * This class observes user's selected players and switches the players.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		Object sth = e.getSource();
		if (sth instanceof MakePlayer) {
			MakePlayer player = (MakePlayer) e.getSource();
			
			//SELECT FIRST PLAYER
			if (clicked == 0 && store1 == 0) {
				player.setBackground(Color.green);
				player.setOpaque(true);
				clicked = 1;
				store1 = Integer.parseInt(player.getName());
				System.out.println("Clicked" + clicked);
				System.out.println("First player " + store1 + " is selected.");
						
			}
			
			//DESELECT FIRST PLAYER
			else if (clicked == 1 && store1 == Integer.parseInt(player.getName()) && store2 == 0) {
				player.setBackground(new Color(0, 0, 0, 0));
				player.setOpaque(false);
				clicked = 0;
				System.out.println("First player " + store1 + " deselected.");
				store1 = 0;
				System.out.println("Clicked" + clicked);
				
			}
			
			
			//SELECT SECOND PLAYER
			else if (clicked == 1 && store1 != 0) {
				player.setBackground(Color.green);
				player.setOpaque(true);
				store2 = Integer.parseInt(player.getName());
				System.out.println("Clicked" + clicked);
				System.out.println("Second player: "+ store2 + " is selected.");
				

				String text = "Player" + store1 + " switch with Player" + store2 + ".Confirm?";
				JOptionPane.showConfirmDialog(cl, text,
							 "Confirm",JOptionPane.OK_CANCEL_OPTION);
					 
				System.out.println(store1);
				System.out.println(store2);
				for (int x = 0; x < 17; x++) {
					 System.out.println(arr[x]);
				}
					 
				//switch positions in array
				for (int i = 0; i < 17; i++) {
					if (arr[i] == store1) {
					 int temp = store1;
					 for (int j = 0; j < 17; j++) {
						 if (arr[j] == store2) {
							 arr[i] = store2;
							 arr[j] = temp; 
							 break;
						 }
					 }
					 break;
				 }
			 }
					 
				for (int x = 0; x < 17; x++) {
				 System.out.println(arr[x]);
				}
					 
				cl.removeAll();
				cl.create(arr);
				cl.revalidate();
			 	cl.repaint();
				
			}
			

		}
		
		

	 }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for (int x = 0; x < 17; x++) {
			 System.out.print(arr[x] + "\t");
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

}
