package com.vertexnet.realestate.view.utility;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

public class MonthYearCellRenderer implements TableCellRenderer {
	MonthYearChooserPanel panel;

	public MonthYearCellRenderer() {
		panel = new MonthYearChooserPanel();
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		if (value != null) {
			String[] values = (String[]) value;
			panel.getMonthTextField().setText(values[0]);
			panel.getYearTextField().setText(values[1]);
		}
		int height = new Double(panel.getPreferredSize().getHeight()).intValue();
		if (table.getRowHeight(row) < height) {
			table.setRowHeight(row, height);
		}
		panel.getYearLabel().setForeground(isSelected ? Color.WHITE : Color.BLACK);
		panel.getMonthLabel().setForeground(isSelected ? Color.WHITE : Color.BLACK);
        
		Color background;
        if (row % 2 == 1) {
        	background = UIManager.getColor("Table.alternateRowColor");
        } else {
        	background = table.getBackground();
        }

		panel.setBackground(isSelected ? table.getSelectionBackground() : background);
		
		return panel;
	}

}
