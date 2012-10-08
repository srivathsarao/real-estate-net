package com.vertexnet.realestate.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vertexnet.realestatenet.model.bean.Agent;

public class TotalAgentPaymentUtility {
	private List<Agent> agents;
	private Map<Agent, AgentPaymentUtility> agentPaymentUtilityMap;
	private List<AgentPaymentUtility> agentPaymentUtilities;

	public TotalAgentPaymentUtility(List<Agent> agents, List<TotalPaymentUtility> totalPayments) {
		this.agents = agents;
		agentPaymentUtilityMap = new HashMap<Agent, AgentPaymentUtility>();
		agentPaymentUtilities = new ArrayList<AgentPaymentUtility>();

		for (Agent agent : agents) {
			AgentPaymentUtility agentPaymentUtility = new AgentPaymentUtility(agent, totalPayments);
			agentPaymentUtilities.add(agentPaymentUtility);
			agentPaymentUtilityMap.put(agent, agentPaymentUtility);
		}
	}

	public Map<Agent, AgentPaymentUtility> getAgentPaymentUtilityMap() {
		return agentPaymentUtilityMap;
	}

	public BigDecimal getTotalPaymentFromTill(Date fromDate, Date toDate) {
		BigDecimal total = new BigDecimal(0);
		if (agentPaymentUtilities == null || agentPaymentUtilities.isEmpty()) {
			return total;
		}
		for (AgentPaymentUtility agentPaymentUtility : agentPaymentUtilities) {
			total = total.add(agentPaymentUtility.getTotalPaymentFromTill(fromDate, toDate));
		}
		return total;
	}

	public BigDecimal getTotalPaymentTillDate() {
		BigDecimal total = new BigDecimal(0);
		if (agentPaymentUtilities == null || agentPaymentUtilities.isEmpty()) {
			return total;
		}
		for (AgentPaymentUtility agentPaymentUtility : agentPaymentUtilities) {
			total = total.add(agentPaymentUtility.getTotalPaymentTillDate());
		}
		return total;
	}

	public List<AgentPaymentUtility> getAgentPaymentUtilities() {
		return agentPaymentUtilities;
	}
}
