package com.vertexnet.realestate.view.utility;

import javax.swing.JTable;

public class DefaultTable extends JTable {

	private static final long serialVersionUID = 1L;

	public DefaultTable() {
		putClientProperty("terminateEditOnFocusLost", Boolean.TRUE); 
	}

}
