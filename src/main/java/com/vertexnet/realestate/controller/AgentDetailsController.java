package com.vertexnet.realestate.controller;

import static com.vertexnet.realestate.constants.UIConstants.*;
import static com.vertexnet.realestate.constants.ModelConstants.*;

import java.awt.AWTEvent;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.vertexnet.realestate.interfaces.Controller;
import com.vertexnet.realestate.model.AgentDetailsModel;
import com.vertexnet.realestate.view.dialog.AgentDetailsDialog;
import com.vertexnet.realestatenet.model.bean.Agent;

public class AgentDetailsController implements Controller, PropertyChangeListener {
	
	private AgentDetailsDialog view;
	private AgentDetailsModel model;

	public AgentDetailsController(Agent agent) {
		this.model = new AgentDetailsModel(this);
		this.view = new AgentDetailsDialog(this, agent);
		view.setModal(true);
		view.setVisible(true);
	}
	
	public AgentDetailsController() {
		this.model = new AgentDetailsModel(this);
		this.view = new AgentDetailsDialog(this);
		view.setModal(true);
		view.setVisible(true);
	}

	public void handleEvent(AWTEvent evt) {
		String name = ((Component) evt.getSource()).getName();
		if(name.equals(AGENT.getSave())) {
			model.saveAgent(view.getAgent());
		}
	}

	public void propertyChange(PropertyChangeEvent evt) {
		String name = evt.getPropertyName();
		if(name.equals(REFRESH_AGENT.getValue())) {
			if(view == null) {
				return;
			}
			view.showSaveConfirmation();
			view.dispose();
			view = null;
		} 
	}
}
