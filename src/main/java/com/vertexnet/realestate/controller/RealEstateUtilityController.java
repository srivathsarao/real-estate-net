package com.vertexnet.realestate.controller;

import static com.vertexnet.realestate.constants.ModelConstants.ADMIN_MODEL;
import static com.vertexnet.realestate.constants.ModelConstants.AGENT_MODEL;
import static com.vertexnet.realestate.constants.CustomerConstants.*;
import static com.vertexnet.realestate.constants.ModelConstants.EMPLOYEE_MODEL;
import static com.vertexnet.realestate.constants.ModelConstants.LAYOUT_MODEL;
import static com.vertexnet.realestate.constants.ModelConstants.PAYMENT_PLAN_MODEL;
import static com.vertexnet.realestate.constants.UIConstants.ADMIN;
import static com.vertexnet.realestate.constants.UIConstants.AGENT;
import static com.vertexnet.realestate.constants.UIConstants.ALL;
import static com.vertexnet.realestate.constants.UIConstants.CUSTOMER;
import static com.vertexnet.realestate.constants.UIConstants.EMPLOYEE;
import static com.vertexnet.realestate.constants.UIConstants.LAYOUT;
import static com.vertexnet.realestate.constants.UIConstants.PAYMENT_PLAN;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.EventQueue;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.JOptionPane;

import com.vertexnet.realestate.dao.HibernateUtil;
import com.vertexnet.realestate.dao.JDBCUtil;
import com.vertexnet.realestate.interfaces.Controller;
import com.vertexnet.realestate.interfaces.MainView;
import com.vertexnet.realestate.interfaces.Model;
import com.vertexnet.realestate.model.RealEstateUtilityModel;
import com.vertexnet.realestate.view.frame.LoginForm;
import com.vertexnet.realestate.view.frame.RealEstateFrame;
import com.vertexnet.realestatenet.model.bean.Agent;
import com.vertexnet.realestatenet.model.bean.Area;
import com.vertexnet.realestatenet.model.bean.Customer;
import com.vertexnet.realestatenet.model.bean.Employee;
import com.vertexnet.realestatenet.model.bean.Layout;
import com.vertexnet.realestatenet.model.bean.PaymentPlan;
import com.vertexnet.realestatenet.model.bean.SecurityQuestion;

