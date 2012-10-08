package com.vertexnet.realestate.interfaces;

import java.util.List;

import com.vertexnet.realestatenet.model.bean.Agent;
import com.vertexnet.realestatenet.model.bean.Area;
import com.vertexnet.realestatenet.model.bean.Customer;
import com.vertexnet.realestatenet.model.bean.Employee;
import com.vertexnet.realestatenet.model.bean.Layout;
import com.vertexnet.realestatenet.model.bean.PaymentPlan;
import com.vertexnet.realestatenet.model.bean.SecurityQuestion;

public interface MainView {
	void setController(Controller controller);
	
	List<Customer> getSelectedCustomers();
	List<Layout> getSelectedLayouts();
	List<PaymentPlan> getSelectedPaymentPlans();
	List<Agent> getSelectedAgents();
	List<Employee> getSelectedEmployees();
	
	void setSelectedCustomers(List<Customer> customers);
	void setSelectedLayouts(List<Layout> layouts);
	void setSelectedPaymentPlans(List<PaymentPlan> paymentPlans);
	void setSelectedAgents(List<Agent> agents);
	void setSelectedEmployees(List<Employee> employees);
	
	void setCustomers(List<Customer> newValue);
	void setLayouts(List<Layout> newValue);
	void setPaymentPlans(List<PaymentPlan> newValue);
	void setAgents(List<Agent> newValue);
	void setEmployees(List<Employee> newValue);
	
	void updateCustomers();
	void updateLayouts();
	void updatePaymentPlans();
	void updateAgents();
	void updateEmployees();

	void showSelectObjectEditDialog(String string);
	void showSelectObjectDeleteDialog(String string);
	void showSelectObjectReportDialog(String string);

	List<Area> getAreasToAdd();
	List<Area> getAreasToDelete();
	List<SecurityQuestion> getSecurityQuestionsToAdd();
	List<SecurityQuestion> getSecurityQuestionsToDelete();

	void setAreas(List<Area> newValue);
	void setSecurityQuestions(List<SecurityQuestion> newValue);
}
