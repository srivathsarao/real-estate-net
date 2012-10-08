package com.vertexnet.realestate.controller;

import static com.vertexnet.realestate.constants.CustomerConstants.*;
import static com.vertexnet.realestate.constants.UIConstants.*;

import java.awt.AWTEvent;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.JOptionPane;

import com.vertexnet.realestate.interfaces.Controller;
import com.vertexnet.realestate.model.CustomerDetailsModel;
import com.vertexnet.realestate.model.RealEstateUtilityModel;
import com.vertexnet.realestate.view.dialog.CustomerDialog;
import com.vertexnet.realestatenet.model.bean.Customer;
import com.vertexnet.realestatenet.model.bean.Site;

public class CustomerDetailsController implements Controller, PropertyChangeListener {
	CustomerDetailsModel model;
	CustomerDialog view;

	public CustomerDetailsController() {
		if (!checkData()) {
			return;
		}
		model = new CustomerDetailsModel(this);
		view = new CustomerDialog(this);
		view.setModal(true);
		view.pack();
		view.setVisible(true);
	}

	public CustomerDetailsController(Customer customer) {
		if (!checkData()) {
			return;
		}
		model = new CustomerDetailsModel(this);

		view = new CustomerDialog(this, customer);
		view.setModal(true);
		view.pack();
		view.setVisible(true);
	}

	public void handleEvent(AWTEvent evt) {
		String name = ((Component)evt.getSource()).getName();
		if(name.equals(CUSTOMER_TABLE)) {
			model.saveCustomer(view.getCustomer());
		} else if(name.equals(CUSTOMER_ID_ADD.getValue())) {
			
		}
	}

	@SuppressWarnings("unchecked")
	public void propertyChange(PropertyChangeEvent evt) {
		String name = evt.getPropertyName();
		Object newValue = evt.getNewValue();
		if(name.equals(CUSTOMER_RELOAD)) {
			view.showSaveConfirmation();
			view.dispose();
		} else if(name.equals(CUSTOMER_EXIST)) {
			view.showCustomerExistDialog();
		} else if(name.equals(SITE_EXIST)) {
			view.showCustomerExistDialog((List<Site>) newValue);
		}
	}
	
	private boolean checkData() {
		StringBuffer data = new StringBuffer();
		if (RealEstateUtilityModel.layouts == null || RealEstateUtilityModel.layouts.isEmpty()) {
			data.append("Please add a Layout\n");
		}
		if (RealEstateUtilityModel.paymentPlans == null || RealEstateUtilityModel.paymentPlans.isEmpty()) {
			data.append("Please add a Payment Plan\n");
		}
		if (RealEstateUtilityModel.areas == null || RealEstateUtilityModel.areas.isEmpty()) {
			data.append("Please add an Area\n");
		}
		if (!data.toString().isEmpty()) {
			JOptionPane.showMessageDialog(null, data.toString());
			return false;
		}
		return true;
	}
}
