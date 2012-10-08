package com.vertexnet.realestate.model.tablemodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vertexnet.realestate.view.utility.DefaultTable;
import javax.swing.table.AbstractTableModel;

import com.vertexnet.realestate.interfaces.TableModel;
import com.vertexnet.realestatenet.model.bean.Employee;

public class EmployeesTableModel extends AbstractTableModel implements TableModel {

	private static final long serialVersionUID = 1L;
	private String[] columnClass = {"Sel", "Employee Name", "Phone", "Mobile", "Salary", "Application Access", "Admin Access"};
	private Map<Employee, Boolean> selectionMap = new HashMap<Employee, Boolean>();
	private List<Employee> employees;

	@SuppressWarnings("unchecked")
	public void setObjects(List<?> employees) {
		this.employees = (List<Employee>) employees;
	}

	public int getColumnCount() {
		return columnClass.length;
	}

	@Override
	public String getColumnName(int column) {
		return columnClass[column];
	}

	public int getRowCount() {
		if(employees == null) {
			return 0;
		}
		return employees.size();
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
		Employee employee = employees.get(rowIndex);
		if(aValue.equals(true)) {
			selectionMap.put(employee, (Boolean) aValue);
		} else if(selectionMap.containsKey(employee)){
			selectionMap.remove(employee);
		}
		super.setValueAt(aValue, rowIndex, columnIndex);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if(columnIndex == 0 || columnIndex == 5 || columnIndex == 6) {
			return Boolean.class;
		}
		return super.getColumnClass(columnIndex);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Employee employee = employees.get(rowIndex);
		switch (columnIndex) {
		case 0: return selectionMap.get(employee);
		case 1: return employee.getName();
		case 2: return employee.getPhone();
		case 3: return employee.getMobile();
		case 4: return employee.getSalary();
		case 5: return employee.isAppAccess();
		case 6: if(employee.isAppAccess()) {
					return employee.isAdminAccess();
				}
				return false;
		}
		return null;
	}
	

	public List<?> getObjects() {
		return employees;
	}

	public List<?> getSelectedObjects() {
		return new ArrayList<Employee>(selectionMap.keySet());
	}

	public void setTable(DefaultTable table) {
		// TODO Auto-generated method stub
		
	}
}
