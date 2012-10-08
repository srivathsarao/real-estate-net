package com.vertexnet.realestate.view.utility;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import com.vertexnet.realestate.model.RealEstateUtilityModel;
import com.vertexnet.realestatenet.model.bean.Agent;

public class AgentsComboBoxesCellEditor extends AbstractCellEditor implements TableCellEditor {

	private static final long serialVersionUID = 1L;
	JComboBox comboBox;

	public AgentsComboBoxesCellEditor() {
		super();
	}

	public Object getCellEditorValue() {
		return comboBox.getSelectedItem();
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		comboBox = new JComboBox();
		for(Agent agent : RealEstateUtilityModel.agents) {
			comboBox.addItem(agent);
		}
		comboBox.setSelectedItem(value);
		return comboBox;
	}

}
