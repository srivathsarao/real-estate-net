package com.vertexnet.realestate.model.tablemodel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vertexnet.realestate.view.utility.DefaultTable;
import javax.swing.table.AbstractTableModel;

import com.vertexnet.realestate.bo.TotalPaymentUtility;
import com.vertexnet.realestate.interfaces.TableModel;
import com.vertexnet.realestatenet.model.bean.Customer;

public class CustomersTableModel extends AbstractTableModel implements TableModel {

	private static final long serialVersionUID = 1L;
	private List<Customer> customers;
	private String[] columnNames = new String[] {"Membership Id", "First Name", "Middle Name", "Last Name", "Email", "Phone", "Mobile", "Area", "Payment Pending", "Payment Due Date"};
	private Map<Customer, TotalPaymentUtility> totalMap = new HashMap<Customer, TotalPaymentUtility>();
	private SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
	private DefaultTable table;

	public void setTable(DefaultTable table) {
		this.table = table;
	}

	public DefaultTable getTable() {
		return table;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		if(customers == null) {
			return 0;
		}
		return customers.size();
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if(columnIndex == 8) {
			return Boolean.class;
		}
		return super.getColumnClass(columnIndex);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Customer customer = customers.get(rowIndex);
		TotalPaymentUtility totalPaymentUtility;
		if (totalMap.containsKey(customer)) {
			totalPaymentUtility = totalMap.get(customer);
		} else {
			totalPaymentUtility = new TotalPaymentUtility(customer);
			totalMap.put(customer, totalPaymentUtility);
		}
		switch (columnIndex) {
			case 0: return customer.getMembershipId();
			case 1: return customer.getFirstName();
			case 2: return customer.getFatherName();
			case 3: return customer.getLastName();
			case 4: return customer.getEmailId();
			case 5: return customer.getPhone();
			case 6: return customer.getMobile();
			case 7: return customer.getArea();
			case 8: return totalPaymentUtility.isPaymentPending();
			case 9:if (totalPaymentUtility.getLastPaymentDueDate() != null) {
						return format.format(totalPaymentUtility.getLastPaymentDueDate());
					} else {
						return null;
					}
			case 10:return customer;
		}
		return null;
	}
	
	public List<?> getSelectedObjects() {
		List<Customer> customers = new ArrayList<Customer>();
		int[] rows = table.getSelectedRows();
		for (int i = 0; i < rows.length; i++) {
			Customer customer = (Customer) getValueAt(rows[i], 10);
			customers.add(customer);
		}
		return customers;
	}

	public void reloadTotalData(Customer customer) {
		if (totalMap != null && customer != null) {
			TotalPaymentUtility totalPaymentUtility = new TotalPaymentUtility(customer);
			totalMap.put(customer, totalPaymentUtility);
		}
	}
	
	public List<?> getObjects() {
		return customers;
	}

	@SuppressWarnings("unchecked")
	public void setObjects(List<?> objects) {
		this.customers = (List<Customer>) objects;
	}
}
