package com.vertexnet.realestate.view.utility;

import java.awt.Component;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ComboBoxCellRenderer extends JComboBox implements TableCellRenderer {

	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		DefaultComboBoxModel model = (DefaultComboBoxModel) this.getModel();
		model.removeAllElements();
		model.addElement(value);
		model.setSelectedItem(value);
		return this;
	}
}
