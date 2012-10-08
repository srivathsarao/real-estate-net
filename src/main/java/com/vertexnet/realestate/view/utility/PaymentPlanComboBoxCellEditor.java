package com.vertexnet.realestate.view.utility;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;

import com.vertexnet.realestatenet.model.bean.Layout;
import com.vertexnet.realestatenet.model.bean.PaymentPlan;

public class PaymentPlanComboBoxCellEditor extends DefaultCellEditor {
	private JComboBox comboBox;

	public PaymentPlanComboBoxCellEditor(JComboBox comboBox) {
		super(comboBox);
		this.comboBox = comboBox;
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		Layout layout = (Layout) table.getValueAt(row, 1);
		if (layout != null) {
			DefaultComboBoxModel model = (DefaultComboBoxModel) comboBox.getModel();
			model.removeAllElements();
			for (PaymentPlan plan : layout.getPaymentPlans()) {
				model.addElement(plan);
			}
			model.setSelectedItem(value);
		}
		return comboBox;
	}
}
