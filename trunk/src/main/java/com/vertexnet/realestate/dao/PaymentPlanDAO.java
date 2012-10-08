package com.vertexnet.realestate.dao;

import java.util.List;

import com.vertexnet.realestatenet.model.bean.Layout;
import com.vertexnet.realestatenet.model.bean.PaymentPlan;

public class PaymentPlanDAO {

	public static void savePaymentPlan(PaymentPlan paymentPlan) {
		if (paymentPlan.getPlanId() == null) {
			HibernateUtil.saveObject(paymentPlan);
		} else {
			HibernateUtil.updateObject(paymentPlan);
		}
	}
	
	/**
	 * Saves or Updates the Customer Data
	 * @param customer
	 */
	public static void updateLayout(Layout layout) {
		HibernateUtil.updateObject(layout);
	}

	@SuppressWarnings("unchecked")
	public static List<PaymentPlan> getPaymentPlans() {
		return (List<PaymentPlan>) HibernateUtil.getObjects(PaymentPlan.class);
	}

	public static void deletePaymentPlans(List<PaymentPlan> paymentPlans) {
		for(PaymentPlan paymentPlan : paymentPlans) {
			HibernateUtil.deleteObject(paymentPlan);
		}
	}
}
