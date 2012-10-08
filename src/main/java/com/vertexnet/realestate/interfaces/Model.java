package com.vertexnet.realestate.interfaces;

import java.util.List;

import com.vertexnet.realestatenet.model.bean.Agent;
import com.vertexnet.realestatenet.model.bean.Area;
import com.vertexnet.realestatenet.model.bean.Customer;
import com.vertexnet.realestatenet.model.bean.Employee;
import com.vertexnet.realestatenet.model.bean.Layout;
import com.vertexnet.realestatenet.model.bean.PaymentPlan;
import com.vertexnet.realestatenet.model.bean.ReportBean;
import com.vertexnet.realestatenet.model.bean.SecurityQuestion;

public interface Model {

	void getCustomers();
	void getLayouts();
	void getPaymentPlans();
	void getAgents();
	void getEmployees();

	void deleteCustomers(List<Customer> customers);
	void deleteLayouts(List<Layout> layouts);
	void deletePaymentPlans(List<PaymentPlan> paymentPlans);
	void deleteAgents(List<Agent> agents);
	void deleteEmployees(List<Employee> employees);
	
	void generateCustomersReport(List<Customer> selectedCustomers);
	void generateLayoutsReport(List<Layout> selectedLayouts);
	void generatePaymentPlansReport(List<PaymentPlan> selectedPaymentPlans);
	void generateAgentsReport(List<Agent> selectedAgents);
	void generateEmployeesReport(List<Employee> selectedEmployees);
	void saveAreas(List<Area> areasToAdd, List<Area> areasToDelete);
	void saveSecurityQuesions(List<SecurityQuestion> sqToAdd, List<SecurityQuestion> sqToDelete);
	void getAreas();
	void getSecurityQuestions();
	void getCompleteReport(ReportBean reportBean);
}
