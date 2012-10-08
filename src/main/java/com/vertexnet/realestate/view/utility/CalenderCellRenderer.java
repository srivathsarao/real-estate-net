package com.vertexnet.realestate.view.utility;

import java.awt.Component;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.toedter.calendar.JDateChooser;

public class CalenderCellRenderer implements TableCellRenderer {
	private JDateChooser dateChooser;
	
	public CalenderCellRenderer() {
		dateChooser = new JDateChooser();
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		dateChooser.setDate((Date) value);
		int height = new Double(dateChooser.getPreferredSize().getHeight()).intValue();
		if (table.getRowHeight(row) < height) {
			table.setRowHeight(row, height);
		}
		return dateChooser;
	}

}
