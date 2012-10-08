package com.vertexnet.realestate.view.utility;

import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Date;

import javax.swing.AbstractCellEditor;
import javax.swing.CellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.toedter.calendar.JDateChooser;

public class CalenderCellEditor extends AbstractCellEditor implements TableCellEditor  {

	private static final long serialVersionUID = 1L;
	private JDateChooser dateChooser;

	public CalenderCellEditor() {
		dateChooser = new JDateChooser();
	}

	public Object getCellEditorValue() {
		return dateChooser.getDate();
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		dateChooser.setDate((Date) value);
		if (table.isEditing()) {
			table.getCellEditor().stopCellEditing();
		}
		return dateChooser;
	}

}
