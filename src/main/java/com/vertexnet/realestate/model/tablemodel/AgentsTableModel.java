package com.vertexnet.realestate.model.tablemodel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vertexnet.realestate.view.utility.DefaultTable;
import javax.swing.table.AbstractTableModel;

import com.vertexnet.realestate.bo.AgentPaymentUtility;
import com.vertexnet.realestate.bo.TotalAgentPaymentUtility;
import com.vertexnet.realestate.bo.TotalPaymentUtility;
import com.vertexnet.realestate.interfaces.TableModel;
import com.vertexnet.realestate.model.RealEstateUtilityModel;
import com.vertexnet.realestatenet.model.bean.Agent;
import com.vertexnet.realestatenet.model.bean.Customer;
import com.vertexnet.realestatenet.model.bean.Payment;

public class AgentsTableModel extends AbstractTableModel implements TableModel {

	private static final long serialVersionUID = 1L;
	
	private List<Agent> agents;
	private String[] columnNames = {"Sel", "Agent Name","Phone","Mobile", "Commision", "Total Commision"};
	private Map<Agent, Boolean> selectionMap = new HashMap<Agent, Boolean>();
	private Map<Agent, AgentPaymentUtility> totalMap = new HashMap<Agent, AgentPaymentUtility>();
	List<TotalPaymentUtility> totalPaymentList;

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		if(agents == null) {
			return 0;
		}
		return agents.size();
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Agent agent = agents.get(rowIndex);
		if(aValue.equals(true)) {
			selectionMap.put(agent, (Boolean) aValue);
			return;
		} else if(selectionMap.containsKey(agent)){
			selectionMap.remove(agent);
			return;
		}
		super.setValueAt(aValue, rowIndex, columnIndex);
	}
	
	

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if(columnIndex == 0) {
			return Boolean.class;
		}
		return super.getColumnClass(columnIndex);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if(columnIndex ==0) {
			return true;
		}
		return false;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Agent agent = agents.get(rowIndex);
		AgentPaymentUtility agentPaymentUtility;
		if (totalMap.containsKey(agent)) {
			agentPaymentUtility = totalMap.get(agent);
		} else {
			if (totalPaymentList == null) {
				totalPaymentList = new ArrayList<TotalPaymentUtility>();
				for (Customer customer : RealEstateUtilityModel.customers) {
					totalPaymentList.add(new TotalPaymentUtility(customer));
				}
			}
			agentPaymentUtility = new AgentPaymentUtility(agent, totalPaymentList);
			totalMap.put(agent, agentPaymentUtility);
		}
		switch (columnIndex) {
		case 0: return selectionMap.get(agent);
		case 1: return agent.getName();
		case 2: return agent.getPhone();
		case 3: return agent.getMobile();
		case 4: return agent.getCommisionPercent();
		case 5:	return totalMap.get(agent).getTotalPaymentTillDate();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public void setObjects(List<?> objects) {
		this.agents = (List<Agent>) objects;
	}

	public List<?> getSelectedObjects() {
		return new ArrayList<Agent>(selectionMap.keySet());
	}

	public List<?> getObjects() {
		return agents;
	}

	public void setTable(DefaultTable table) {
		// TODO Auto-generated method stub
		
	}
}
