package com.vertexnet.realestate.reports;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.vertexnet.realestatenet.model.bean.Agent;

public class AgentUtil {

	private List<Agent> agents;

	public AgentUtil(List<Agent> agents) {
		this.agents = agents;
	}

	public List<String> getAgentDetails() {
		List<String> dataList = new ArrayList<String>();
		for(Agent agent : agents) {
			BigDecimal totalCustomerPayment = new BigDecimal(0);
			StringBuffer data = new StringBuffer();
			data.append("<tr>");
			data.append("<td bgcolor=\"#C0C0C0\" colspan=\"2\">Agent Name = ").append(agent.getName()).append(" ").append("</td>");
			data.append("</tr><tr>");
			if(agent.getPayments() == null || agent.getPayments().isEmpty()) {
				data.append("<td>No Commision Yet</td><td />");
			}
			data.append("</tr>");
			data.append("<tr><td>Total Commision</td>").append("<td>Rs. 0</td></tr>");
			dataList.add(data.toString());
		}
		return dataList;
	}

	public BigDecimal getTotalPayment() {
		return new BigDecimal(0);
	}

}
