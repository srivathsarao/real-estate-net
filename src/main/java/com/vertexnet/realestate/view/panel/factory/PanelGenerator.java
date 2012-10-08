package com.vertexnet.realestate.view.panel.factory;

import static com.vertexnet.realestate.constants.UIConstants.AGENT;
import static com.vertexnet.realestate.constants.UIConstants.CUSTOMER;
import static com.vertexnet.realestate.constants.UIConstants.EMPLOYEE;
import static com.vertexnet.realestate.constants.UIConstants.LAYOUT;
import static com.vertexnet.realestate.constants.UIConstants.PAYMENT_PLAN;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.vertexnet.realestate.view.utility.DefaultTable;

import com.vertexnet.realestate.interfaces.Controller;
import com.vertexnet.realestate.interfaces.DefaultPanel;
import com.vertexnet.realestate.interfaces.MainView;
import com.vertexnet.realestate.interfaces.TableModel;
import com.vertexnet.realestate.model.tablemodel.AgentsTableModel;
import com.vertexnet.realestate.model.tablemodel.CustomersTableModel;
import com.vertexnet.realestate.model.tablemodel.EmployeesTableModel;
import com.vertexnet.realestate.model.tablemodel.LayoutsTableModel;
import com.vertexnet.realestate.model.tablemodel.PaymentPlansTableModel;
import com.vertexnet.realestate.view.panel.AdminPanel;
import com.vertexnet.realestate.view.panel.DefaultPanelImpl;
import com.vertexnet.realestate.view.panel.ReportsPanel;
import com.vertexnet.realestatenet.model.bean.Agent;
import com.vertexnet.realestatenet.model.bean.Customer;
import com.vertexnet.realestatenet.model.bean.Employee;
import com.vertexnet.realestatenet.model.bean.Layout;
import com.vertexnet.realestatenet.model.bean.PaymentPlan;

public class PanelGenerator {
	public static DefaultPanel createAgentsPanel(MainView view, Controller controller) {
		TableModel tableModel = new AgentsTableModel();
		DefaultPanel panel = new DefaultPanelImpl(tableModel, controller, view);
		panel.getReportButton().setName(AGENT.getReport());
		panel.getAddButton().setName(AGENT.getAdd());
		panel.getEditButton().setName(AGENT.getEdit());
		panel.getDeleteButton().setName(AGENT.getDelete());
		panel.getMakePaymentButton().setVisible(false);
		panel.getReportButton().setVisible(false);
		panel.getTable().setName(AGENT.getEdit());
		panel.getTable().addMouseListener(new Listener(view, controller, tableModel));
		panel.getTable().repaint();
		return panel;
	}
	
	public static DefaultPanel createPaymentPlanPanel(MainView view, Controller controller) {
		TableModel tableModel = new PaymentPlansTableModel();
		DefaultPanel panel = new DefaultPanelImpl(tableModel, controller, view);
		panel.getReportButton().setVisible(false);
		panel.getReportButton().setName(PAYMENT_PLAN.getReport());
		panel.getAddButton().setName(PAYMENT_PLAN.getAdd());
		panel.getEditButton().setName(PAYMENT_PLAN.getEdit());
		panel.getDeleteButton().setName(PAYMENT_PLAN.getDelete());
		panel.getMakePaymentButton().setVisible(false);
		panel.getReportButton().setVisible(false);
		panel.getTable().setName(PAYMENT_PLAN.getEdit());
		panel.getTable().addMouseListener(new Listener(view, controller, tableModel));
		panel.getTable().repaint();
		return panel;
	}

	public static DefaultPanel createCustomersPanel(MainView view, Controller controller) {
		TableModel tableModel = new CustomersTableModel();
		DefaultPanel panel = new DefaultPanelImpl(tableModel, controller, view);
		tableModel.setTable(panel.getTable());
		panel.getReportButton().setName(CUSTOMER.getReport());
		panel.getAddButton().setName(CUSTOMER.getAdd());
		panel.getEditButton().setName(CUSTOMER.getEdit());
		panel.getDeleteButton().setName(CUSTOMER.getDelete());
		panel.getTable().setName(CUSTOMER.getEdit());
		panel.getTable().addMouseListener(new Listener(view, controller, tableModel));
		panel.getTable().repaint();
		return panel;
	}
	
