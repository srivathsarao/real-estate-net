package com.vertexnet.realestate.view.utility;

import java.awt.Color;
import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class MonthYearCellEditor extends AbstractCellEditor implements TableCellEditor {

	private static final long serialVersionUID = 1L;
	MonthYearChooserPanel panel;

	public MonthYearCellEditor() {
		panel = new MonthYearChooserPanel();
	}

	public Object getCellEditorValue() {
		String[] values = new String[2];
		values[0] = panel.getMonthTextField().getText();
		values[1] = panel.getYearTextField().getText();
		return values;
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		if(value != null) {
			String[] values = (String[]) value;
			panel.getMonthTextField().setText(values[0]);
			panel.getYearTextField().setText(values[1]);
		}
		panel.getYearLabel().setForeground(Color.WHITE);
		panel.getMonthLabel().setForeground(Color.WHITE);
		panel.setBackground(table.getSelectionBackground());
		
		return panel;
	}
}
