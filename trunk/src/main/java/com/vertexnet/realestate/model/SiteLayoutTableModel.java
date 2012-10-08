package com.vertexnet.realestate.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.vertexnet.realestatenet.model.bean.Site;

public class SiteLayoutTableModel  extends AbstractTableModel {
	
	public SiteLayoutTableModel(List<Site> sites) {
	}

	private static final long serialVersionUID = 1L;
	private String[] columnNames = new String[] {"sel", "Id", "corner"};

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return 2;
	}

	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
