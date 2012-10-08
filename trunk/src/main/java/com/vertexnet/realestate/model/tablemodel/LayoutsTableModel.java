package com.vertexnet.realestate.model.tablemodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vertexnet.realestate.view.utility.DefaultTable;
import javax.swing.table.AbstractTableModel;

import com.vertexnet.realestate.interfaces.TableModel;
import com.vertexnet.realestatenet.model.bean.Layout;

public class LayoutsTableModel extends AbstractTableModel implements TableModel  {
	
	private static final long serialVersionUID = 1L;
	private List<Layout> layouts;
	private String[] columnNames = new String[] {"sel", "Name", "Number of Sites", "Total Square Feet"};
	private Map<Layout, Boolean> selectionMap = new HashMap<Layout, Boolean>();
	
	public int getColumnCount() {
		return columnNames.length;
	}

	public void setLayouts(List<Layout> layouts) {
		this.layouts = layouts;
	}

	public int getRowCount() {
		if(layouts == null) {
			return 0;
		}
		return layouts.size();
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
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
		Layout layout = layouts.get(rowIndex);
		switch (columnIndex) {
		case 0: if(aValue.equals(true)) {
					selectionMap.put(layout, (Boolean) aValue);
					return;
				} else if(selectionMap.containsKey(layout)){
					selectionMap.remove(layout);
					return;
				}
		}
		super.setValueAt(aValue, rowIndex, columnIndex);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Layout layout = layouts.get(rowIndex);
		switch (columnIndex) {
			case 0: return selectionMap.get(layout);
			case 1: return layout.getName();
			case 2: return layout.getNumSites();
			case 3: return layout.getTotalSquareFeet();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public void setObjects(List<?> objects) {
		this.layouts = (List<Layout>) objects;
	}

	public List<?> getSelectedObjects() {
		return new ArrayList<Layout>(selectionMap.keySet());
	}

	public List<?> getObjects() {
		return layouts;
	}

	public void setTable(DefaultTable table) {
		// TODO Auto-generated method stub
		
	}
}
