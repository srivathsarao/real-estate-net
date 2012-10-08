package com.vertexnet.realestate.model.tablemodel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import com.vertexnet.realestatenet.model.bean.Installment;
import com.vertexnet.realestatenet.model.bean.PaymentPlan;

public class InstallmentsTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private PaymentPlan paymentPlan;
	private String[] columnNames = { "Sel", "Installment Name", "Amount", "Interval", "Repeat" };
	private Map<Installment, Boolean> selectionMap;

	public InstallmentsTableModel() {
		selectionMap = new HashMap<Installment, Boolean>();
	}

	public void setPaymentPlan(PaymentPlan paymentPlan) {
		this.paymentPlan = paymentPlan;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		if (paymentPlan == null) {
			return 0;
		}
		return paymentPlan.getInstallments().size();
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:	return Boolean.class;
		case 1:	return String.class;
		case 2:	return BigDecimal.class;
		case 4:	return Integer.class;
		}
		return super.getColumnClass(columnIndex);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Installment installment = new ArrayList<Installment>(paymentPlan.getInstallments()).get(rowIndex);

		switch (columnIndex) {
		case 0:	if (aValue.equals(true)) {
					selectionMap.put(installment, (Boolean) aValue);
				} else if (selectionMap.containsKey(installment)) {
					selectionMap.remove(installment);
				}
				return;
		case 1:	installment.setInstallmentName(aValue.toString());
				return;
		case 2:	installment.setInstallmentAmount(new BigDecimal(aValue.toString()));
				return;
		case 3:	String[] values = (String[]) aValue;
				if (isNumeric(values[0])) {
					installment.setMonthDiff(new Integer(values[0]));
				}
				if (isNumeric(values[1])) {
					installment.setYearDiff(new Integer(values[1]));
				}
				return;
		case 4:	installment.setNumRepeat((Integer) aValue);
				return;
		}
		super.setValueAt(aValue, rowIndex, columnIndex);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Installment installment = new ArrayList<Installment>(paymentPlan.getInstallments()).get(rowIndex);
		switch (columnIndex) {
		case 0:	return selectionMap.get(installment);
		case 1:	return installment.getInstallmentName();
		case 2:	return installment.getInstallmentAmount();
		case 3:	String[] values = new String[2];
				if (installment.getMonthDiff() == 0) {
					values[0] = "0";
				} else {
					values[0] = new Integer(installment.getMonthDiff()).toString();
				}
				if (installment.getYearDiff() == 0) {
					values[1] = "0";
				} else {
					values[1] = new Integer(installment.getYearDiff()).toString();
				}
				return values;
		case 4:	return installment.getNumRepeat();
		}
		return null;
	}

	public Map<Installment, Boolean> getSelectionMap() {
		return selectionMap;
	}

	public boolean isNumeric(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
