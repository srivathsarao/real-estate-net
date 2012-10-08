package com.vertexnet.realestate.model;

import static com.vertexnet.realestate.constants.ModelConstants.ADMIN_MODEL;
import static com.vertexnet.realestate.constants.ModelConstants.AGENT_MODEL;
import static com.vertexnet.realestate.constants.CustomerConstants.*;
import static com.vertexnet.realestate.constants.ModelConstants.EMPLOYEE_MODEL;
import static com.vertexnet.realestate.constants.ModelConstants.LAYOUT_MODEL;
import static com.vertexnet.realestate.constants.ModelConstants.PAYMENT_PLAN_MODEL;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import com.vertexnet.realestate.dao.AgentDAO;
import com.vertexnet.realestate.dao.AreaDAO;
import com.vertexnet.realestate.dao.CustomerDAO;
import com.vertexnet.realestate.dao.EmployeeDAO;
import com.vertexnet.realestate.dao.LayoutDAO;
import com.vertexnet.realestate.dao.PaymentPlanDAO;
import com.vertexnet.realestate.dao.SecurityQuestionsDAO;
import com.vertexnet.realestate.interfaces.Model;
import com.vertexnet.realestate.reports.ReportBuilder;
import com.vertexnet.realestatenet.model.bean.Agent;
import com.vertexnet.realestatenet.model.bean.Area;
import com.vertexnet.realestatenet.model.bean.Customer;
import com.vertexnet.realestatenet.model.bean.Employee;
import com.vertexnet.realestatenet.model.bean.Layout;
import com.vertexnet.realestatenet.model.bean.PaymentPlan;
import com.vertexnet.realestatenet.model.bean.ReportBean;
import com.vertexnet.realestatenet.model.bean.SecurityQuestion;

public class RealEstateUtilityModel extends PropertyChangeSupport implements Model {

	private static final long serialVersionUID = 1L;
	public static List<Customer> customers;
	public static List<Layout> layouts;
	public static List<Agent> agents;
	public static List<PaymentPlan> paymentPlans;
	public static List<Employee> employees;
	private ReportBuilder reportBuilder;
	public static List<Area> areas;
	public static List<SecurityQuestion> securityQuestions;

	public RealEstateUtilityModel(Object controller) {
		super(controller);
		this.addPropertyChangeListener((PropertyChangeListener)controller);
		reportBuilder = new ReportBuilder();
	}

	public void getCustomers() {
		customers = CustomerDAO.getCustomers();
		firePropertyChange(CUSTOMER_TABLE, null, customers);
	}

	public void getLayouts() {
		layouts = LayoutDAO.getLayouts();
		firePropertyChange(LAYOUT_MODEL.getTable(), null, layouts);
	}
	

	public void getPaymentPlans() {
		paymentPlans = PaymentPlanDAO.getPaymentPlans();
		firePropertyChange(PAYMENT_PLAN_MODEL.getTable(), null, paymentPlans);
	}
	
	public void getAgents() {
		agents = AgentDAO.getAgents();
		firePropertyChange(AGENT_MODEL.getTable(), null, agents);
	}
	

	public void getEmployees() {
		employees = EmployeeDAO.getEmployees();
		firePropertyChange(EMPLOYEE_MODEL.getTable(), null, employees);
	}

	public void deleteCustomers(List<Customer> customers) {
		CustomerDAO.deleteCustomers(customers);
		RealEstateUtilityModel.customers.removeAll(customers);
		firePropertyChange(CUSTOMER_RELOAD, null, null);
	}
	
	public void deleteLayouts(List<Layout> layouts) {
		LayoutDAO.deleteLayouts(layouts);
		getLayouts();
		getPaymentPlans();
		firePropertyChange(LAYOUT_MODEL.getReload(), null, null);
		firePropertyChange(PAYMENT_PLAN_MODEL.getReload(), null, null);
	}

	public void deletePaymentPlans(List<PaymentPlan> paymentPlans) {
		PaymentPlanDAO.deletePaymentPlans(paymentPlans);
		RealEstateUtilityModel.paymentPlans.removeAll(paymentPlans);
		firePropertyChange(PAYMENT_PLAN_MODEL.getReload(), null, null);
	}

	public void deleteAgents(List<Agent> agents) {
		AgentDAO.deleteAgents(agents);
		RealEstateUtilityModel.agents.removeAll(agents);
		firePropertyChange(AGENT_MODEL.getReload(), null, null);
	}

	public void deleteEmployees(List<Employee> employees) {
		EmployeeDAO.deleteEmployees(employees);
		RealEstateUtilityModel.employees.removeAll(employees);
		firePropertyChange(EMPLOYEE_MODEL.getReload(), null, null);
	}

	public void getAreas() {
		areas = AreaDAO.getAreas();
		firePropertyChange(ADMIN_MODEL.getTable(), null, areas);
	}
	
	public void getSecurityQuestions() {
		securityQuestions = SecurityQuestionsDAO.getSecurityQuestions();
		firePropertyChange(ADMIN_MODEL.getReload(), null, securityQuestions);
	}
	
	public void generateCustomersReport(List<Customer> selectedCustomers) {
		reportBuilder.generateCustomersReport(selectedCustomers);
	}

	public void generateLayoutsReport(List<Layout> selectedLayouts) {
		reportBuilder.generateLayoutsReport(selectedLayouts);
	}

	public void generatePaymentPlansReport(List<PaymentPlan> selectedPaymentPlans) {
		reportBuilder.generatePaymentPlansReport(selectedPaymentPlans);
	}

	public void generateAgentsReport(List<Agent> selectedAgents) {
		reportBuilder.generateAgentsReport(selectedAgents);
	}

	public void generateEmployeesReport(List<Employee> selectedEmployees) {
		reportBuilder.generateEmployeesReport(selectedEmployees);
	}

	public void saveAreas(List<Area> areasToAdd, List<Area> areasToDelete) {
		AreaDAO.saveAreas(areasToAdd);
		AreaDAO.deleteAreas(areasToDelete);
		areasToAdd.clear();
		areasToDelete.clear();
		getAreas();
	}

	public void saveSecurityQuesions(List<SecurityQuestion> sqToAdd, List<SecurityQuestion> sqToDelete) {
		SecurityQuestionsDAO.saveSecurityQuestions(sqToAdd);
		SecurityQuestionsDAO.deleteSecurityQuestions(sqToDelete);
		sqToAdd.clear();
		sqToDelete.clear();
		getSecurityQuestions();
	}

	public void getCompleteReport(ReportBean reportBean) {
		reportBuilder.generateCompleteReport(reportBean, customers, agents);
	}
}
