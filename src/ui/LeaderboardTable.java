package ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTable;

import javax.swing.table.TableColumn;


public class LeaderboardTable extends JPanel {
	
	private Object[][] data;
	private String[] headers;
	private Font textFont;
	
	public LeaderboardTable (Object[][] data, String[] headers, Font textFont) {
		this.data = data;
		this.headers = headers;
		this.textFont = textFont;
	}
	
	public void create() {
	
	JTable table = new JTable(data, headers);
	table.setBackground(new Color(104, 0, 0));
	table.setForeground(Color.WHITE);
	table.setFont(textFont);
	table.setShowGrid(false);
//	table.setOpaque(false);
	table.setEnabled(false);
	table.setRowSelectionInterval(3, 3);
	

	
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
	
}
