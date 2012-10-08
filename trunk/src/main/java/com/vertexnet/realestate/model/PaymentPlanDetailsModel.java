package com.vertexnet.realestate.model;

import static com.vertexnet.realestate.constants.ModelConstants.PAYMENT_PLAN_MODEL;

import com.vertexnet.realestate.dao.LayoutDAO;
import com.vertexnet.realestate.dao.PaymentPlanDAO;
import com.vertexnet.realestate.interfaces.Controller;
import com.vertexnet.realestatenet.model.bean.Layout;
import com.vertexnet.realestatenet.model.bean.PaymentPlan;

public class PaymentPlanDetailsModel extends RealEstateUtilityModel {

	private static final long serialVersionUID = 1L;

	public PaymentPlanDetailsModel(Controller controller) {
		super(controller);
	}

	public void savePaymentPlan(Layout layout) {
		PaymentPlanDAO.updateLayout(layout);
		firePropertyChange(PAYMENT_PLAN_MODEL.getReload(), null, null);
		getLayouts();
		getPaymentPlans();
	}
}
