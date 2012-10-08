package com.vertexnet.realestate.model.tablemodel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import com.vertexnet.realestatenet.model.bean.Customer;
import com.vertexnet.realestatenet.model.bean.Installment;
import com.vertexnet.realestatenet.model.bean.Layout;
import com.vertexnet.realestatenet.model.bean.PaymentPlan;
import com.vertexnet.realestatenet.model.bean.Site;

public class SitesTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private String[] columnNames = {"Sel", "Layout","Plot Number", "Payment Plan", "Total Amount", "Payment Start Date"};
	private Map<Site, Boolean> selectionMap;
	private Customer customer; 
	
	public SitesTableModel() {
		selectionMap = new HashMap<Site, Boolean>();
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		if(customer == null) {
			return 0;
		}
		return customer.getSites().size();
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex == 0) {
			return Boolean.class;
		} else if (columnIndex == 2) {
			return Integer.class;
		}
		return super.getColumnClass(columnIndex);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if(columnIndex == 0 || columnIndex == 1 || columnIndex == 2 || columnIndex == 3 || columnIndex == 5) {
			return true;
		}
		return false;
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Site sitePurchased = new ArrayList<Site>(customer.getSites()).get(rowIndex);
		switch (columnIndex) {
		case 0: if(aValue.equals(true)) {
					selectionMap.put(sitePurchased, (Boolean) aValue);
					return;
				} else if(selectionMap.containsKey(sitePurchased)){
					selectionMap.remove(sitePurchased);
					return;
				}
		case 1: sitePurchased.setLayout(((Layout) aValue));
				fireTableCellUpdated(rowIndex, columnIndex);
				return;
		case 2: if(aValue != null && !aValue.toString().isEmpty()) {
					sitePurchased.setPurchaseId(Integer.parseInt(aValue.toString()));
				}
				return;
		case 3: PaymentPlan plan = (PaymentPlan) aValue;
				sitePurchased.setPaymentPlan(plan);
				fireTableCellUpdated(rowIndex, columnIndex);
				return;
		case 5: sitePurchased.setPaymentStartDate((Date) aValue);
				return;
		}

		super.setValueAt(aValue, rowIndex, columnIndex);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Site sitePurchased = new ArrayList<Site>(customer.getSites()).get(rowIndex);
		switch (columnIndex) {
		case 0: return selectionMap.get(sitePurchased);
		case 1: return sitePurchased.getLayout();
		case 2: return sitePurchased.getPurchaseId() != 0 ? sitePurchased.getPurchaseId() : null;
		case 3: return sitePurchased.getPaymentPlan();
		case 4: BigDecimal amount = new BigDecimal(0);
				if(sitePurchased.getPaymentPlan() == null || sitePurchased.getPaymentPlan().getInstallments() ==null) {
					return amount;
				}
				for(Installment installment : sitePurchased.getPaymentPlan().getInstallments()) {
					if(installment.getInstallmentAmount() == null) {
						continue;
					}
					BigDecimal installmentAmout = installment.getInstallmentAmount().multiply(new BigDecimal(installment.getNumRepeat()));
					amount = amount.add(installmentAmout);
				}
				return amount;
		case 5: if(sitePurchased.getPaymentStartDate() == null) {
					sitePurchased.setPaymentStartDate(new Date());
				}
				return sitePurchased.getPaymentStartDate();
		case 6: return sitePurchased;
		}
		return null;
	}

	public  Map<Site, Boolean> getSelectionMap() {
		return selectionMap;
	}
}
