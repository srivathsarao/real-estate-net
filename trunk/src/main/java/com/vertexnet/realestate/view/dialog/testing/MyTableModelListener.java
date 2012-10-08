package com.vertexnet.realestate.view.dialog.testing;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import com.vertexnet.realestate.view.utility.DefaultTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.vertexnet.realestate.dao.HibernateUtil;
import com.vertexnet.realestate.model.tablemodel.SitesTableModel;
//import com.vertexnet.realestate.view.utility.ComboBoxCellRenderer;
import com.vertexnet.realestatenet.model.bean.Layout;
import com.vertexnet.realestatenet.model.bean.PaymentPlan;

public class MyTableModelListener implements TableModelListener {

	private DefaultTable table;

	public MyTableModelListener(DefaultTable table) {
		this.table = table;
	}

	public void tableChanged(TableModelEvent e) {
		System.out.println(e.getSource());
		if (e.getType() == TableModelEvent.UPDATE) {
			int row = e.getFirstRow();
			int column = e.getColumn();
			TableModel model = table.getModel();
			if (column == 1) {
				Layout layout = (Layout) model.getValueAt(row, 1);

				JComboBox paymentPlanComboBox = new JComboBox();
				if(layout == null || layout.getPaymentPlans() == null) {
					return;
				}
				for (PaymentPlan paymentPlan : layout.getPaymentPlans()) {
					paymentPlanComboBox.addItem(paymentPlan);
				}
				//table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(paymentPlanComboBox));
				//table.getColumnModel().getColumn(3).setCellRenderer(new ComboBoxCellRenderer(paymentPlanComboBox));
				((SitesTableModel) model).setValueAt(paymentPlanComboBox.getItemAt(0), row, 3);
				table.repaint();
			}
		}
	}
}