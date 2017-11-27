package ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTable;

import javax.swing.table.TableColumn;

import clientObjects.LeaderBoard;

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
	
	public LeaderboardTable (LeaderBoard lb, String[] headers, Font textFont) {
		this.lb = lb;
		this.headers = headers;
		this.textFont = textFont;
	}
	
	/**
	 * This method adds all the necessary components to the JPanel.
	 */
	public void create() {
		
	//Change Leaderboard to Object[][]
//	for (int i = 0; i < lb.getBoardLen(); i++) {
//		for (int j = 0; j < 3; j++) {
//			if (j == 0) {
//				data[i][j] = "#" + Integer.toString(i + 1);
//			}
//			else if (j == 1) {
//				data[i][j] = lb.getUserPointsList().get(i).username;
//			}
//			else if (j == 2) {
//				data[i][j] = Integer.toString(lb.getUserPointsList().get(i).points);
//			}
//		}
//	}
		this.data = new Object[lb.getBoardLen()+1][3];
		data[0][0] = "RANK";
		data[0][1] = "PLAYER";
		data[0][2] = "POINTS";
		
		for (int i = 1; i <= lb.getBoardLen(); i++) {
			data[i][0] = "#" + Integer.toString(i);
			data[i][1] = lb.getUserPointsList().get(i-1).username;
			data[i][2] = Integer.toString(lb.getUserPointsList().get(i-1).points);
		}
	
	JTable table = new JTable(data, headers);
	table.setBackground(new Color(104, 0, 0));
	table.setForeground(Color.WHITE);
	table.setFont(textFont);
	table.setShowGrid(false);
	table.setEnabled(false);

	//Depending on where the ME is
	me_row = getRowByValue(table, "ME");
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