	public static DefaultPanel createLayoutsPanel(MainView view, Controller controller) {
		TableModel tableModel = new LayoutsTableModel();
		DefaultPanel panel = new DefaultPanelImpl(tableModel, controller, view);
		panel.getReportButton().setVisible(false);
		panel.getReportButton().setName(LAYOUT.getReport());
		panel.getAddButton().setName(LAYOUT.getAdd());
		panel.getEditButton().setName(LAYOUT.getEdit());
		panel.getDeleteButton().setName(LAYOUT.getDelete());
		panel.getMakePaymentButton().setVisible(false);
		panel.getReportButton().setVisible(false);
		panel.getTable().setName(LAYOUT.getEdit());
		panel.getTable().addMouseListener(new Listener(view, controller, tableModel));
		panel.getTable().repaint();
		return panel;
	}
	
	public static DefaultPanel createEmployeesPanel(MainView view, Controller controller) {
		TableModel tableModel = new EmployeesTableModel();
		DefaultPanel panel = new DefaultPanelImpl(tableModel, controller, view);
		panel.getReportButton().setName(EMPLOYEE.getReport());
		panel.getAddButton().setName(EMPLOYEE.getAdd());
		panel.getEditButton().setName(EMPLOYEE.getEdit());
		panel.getDeleteButton().setName(EMPLOYEE.getDelete());
		panel.getMakePaymentButton().setVisible(false);
		panel.getReportButton().setVisible(false);
		panel.getTable().setName(EMPLOYEE.getEdit());
		panel.getTable().addMouseListener(new Listener(view, controller, tableModel));
		panel.getTable().repaint();
		return panel;
	}

	public static DefaultPanel createAdminPanel(MainView view, Controller controller) {
		return new AdminPanel(controller);
	}

	public static DefaultPanel createReportsPanel(MainView view, Controller controller) {
		return new ReportsPanel(controller);
	}
}

class Listener extends MouseAdapter {
	private MainView view;
	private Controller controller;
	private TableModel tableModel;

	public Listener(MainView view, Controller controller, TableModel tableModel) {
		super();
		this.view = view;
		this.controller = controller;
		this.tableModel = tableModel;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void mouseClicked(MouseEvent evt) {
		if (evt.getClickCount() == 2) {
			DefaultTable table = ((DefaultTable) evt.getSource());
			List<?> objects = tableModel.getObjects();
			
			List selectedObjects = new ArrayList();
			selectedObjects.add(objects.get(table.getSelectedRow()));
			
			if (table.getName().equals(CUSTOMER.getEdit())) {
				view.setSelectedCustomers((List<Customer>) selectedObjects);
			} else if (table.getName().equals(LAYOUT.getEdit())) {
				view.setSelectedLayouts((List<Layout>) selectedObjects);
			} else if (table.getName().equals(PAYMENT_PLAN.getEdit())) {
				view.setSelectedPaymentPlans((List<PaymentPlan>) selectedObjects);
			} else if (table.getName().equals(AGENT.getEdit())) {
				view.setSelectedAgents((List<Agent>) selectedObjects);
			} else if (table.getName().equals(EMPLOYEE.getEdit())) {
				view.setSelectedEmployees((List<Employee>) selectedObjects);
			}
			controller.handleEvent(evt);
			
			if (table.getName().equals(CUSTOMER.getEdit())) {
				String name = ((Component) evt.getSource()).getName();
				if (name.equals(CUSTOMER.getEdit()) && !selectedObjects.isEmpty()) {
					((CustomersTableModel) tableModel).reloadTotalData((Customer) selectedObjects.get(0));
				}
			}
		}
	}

}
