package com.vertexnet.realestate.view.utility;

import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.vertexnet.realestate.model.RealEstateUtilityModel;
import com.vertexnet.realestatenet.model.bean.Agent;

public class AgentsComboBoxCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;
	
	public AgentsComboBoxCellRenderer() {
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		JComboBox comboBox = new JComboBox();
		for(Agent agent : RealEstateUtilityModel.agents) {
			comboBox.addItem(agent);
		}
		comboBox.setSelectedItem(value);
		return comboBox;
	}
}
