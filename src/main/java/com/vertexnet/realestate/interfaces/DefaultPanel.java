package com.vertexnet.realestate.interfaces;

import javax.swing.JButton;
import com.vertexnet.realestate.view.utility.DefaultTable;

public interface DefaultPanel {

	JButton getAddButton();

	JButton getEditButton();

	JButton getDeleteButton();
	
	JButton getReportButton();
	
	JButton getMakePaymentButton();

	DefaultTable getTable();
}
