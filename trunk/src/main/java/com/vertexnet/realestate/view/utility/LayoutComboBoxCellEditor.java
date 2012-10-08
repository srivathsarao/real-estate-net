package com.vertexnet.realestate.view.utility;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;

import com.vertexnet.realestate.model.tablemodel.SitesTableModel;
import com.vertexnet.realestatenet.model.bean.Layout;
import com.vertexnet.realestatenet.model.bean.PaymentPlan;

public class LayoutComboBoxCellEditor extends DefaultCellEditor {

	private JComboBox comboBox;
	private JComboBox payments;
	private JTable table;

	public LayoutComboBoxCellEditor(JComboBox jComboBox, JComboBox payments) {
		super(jComboBox);
		this.comboBox = jComboBox;
		this.payments = payments;
	}

	@Override
	public boolean stopCellEditing() {
		SitesTableModel sitesTableModel = (SitesTableModel) this.table.getModel();
		sitesTableModel.fireTableDataChanged();
		return super.stopCellEditing();
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		this.table = table;
		if (value instanceof Layout) {
			Layout layout = (Layout) value; 
			DefaultComboBoxModel model = (DefaultComboBoxModel) payments.getModel();
			model.removeAllElements();
			for(PaymentPlan plan : layout.getPaymentPlans()) {
				model.addElement(plan);
			}
			comboBox.setSelectedIndex(-1);
		}
		return comboBox;
	}
}