public class RealEstateUtilityController implements Controller, PropertyChangeListener {
	private Model model;
	private MainView view;
	public RealEstateUtilityController() {
		this.model = new RealEstateUtilityModel(this);
		this.view = new RealEstateFrame(this);
		HibernateUtil.callNativeQuery("SET GLOBAL query_cache_size = 0;");
		getModel().getCustomers();
		getModel().getLayouts();
		getModel().getAgents();
		getModel().getPaymentPlans();
		getModel().getEmployees();
		getModel().getAreas();
		getModel().getSecurityQuestions();
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public MainView getView() {
		return view;
	}

	public void setView(MainView view) {
		this.view = view;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							javax.swing.UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
					HibernateUtil.createSessionFactory();
					JDBCUtil.createConnection();
					LoginForm form = new LoginForm();
					form.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Could not Connect to Database");
					System.exit(0);
				}
			}
		});
	}

	public void handleEvent(AWTEvent evt) {
		String name = ((Component) evt.getSource()).getName();
		
		if (name.equals(CUSTOMER.getAdd())) {
			new CustomerDetailsController();
			getView().setCustomers(RealEstateUtilityModel.customers);
		} else if (name.equals(CUSTOMER.getEdit())) {
			List<Customer> selectedCustomers = getView().getSelectedCustomers();
			if (selectedCustomers == null || selectedCustomers.isEmpty() || selectedCustomers.size() > 1) {
				getView().showSelectObjectEditDialog("Customer");
				return;
			}
			Customer customer = getView().getSelectedCustomers().get(0);
			new CustomerDetailsController(customer);
			getView().updateCustomers();
		} else if (name.equals(CUSTOMER.getDelete())) {
			List<Customer> selectedCustomers = getView().getSelectedCustomers();
			if (selectedCustomers == null || selectedCustomers.isEmpty()) {
				getView().showSelectObjectDeleteDialog("Customer");
				return;
			}
			getModel().deleteCustomers(selectedCustomers);
		} else if (name.equals(CUSTOMER.getReport())) {
			List<Customer> selectedCustomers = getView().getSelectedCustomers();
			if (selectedCustomers == null || selectedCustomers.isEmpty()) {
				getView().showSelectObjectReportDialog("Customer");
				return;
			}
			getModel().generateCustomersReport(selectedCustomers);
		} else if(name.equals(CUSTOMER.getMakePayment())) {
			List<Customer> selectedCustomers = getView().getSelectedCustomers();
			if (selectedCustomers == null || selectedCustomers.isEmpty() || selectedCustomers.size() > 1) {
				getView().showSelectObjectEditDialog("Customer");
				return;
			}
			Customer customer = getView().getSelectedCustomers().get(0);
			new SitePaymentController(customer);
			getView().updateCustomers();
		}
		
		else if (name.equals(LAYOUT.getAdd())) {
			new LayoutDetailsController();
			getView().setLayouts(RealEstateUtilityModel.layouts);
		} else if (name.equals(LAYOUT.getEdit())) {
			List<Layout> selectedLayouts = getView().getSelectedLayouts();
			if (selectedLayouts == null || selectedLayouts.isEmpty() || selectedLayouts.size() > 1) {
				getView().showSelectObjectEditDialog("Layout");
				return;
			}
			Layout layout = selectedLayouts.get(0);
			new LayoutDetailsController(layout);
			getView().updateLayouts();
		} else if (name.equals(LAYOUT.getDelete())) {
			List<Layout> selectedLayouts = getView().getSelectedLayouts();
			if (selectedLayouts == null || selectedLayouts.isEmpty()) {
				getView().showSelectObjectDeleteDialog("Layout");
				return;
			}
			getModel().deleteLayouts(selectedLayouts);
		} else if (name.equals(LAYOUT.getReport())) {
			List<Layout> selectedLayouts = getView().getSelectedLayouts();
			if (selectedLayouts == null || selectedLayouts.isEmpty()) {
				getView().showSelectObjectReportDialog("Layout");
				return;
			}
			getModel().generateLayoutsReport(selectedLayouts);
		}
		
		else if (name.equals(AGENT.getAdd())) {
			new AgentDetailsController();
			getView().setAgents(RealEstateUtilityModel.agents);
		} else if (name.equals(AGENT.getEdit())) {
			List<Agent> selectedAgents = getView().getSelectedAgents();
			if (selectedAgents == null || selectedAgents.isEmpty() || selectedAgents.size() > 1) {
				getView().showSelectObjectEditDialog("Agent");
				return;
			}
			Agent agent = selectedAgents.get(0);
			new AgentDetailsController(agent);
			getView().updateAgents();
		} else if (name.equals(AGENT.getDelete())) {
			List<Agent> selectedAgents = getView().getSelectedAgents();
			if (selectedAgents == null || selectedAgents.isEmpty()) {
				getView().showSelectObjectDeleteDialog("Agent");
				return;
			}
			getModel().deleteAgents(selectedAgents);
		} else if (name.equals(AGENT.getReport())) {
			List<Agent> selectedAgents = getView().getSelectedAgents();
			if (selectedAgents == null || selectedAgents.isEmpty()) {
				getView().showSelectObjectReportDialog("Agent");
				return;
			}
			getModel().generateAgentsReport(selectedAgents);
		}
		
		else if (name.equals(PAYMENT_PLAN.getAdd())) {
			new PaymentPlanDetailsController();
			getView().setPaymentPlans(RealEstateUtilityModel.paymentPlans);
		} else if (name.equals(PAYMENT_PLAN.getEdit())) {
			List<PaymentPlan> selectedPaymentPlans = getView().getSelectedPaymentPlans();
			if (selectedPaymentPlans == null || selectedPaymentPlans.isEmpty() || selectedPaymentPlans.size() > 1) {
				getView().showSelectObjectEditDialog("PaymentPlan");
				return;
			}
			PaymentPlan paymentPlan = getView().getSelectedPaymentPlans().get(0);
			new PaymentPlanDetailsController(paymentPlan);
			getView().updatePaymentPlans();
		} else if (name.equals(PAYMENT_PLAN.getDelete())) {
			List<PaymentPlan> selectedPaymentPlans = getView().getSelectedPaymentPlans();
			if (selectedPaymentPlans == null || selectedPaymentPlans.isEmpty()) {
				getView().showSelectObjectDeleteDialog("PaymentPlan");
				return;
			}
			getModel().deletePaymentPlans(selectedPaymentPlans);
		} else if (name.equals(PAYMENT_PLAN.getReport())) {
			List<PaymentPlan> selectedPaymentPlans = getView().getSelectedPaymentPlans();
			if (selectedPaymentPlans == null || selectedPaymentPlans.isEmpty()) {
				getView().showSelectObjectReportDialog("Employee");
				return;
			}
			getModel().generatePaymentPlansReport(selectedPaymentPlans);
		}
		
		else if (name.equals(EMPLOYEE.getAdd())) {
			new EmployeeDetailsController();
			getView().setEmployees(RealEstateUtilityModel.employees);
		} else if (name.equals(EMPLOYEE.getEdit())) {
			List<Employee> selectedEmployees = getView().getSelectedEmployees();
			if (selectedEmployees == null || selectedEmployees.isEmpty() || selectedEmployees.size() > 1) {
				getView().showSelectObjectEditDialog("Employee");
				return;
			}
			Employee employee = getView().getSelectedEmployees().get(0);
			new EmployeeDetailsController(employee);
			getView().updateEmployees();
		} else if (name.equals(EMPLOYEE.getDelete())) {
			List<Employee> selectedEmployees = getView().getSelectedEmployees();
			if (selectedEmployees == null || selectedEmployees.isEmpty()) {
				getView().showSelectObjectDeleteDialog("Employee");
				return;
			}
			getModel().deleteEmployees(selectedEmployees);
		} else if (name.equals(EMPLOYEE.getReport())) {
			List<Employee> selectedEmployees = getView().getSelectedEmployees();
			if (selectedEmployees == null || selectedEmployees.isEmpty()) {
				getView().showSelectObjectReportDialog("Employee");
				return;
			}
			getModel().generateEmployeesReport(selectedEmployees);
		}
		
		else if (name.equals(ADMIN.getSave())) {
			List<Area> areasToAdd = getView().getAreasToAdd();
			List<Area> areasToDelete = getView().getAreasToDelete();
			List<SecurityQuestion> sqToAdd = getView().getSecurityQuestionsToAdd();
			List<SecurityQuestion> sqToDelete = getView().getSecurityQuestionsToDelete();
			getModel().saveAreas(areasToAdd, areasToDelete);
			getModel().saveSecurityQuesions(sqToAdd, sqToDelete);
		} else if (name.equals(ADMIN.getReport())) {
			getModel().getAreas();
			getModel().getSecurityQuestions();
		}
		
		else if(name.equals(ALL.getReport())) {
			RealEstateFrame frame = (RealEstateFrame) getView();
			getModel().getCompleteReport(frame.getReportBean());
		}
	}

	@SuppressWarnings("unchecked")
	public void propertyChange(PropertyChangeEvent evt) {
		String name = evt.getPropertyName();
		
		if (name.equals(CUSTOMER_TABLE)) {
			getView().setCustomers((List<Customer>) evt.getNewValue());
		} else if (name.equals(CUSTOMER_RELOAD)) {
			getView().updateCustomers();
		} 
		
		else if (name.equals(LAYOUT_MODEL.getTable())) {
			getView().setLayouts((List<Layout>) evt.getNewValue());
		} else if(name.equals(LAYOUT_MODEL.getReload())) {
			getView().updateLayouts();
		} 
		
		else if(name.equals(PAYMENT_PLAN_MODEL.getTable())) {
			getView().setPaymentPlans((List<PaymentPlan>) evt.getNewValue());
		} else if(name.equals(PAYMENT_PLAN_MODEL.getReload())) {
			getView().updatePaymentPlans();
		}
		
		else if(name.equals(AGENT_MODEL.getTable())) {
			getView().setAgents((List<Agent>) evt.getNewValue());
		} else if(name.equals(AGENT_MODEL.getReload())) {
			getView().updateAgents();
		}
		
		else if(name.equals(EMPLOYEE_MODEL.getTable())) {
			getView().setEmployees((List<Employee>) evt.getNewValue());
		} else if(name.equals(EMPLOYEE_MODEL.getReload())) {
			getView().updateEmployees();
		}
		
		else if(name.equals(ADMIN_MODEL.getTable())) {
			getView().setAreas((List<Area>) evt.getNewValue());
		} else if(name.equals(ADMIN_MODEL.getReload())) {
			getView().setSecurityQuestions((List<SecurityQuestion>) evt.getNewValue());
		}
	}
}
