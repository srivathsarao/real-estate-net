package com.vertexnet.realestate.controller;

import static com.vertexnet.realestate.constants.ModelConstants.PAYMENT_PLAN_MODEL;
import static com.vertexnet.realestate.constants.UIConstants.PAYMENT_PLAN;

import java.awt.AWTEvent;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JOptionPane;

import com.vertexnet.realestate.interfaces.Controller;
import com.vertexnet.realestate.model.PaymentPlanDetailsModel;
import com.vertexnet.realestate.model.RealEstateUtilityModel;
import com.vertexnet.realestate.view.dialog.PaymentPlanDialog;
import com.vertexnet.realestatenet.model.bean.Layout;
import com.vertexnet.realestatenet.model.bean.PaymentPlan;

public class PaymentPlanDetailsController implements Controller, PropertyChangeListener {
	
	private PaymentPlanDetailsModel model;
	private PaymentPlanDialog view;

	public PaymentPlanDetailsController() {
		if(!checkData()) {
			return;
		}
		this.model = new PaymentPlanDetailsModel(this);
		view = new PaymentPlanDialog(this, RealEstateUtilityModel.layouts);
		view.setModal(true);
		view.setVisible(true);
	}
	
	public PaymentPlanDetailsController(PaymentPlan paymentPlan) {
		if(!checkData()) {
			return;
		}
		this.model = new PaymentPlanDetailsModel(this);
		view = new PaymentPlanDialog(this, paymentPlan, RealEstateUtilityModel.layouts);
		view.setModal(true);
		view.setVisible(true);
	}

	public void handleEvent(AWTEvent evt) {
		String name = ((Component)evt.getSource()).getName();
		if(name.equals(PAYMENT_PLAN.getSave())) {
			Layout layout = view.getLayOut();
			model.savePaymentPlan(layout);
		}
	}

	public void propertyChange(PropertyChangeEvent evt) {
		String name = evt.getPropertyName();
		if(name.equals(PAYMENT_PLAN_MODEL.getReload())) {
			view.showSaveConfirmation();
		}
	}
	
	
	private boolean checkData() {
		if (RealEstateUtilityModel.layouts == null || RealEstateUtilityModel.layouts.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please add Layout Before adding Payment Plans\n");
			return false;
		}
		return true;
	}

}
