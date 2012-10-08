package com.vertexnet.realestate.model.tablemodel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vertexnet.realestate.view.utility.DefaultTable;
import javax.swing.table.AbstractTableModel;

import com.vertexnet.realestate.interfaces.TableModel;
import com.vertexnet.realestatenet.model.bean.Installment;
import com.vertexnet.realestatenet.model.bean.PaymentPlan;

public class PaymentPlansTableModel  extends AbstractTableModel implements TableModel {

	private static final long serialVersionUID = 1L;
	private String[] columnNames = {"Sel", "Plan Name", "Layout", "Total Amount"};
	private List<PaymentPlan> paymentPlans;
	private Map<PaymentPlan, Boolean> selectionMap = new HashMap<PaymentPlan, Boolean>();

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		if(paymentPlans == null) {
			return 0;
		}
		return paymentPlans.size();
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if(columnIndex == 0) {
			return Boolean.class;
		}
		return super.getColumnClass(columnIndex);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if(columnIndex == 0) {
			return true;
		}
		return false;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		PaymentPlan paymentPlan = paymentPlans.get(rowIndex);
		switch (columnIndex) {
		case 0: if(aValue.equals(true)) {
					selectionMap.put(paymentPlan, (Boolean) aValue);
					return;
				} else if(selectionMap.containsKey(paymentPlan)){
					selectionMap.remove(paymentPlan);
					return;
				}
		}
		super.setValueAt(aValue, rowIndex, columnIndex);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		PaymentPlan paymentPlan = paymentPlans.get(rowIndex);
		switch (columnIndex) {
		case 0: return selectionMap.get(paymentPlan);
		case 1: return paymentPlan.getPlanName();
		case 2: return paymentPlan.getLayout();
		case 3: if(paymentPlan.getInstallments() == null) {
					return 0;
				}
				BigDecimal total = new BigDecimal(0);
				for(Installment paymentPlanDetails : paymentPlan.getInstallments()) {
					if(paymentPlanDetails.getInstallmentAmount() == null) {
						continue;
					}
					total = total.add(paymentPlanDetails.getInstallmentAmount());
				}
				return total;
		}
		return null;
	}
	

	@SuppressWarnings("unchecked")
	public void setObjects(List<?> objects) {
		this.paymentPlans = (List<PaymentPlan>) objects;
	}

	public List<?> getSelectedObjects() {
		return new ArrayList<PaymentPlan>(selectionMap.keySet());
	}
	
	public List<?> getObjects() {
		return paymentPlans;
	}

	public void setTable(DefaultTable table) {
		// TODO Auto-generated method stub
		
	}
}
