package com.vertexnet.realestate.resources;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.vertexnet.realestate.dao.HibernateUtil;
import com.vertexnet.realestatenet.model.bean.Customer;
import com.vertexnet.realestatenet.model.bean.Installment;
import com.vertexnet.realestatenet.model.bean.Layout;
import com.vertexnet.realestatenet.model.bean.PaymentPlan;
import com.vertexnet.realestatenet.model.bean.Site;

public class axy {
	public static void main(String[] args) throws Exception {
		HibernateUtil.createSessionFactory();
		
		Customer customer = new Customer();
		customer.setMembershipId(100);
		customer.setAddress("Bangalore");
		customer.setEmailId("@gmail.com");
		customer.setFatherName("ftestert");
		customer.setFirstName("ffdsfdsf");
		customer.setLastName("fsfadf");
		customer.setMobile("2222222222");
		customer.setPhone("fffffff");
		
		List<Site> sitePurchasedList = new ArrayList<Site>();
		
		Site sitePurchased = new Site();
		sitePurchased.setCustomer(customer);
		sitePurchasedList.add(sitePurchased);

		List<Layout> layouts = new ArrayList<Layout>();
		Layout layout = new Layout();
		layout.setAddress("Mangalore");
		layout.setLayoutId(100);
		layout.setName("Tippanahalli");
			
//		List<Site> sites = new ArrayList<Site>();
//		Site site = new Site();
//		site.setLayout(layout);
//		site.setId(100);
//		site.setLength(100);
//		site.setWidth(200);
//		sites.add(site);
			
		//layout.setTotalSquareFeet(1000);
		//layout.setSites(sites);
		layouts.add(layout);
		
		//sitePurchased.setSite(layouts.get(0).getSites().get(0));
		
		
		List<PaymentPlan> paymentPlans = new ArrayList<PaymentPlan>();

		PaymentPlan plan = new PaymentPlan();
		plan.setPlanId(111123);
		plan.setPlanName("test Plan");
		
		Installment planDetails = new Installment();
		planDetails.setInstallmentAmount(new BigDecimal("111111111"));
		planDetails.setMonthDiff(1);
		planDetails.setInstallmentId(111);
		planDetails.setInstallmentName("th installment");
		planDetails.setPaymentPlan(plan);
		planDetails.setYearDiff(100);

		plan.setSites(new HashSet(sitePurchasedList));	
		paymentPlans.add(plan);
		
		sitePurchased.setPaymentPlan((paymentPlans.get(0)));
		
		customer.setSites(new HashSet(sitePurchasedList));
		
		Session session2 = HibernateUtil.getSession();
		Transaction transaction2 = session2.beginTransaction();
		session2.merge(customer);
		transaction2.commit();
		
//		Session session = HibernateUtil.getSession();
//		Transaction transaction = session.beginTransaction();
//		session.save(customer);
//		transaction.commit();
		
//		for(PaymentPlan paymentPlan : paymentPlans) { 
//			Session session2 = HibernateUtil.getSession();
//			Transaction transaction2 = session2.beginTransaction();
//			session2.merge(paymentPlan);
//			transaction2.commit();
//		}
//		
		//for(SitePurchased sitePurchasedItem : sitePurchasedList) {
//			Session session2 = HibernateUtil.getSession();
//			Transaction transaction2 = session2.beginTransaction();
//			session2.merge(sitePurchased);
//			transaction2.commit();
		//}
//		Session session3 = HibernateUtil.getSession();
//		Transaction transaction3 = session3.beginTransaction();
//		session3.merge(object);
//		transaction3.commit();
	}
	
	
}
