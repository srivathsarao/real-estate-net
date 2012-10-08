package com.vertexnet.realestate.dao;

import java.util.List;

import com.vertexnet.realestatenet.model.bean.Employee;

public class EmployeeDAO {

	@SuppressWarnings("unchecked")
	public static List<Employee> getEmployees() {
		return (List<Employee>) HibernateUtil.getObjects(Employee.class);
	}

	public static void deleteEmployees(List<Employee> employees) {
		for(Employee employee : employees) {
			HibernateUtil.deleteObject(employee);
		}
	}

	public static void saveEmployee(Employee employee) {
		HibernateUtil.saveObject(employee);
	}

}
