package com.vertexnet.realestate.model;

import static com.vertexnet.realestate.constants.ModelConstants.*;

import java.beans.PropertyChangeListener;

import com.vertexnet.realestate.dao.AgentDAO;
import com.vertexnet.realestate.interfaces.Controller;
import com.vertexnet.realestatenet.model.bean.Agent;

public class AgentDetailsModel extends RealEstateUtilityModel {

	private static final long serialVersionUID = 1L;

	public AgentDetailsModel(Controller controller) {
		super(controller);
		this.addPropertyChangeListener((PropertyChangeListener)controller);
	}

	public void saveAgent(Agent agent) {
		AgentDAO.saveAgent(agent);
		firePropertyChange(REFRESH_AGENT.getValue(), null, agent);
		getAgents();
	}
}
