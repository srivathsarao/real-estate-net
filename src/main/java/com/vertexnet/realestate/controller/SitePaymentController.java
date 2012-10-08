package com.vertexnet.realestate.controller;

import static com.vertexnet.realestate.constants.UIConstants.CUSTOMER;
import static com.vertexnet.realestate.constants.CustomerConstants.*;

import java.awt.AWTEvent;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.JOptionPane;

import com.vertexnet.realestate.interfaces.Controller;
import com.vertexnet.realestate.model.RealEstateUtilityModel;
import com.vertexnet.realestate.model.SitePaymentModel;
import com.vertexnet.realestate.view.dialog.SitePaymentDialog;
import com.vertexnet.realestatenet.model.bean.Customer;
import com.vertexnet.realestatenet.model.bean.Site;

public class SitePaymentController implements Controller, PropertyChangeListener {
	
	private SitePaymentDialog view;
	private SitePaymentModel model;

	public SitePaymentController(Customer customer) {
		if(!checkData(customer)) {
			return;
		}
		model = new SitePaymentModel(this);
		view = new SitePaymentDialog(this, customer);
		view.setModal(true);
		view.pack();
		view.setVisible(true);
	}

	public void propertyChange(PropertyChangeEvent evt) {
		String name = evt.getPropertyName();
		Object newValue = evt.getNewValue();
		if (name.equals(CUSTOMER_RELOAD)) {
			view.showSaveConfirmation();
			view.dispose();
		} else if (name.equals(PAYMENT_ISSUE)) {
			view.showPaymentIssueDialog((String)newValue);
		}
	}

	public void handleEvent(AWTEvent evt) {
		String name = ((Component)evt.getSource()).getName();
		if(name.equals(CUSTOMER.getSave())) {
			model.saveCustomer(view.getCustomer(), view.getPaymentUI());
		}
	}

	boolean checkData(Customer customer) {
		StringBuffer data = new StringBuffer();
		if(customer.getSites() == null || customer.getSites().isEmpty()) {
			data.append("No Sites sold to selected Customer\n");
		}
		if(RealEstateUtilityModel.agents.size() < 1) {
			data.append("Please add an Agent");
		}
		if(!data.toString().isEmpty()) {
			JOptionPane.showMessageDialog(null, data.toString());
			return false;
		}
		return true;
	}
}
