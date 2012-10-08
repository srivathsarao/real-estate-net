package com.vertexnet.realestate.controller;

import static com.vertexnet.realestate.constants.ModelConstants.LAYOUT_MODEL;
import static com.vertexnet.realestate.constants.UIConstants.LAYOUT;

import java.awt.AWTEvent;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.vertexnet.realestate.interfaces.Controller;
import com.vertexnet.realestate.model.LayoutDetailsModel;
import com.vertexnet.realestate.view.dialog.LayoutDetailsDialog;
import com.vertexnet.realestatenet.model.bean.Layout;

public class LayoutDetailsController implements Controller, PropertyChangeListener {

	private LayoutDetailsDialog view;
	private LayoutDetailsModel model;

	public LayoutDetailsController(Layout layout) {
		this.model = new LayoutDetailsModel(this);
		this.view = new LayoutDetailsDialog(layout,this);
		view.setModal(true);
		view.setVisible(true);
	}

	public LayoutDetailsController() {
		this.model = new LayoutDetailsModel(this);
		this.view = new LayoutDetailsDialog(this);
		view.setModal(true);
		view.setVisible(true);
	}

	public void handleEvent(AWTEvent evt) {
		String name = ((Component)evt.getSource()).getName();
		if(name.equals(LAYOUT.getSave())) {
			model.saveLayout(view.getLayoutObject());
		}
	}

	public void propertyChange(PropertyChangeEvent evt) {
		String name = evt.getPropertyName();
		if(name.equals(LAYOUT_MODEL.getReload())) {
			if(view == null) {
				return;
			}
			view.showSaveConfirmation();
			view.dispose();
			view = null;
		}
	}
}
