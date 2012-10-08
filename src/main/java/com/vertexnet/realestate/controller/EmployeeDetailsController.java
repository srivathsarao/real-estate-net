package com.vertexnet.realestate.controller;

import static com.vertexnet.realestate.constants.ModelConstants.EMPLOYEE_MODEL;
import static com.vertexnet.realestate.constants.UIConstants.EMPLOYEE;

import java.awt.AWTEvent;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.vertexnet.realestate.interfaces.Controller;
import com.vertexnet.realestate.model.EmployeeDetailsModel;
import com.vertexnet.realestate.view.dialog.EmployeeDetailsDialog;
import com.vertexnet.realestatenet.model.bean.Employee;

public class EmployeeDetailsController implements Controller, PropertyChangeListener {
	EmployeeDetailsModel model;
	EmployeeDetailsDialog view;

	public EmployeeDetailsController() {
		model = new EmployeeDetailsModel(this);
		view = new EmployeeDetailsDialog(this);
		view.setModal(true);
		view.setVisible(true);
	}
	
	public EmployeeDetailsController(Employee employee) {
		model = new EmployeeDetailsModel(this);
		view = new EmployeeDetailsDialog(this, employee);
		view.setModal(true);
		view.setVisible(true);
	}

	public void propertyChange(PropertyChangeEvent evt) {
		String name = evt.getPropertyName();
		if(name.equals(EMPLOYEE_MODEL.getReload())) {
			if(view == null) {
				return;
			}
			view.showSaveConfirmation();
			view.dispose();
			view = null;
		}
	}

	public void handleEvent(AWTEvent evt) {
		String name = ((Component)evt.getSource()).getName();
		if(name.equals(EMPLOYEE.getSave())) {
			Employee employee = view.getEmployee();
			model.saveEmployee(employee);
		}
	}
}
