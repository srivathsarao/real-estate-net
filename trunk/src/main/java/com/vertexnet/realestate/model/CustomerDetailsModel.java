package com.vertexnet.realestate.model;

import java.util.List;

import com.vertexnet.realestate.dao.CustomerDAO;
import static com.vertexnet.realestate.constants.CustomerConstants.*;

import com.vertexnet.realestatenet.model.bean.Customer;
import com.vertexnet.realestatenet.model.bean.Site;

public class CustomerDetailsModel extends RealEstateUtilityModel {

	private static final long serialVersionUID = 1L;

	public CustomerDetailsModel(Object controller) {
		super(controller);
	}

	public void saveCustomer(Customer customer) {
		if (verifyData(customer)) {
			CustomerDAO.saveCustomer(customer);
			firePropertyChange(CUSTOMER_RELOAD, null, null);
			getCustomers();
		}
	}
	
	private boolean verifyData(Customer customer) {
		List<Customer> customers = CustomerDAO.getWithMembershipId(customer);
		if(customers.size() > 0) {
			firePropertyChange(CUSTOMER_EXIST, null, null);
			return false;
		}
		for (Site site : customer.getSites()) {
			List<Site> sites = CustomerDAO.getWithSiteId(customer, site);
			
			int siteCount = 0;
			for (Site customerSite : customer.getSites()) {
				if (customerSite.getPurchaseId() == site.getPurchaseId()) {
					siteCount++;
				}
			}
			if (siteCount > 1) {
				sites.add(site);
			}
			
			if (!sites.isEmpty()) {
				firePropertyChange(SITE_EXIST, null, sites);
				return false;
			}
		}
		
		return true;
	}
}
