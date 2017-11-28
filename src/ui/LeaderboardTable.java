package ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTable;

import javax.swing.table.TableColumn;

import clientFunctions.Sfunctions;
import clientObjects.LeaderBoard;
import clientObjects.User;

/**
 * This class creates a table of all the users, ranks and their points for the leaderboard.
 * @author charisannelim
 *
 */
public class LeaderboardTable extends JPanel {
	
	private Object[][] data;
	private String[] headers;
	private Font textFont;
	private int me_row;
	private LeaderBoard lb;
	private User user;
	
	/**
	 * Takes an object array and returns a formatted table
	 * @param data, object array
	 * @param headers, column names
	 * @param textFont, custom font
	 */
//	public LeaderboardTable (Object[][] data, String[] headers, Font textFont) {
//		this.data = data;
//		this.headers = headers;
//		this.textFont = textFont;
//	}
	
	public LeaderboardTable (User user, LeaderBoard lb, String[] headers, Font textFont) {
		this.lb = lb;
		this.headers = headers;
		this.textFont = textFont;
		this.user = user;
	}
	
	/**
	 * This method adds all the necessary components to the JPanel.
	 */
	public void create() {

		this.data = new Object[lb.getBoardLen()+3][3];
		data[0][0] = "RANK";
		data[0][1] = "PLAYER";
		data[0][2] = "POINTS";
		
		for (int i = 1; i <= lb.getBoardLen(); i++) {
			data[i][0] = "#" + Integer.toString(i);
			data[i][1] = lb.getUserPointsList().get(i-1).username;
			data[i][2] = Integer.toString(lb.getUserPointsList().get(i-1).points);
		}
		
		
		
		if (Sfunctions.sUserPull(user.getUsername(), user.getPassword()).getWeek() == -1) {
			data[lb.getBoardLen() + 1][0] = "";
			data[lb.getBoardLen() + 1][1] = "THE SEASON HAS ENDED.";
			data[lb.getBoardLen() + 1][2] = "";
			data[lb.getBoardLen() + 2][0] = "";
			data[lb.getBoardLen() + 2][1] = "THANKS FOR PLAYING!";
			data[lb.getBoardLen() + 2][2] = "";
		}
		else {
			data[lb.getBoardLen() + 1][0] = "";
			data[lb.getBoardLen() + 1][1] = "";
			data[lb.getBoardLen() + 1][2] = "";
			data[lb.getBoardLen() + 2][0] = "";
			data[lb.getBoardLen() + 2][1] = "";
			data[lb.getBoardLen() + 2][2] = "";
		}
	
	JTable table = new JTable(data, headers);
	table.setBackground(new Color(104, 0, 0));
	table.setForeground(Color.WHITE);
	table.setFont(textFont);
	table.setShowGrid(false);
	table.setEnabled(false);

	//Depending on where the ME is
	me_row = getRowByValue(table, user.getUsername());
	table.setRowSelectionInterval(me_row, me_row);
	
	

	
	TableColumn column = null;
	for (int i = 0; i < 3; i++) {
	    column = table.getColumnModel().getColumn(i);
	    if (i == 0) {
	    		column.setPreferredWidth(60);
	    }
	    if (i == 1) {
	        column.setPreferredWidth(210); //third column is bigger
	    } else {
	    		column.setPreferredWidth(80);
	    }
	}

	
	this.add(table);
	this.setBackground(new Color(104, 0, 0));
	}
	
	 public static int getRowByValue(JTable model, String user) {
		    for (int i = model.getRowCount() - 1; i >= 0; --i) {
		        for (int j = model.getColumnCount() - 1; j >= 0; --j) {
		            if (model.getValueAt(i, j).equals(user)) {
		            		return i;
		            }
		        }
		    }
			return 0;
		 }

	
}


