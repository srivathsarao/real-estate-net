package com.vertexnet.realestate.model;

import static com.vertexnet.realestate.constants.ModelConstants.LAYOUT_MODEL;

import java.beans.PropertyChangeListener;

import com.vertexnet.realestate.dao.LayoutDAO;
import com.vertexnet.realestate.interfaces.Controller;
import com.vertexnet.realestatenet.model.bean.Layout;

public class LayoutDetailsModel extends RealEstateUtilityModel {

	private static final long serialVersionUID = 1L;

	public LayoutDetailsModel(Controller controller) {
		super(controller);
		this.addPropertyChangeListener((PropertyChangeListener)controller);
	}
	
	public void saveLayout(Layout layoutObject) {
		LayoutDAO.saveLayout(layoutObject);
		firePropertyChange(LAYOUT_MODEL.getReload(), null, null);
		getLayouts();
	}
}
