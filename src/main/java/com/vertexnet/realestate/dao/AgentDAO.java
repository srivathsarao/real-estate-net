package com.vertexnet.realestate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.vertexnet.realestatenet.model.bean.Agent;

public class AgentDAO {

	@SuppressWarnings("unchecked")
	public static List<Agent> getAgents() {
		return (List<Agent>) HibernateUtil.getObjects(Agent.class);
	}

	public static Agent getAgent(String agentName) {
		Session session = HibernateUtil.getSession();

		Criteria criteria = session.createCriteria(Agent.class);
		criteria.add(Restrictions.eq("agentName", agentName));

		Agent agent = (Agent) criteria.uniqueResult();

		return agent;
	}

	public static void saveAgent(Agent agent) {
		HibernateUtil.saveObject(agent);
	}

	public static void deleteAgent(String agentName) {

	}

	public static void deleteAgents(List<Agent> agents) {
		for(Agent agent : agents) {
			HibernateUtil.deleteObject(agent);
		}
	}
}
