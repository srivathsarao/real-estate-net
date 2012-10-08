package com.vertexnet.realestate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.vertexnet.realestate.bo.PaymentUtility.PaymentPlanUI;
import com.vertexnet.realestatenet.model.bean.Customer;
import com.vertexnet.realestatenet.model.bean.Site;

public class CustomerDAO {
	
	@SuppressWarnings("unchecked")
	public static List<Customer> getCustomers() {
		return (List<Customer>) HibernateUtil.getObjects(Customer.class);
	}
	
	/**
	 * Saves or Updates the Customer Data
	 * @param customer
	 */
	public static void saveCustomer(Customer customer) {
		if(customer.getCustomerId() == null) {
			HibernateUtil.saveObject(customer);
			HibernateUtil.refreshObject(customer);
		} else {
			HibernateUtil.mergeObject(customer);
		}
	}
	
	public static void savePayments(List<PaymentPlanUI> utilList) {
		for(PaymentPlanUI paymentPlanUI : utilList) {
			if(paymentPlanUI.getPayment() != null) {
				HibernateUtil.saveObject(paymentPlanUI.getPayment());
			}
		}
	}

	
	/**
	 * Deletes a Customer from the database
	 * @param customer
	 */
	public static void deleteCustomer(Customer customer) {
		HibernateUtil.deleteObject(customer);
	}

	public static void deleteCustomers(List<Customer> customers) {
		for(Customer customer : customers) {
			HibernateUtil.deleteObject(customer);
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Customer> getWithMembershipId(Customer customer) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(customer.getClass());
		criteria.add(Restrictions.eq("membershipId", customer.getMembershipId()));
		if(customer.getCustomerId() != null) {
			criteria.add(Restrictions.ne("customerId", customer.getCustomerId()));
		}
		List<Customer> data = criteria.list();
		return data;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Site> getWithSiteId(Customer customer, Site site) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(site.getClass());
		criteria.add(Restrictions.eq("purchaseId", site.getPurchaseId()));
		if (site.getCustomer() != null && site.getCustomer().getCustomerId() != null) {
			criteria.add(Restrictions.ne("customer", site.getCustomer()));
		}
		List<Site> data = criteria.list();
		return data;
	}
}
