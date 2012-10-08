package com.vertexnet.realestate.model;

import static com.vertexnet.realestate.constants.ModelConstants.EMPLOYEE_MODEL;

import java.beans.PropertyChangeListener;

import com.vertexnet.realestate.dao.EmployeeDAO;
import com.vertexnet.realestate.interfaces.Controller;
import com.vertexnet.realestatenet.model.bean.Employee;

public class EmployeeDetailsModel extends RealEstateUtilityModel {

	private static final long serialVersionUID = 1L;

	public EmployeeDetailsModel(Controller controller) {
		super(controller);
		this.addPropertyChangeListener((PropertyChangeListener)controller);
	}

	public void saveEmployee(Employee employee) {
		EmployeeDAO.saveEmployee(employee);
		firePropertyChange(EMPLOYEE_MODEL.getReload(), null, null);
		getEmployees();
	}
}
