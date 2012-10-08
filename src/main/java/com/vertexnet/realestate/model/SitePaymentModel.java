package com.vertexnet.realestate.model;

import static com.vertexnet.realestate.constants.CustomerConstants.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.vertexnet.realestate.bo.PaymentUtility.PaymentPlanUI;
import com.vertexnet.realestate.dao.CustomerDAO;
import com.vertexnet.realestatenet.model.bean.Customer;
import com.vertexnet.realestatenet.model.bean.Payment;
import com.vertexnet.realestatenet.model.bean.Site;

public class SitePaymentModel extends RealEstateUtilityModel {

	private static final long serialVersionUID = 1L;

	public SitePaymentModel(Object controller) {
		super(controller);
	}

	public void saveCustomer(Customer customer, List<PaymentPlanUI> utilList) {
		if (verifyData(customer)) {
			CustomerDAO.saveCustomer(customer);
			firePropertyChange(CUSTOMER_RELOAD, null, null);
			getCustomers();
		}
	}

	private boolean verifyData(Customer customer) {
		StringBuffer message = new StringBuffer();
		for (Site site : customer.getSites()) {
			Set<Payment> paymentToRemove = new HashSet<Payment>();
			for (Payment payment : site.getPayments()) {
				if (payment.getAgent() == null && payment.getPaymentDate() == null) {
					paymentToRemove.add(payment);
				} else {
					if (payment.getPaymentDate() == null) {
						message.append("Payment Date for the Site " + payment.getSite() + " with Payment Plan " + payment.getInstallment()
								+ " can not be null\n");
					}
					if (payment.getAgent() == null) {
						message.append("Agent for the Site " + payment.getSite() + " with Payment Plan " + payment.getInstallment()
								+ " can not be null\n");
					}
				}
			}
			site.getPayments().removeAll(paymentToRemove);
		}
		boolean payments = false;
		for(Site site : customer.getSites()) {
			if(site.getPayments() != null && !site.getPayments().isEmpty()) {
				payments = true;
			}
		}
		
		if(!payments) {
			firePropertyChange(PAYMENT_ISSUE, null, "Please Add atleast one Payment");
			return false;
		}
		if (!message.toString().isEmpty()) {
			firePropertyChange(PAYMENT_ISSUE, null, message.toString());
			return false;
		}
		return true;
	}
}
