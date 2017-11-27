package ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTable;

import javax.swing.table.TableColumn;

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
	
	/**
	 * Takes an object array and returns a formatted table
	 * @param data, object array
	 * @param headers, column names
	 * @param textFont, custom font
	 */
	public LeaderboardTable (Object[][] data, String[] headers, Font textFont) {
		this.data = data;
		this.headers = headers;
		this.textFont = textFont;
	}
	
	/**
	 * This method adds all the necessary components to the JPanel.
	 */
	public void create() {
	
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


