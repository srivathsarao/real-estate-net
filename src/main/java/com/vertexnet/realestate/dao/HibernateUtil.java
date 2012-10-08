package com.vertexnet.realestate.dao;

import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.JOptionPane;

import org.hibernate.CacheMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.vertexnet.realestatenet.model.bean.Agent;
import com.vertexnet.realestatenet.model.bean.Application;
import com.vertexnet.realestatenet.model.bean.Area;
import com.vertexnet.realestatenet.model.bean.Customer;
import com.vertexnet.realestatenet.model.bean.Employee;
import com.vertexnet.realestatenet.model.bean.Installment;
import com.vertexnet.realestatenet.model.bean.Layout;
import com.vertexnet.realestatenet.model.bean.Payment;
import com.vertexnet.realestatenet.model.bean.PaymentPlan;
import com.vertexnet.realestatenet.model.bean.Photo;
import com.vertexnet.realestatenet.model.bean.SecurityQuestion;
import com.vertexnet.realestatenet.model.bean.Site;

public class HibernateUtil {
	private static Logger log = Logger.getLogger(HibernateUtil.class.getName());
	private static SessionFactory sessionFactory;
	private static Session session;
	
	public static void createSessionFactory() throws Exception {
		//Setup Logger
		FileHandler fileHandler = new FileHandler("RealEstateLogFile.log");
		log.addHandler(fileHandler); 
		SimpleFormatter formatter = new SimpleFormatter();  
		fileHandler.setFormatter(formatter);
		
		//Setup Configuration
		Configuration configuration = new Configuration();
		configuration.configure();
		
		configuration.addAnnotatedClass(Area.class);
		configuration.addAnnotatedClass(Customer.class);
		configuration.addAnnotatedClass(Site.class);
		configuration.addAnnotatedClass(Layout.class);
		configuration.addAnnotatedClass(PaymentPlan.class);
		configuration.addAnnotatedClass(Installment.class);
		configuration.addAnnotatedClass(Payment.class);
		configuration.addAnnotatedClass(Agent.class);
		configuration.addAnnotatedClass(SecurityQuestion.class);
		configuration.addAnnotatedClass(Employee.class);
		configuration.addAnnotatedClass(Photo.class);
		configuration.addAnnotatedClass(Application.class);
		
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}

	public static Session getSession() {
		if(session == null) {
			session = sessionFactory.openSession();
			return session;
		}
		return session;
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static boolean saveObject(Object object) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			session.save(object);
			session.flush();
			transaction.commit();
			return true;
		} catch (RuntimeException e) {
			try {
				transaction.rollback();
				session = sessionFactory.openSession();
				if(!saveObject(object)) {
					JOptionPane.showMessageDialog(null, "Failed to Save Objects");
					log.log(Level.SEVERE, "Couldn’t roll back Save transaction", e);
				}
			} catch (RuntimeException rbe) {
				log.log(Level.SEVERE, "Couldn’t roll back Save transaction", rbe);
			}
		}
		return false;
	}
	
	public static boolean updateObject(Object object) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			session.update(object);
			session.flush();
			transaction.commit();
			return true;
		} catch (RuntimeException e) {
			try {
				JOptionPane.showMessageDialog(null, "Error in Transaction");
				transaction.rollback();
				session = sessionFactory.openSession();
				if(!updateObject(object)) {
					JOptionPane.showMessageDialog(null, "Failed to Update Objects");
					log.log(Level.SEVERE, "Couldn’t roll back Update transaction", e);
				}
			} catch (RuntimeException rbe) {
				log.log(Level.SEVERE, "Couldn’t roll back Update transaction", rbe);
			}
		}
		return false; 
	}
	
	public static boolean mergeObject(Object object) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			session.merge(object);
			session.flush();
			transaction.commit();
			return true;
		} catch (RuntimeException e) {
			try {
				transaction.rollback();
				session = sessionFactory.openSession();
				if(!mergeObject(object)) {
					JOptionPane.showMessageDialog(null, "Failed to Update Objects");
					log.log(Level.SEVERE, "Couldn’t roll back Merge transaction", e);
				}
			} catch (RuntimeException rbe) {
				log.log(Level.SEVERE, "Couldn’t roll back Merge transaction", rbe);
			}
		}
		return false;
	}

	//	
//	public static void saveOrUpdateObject(Object object) {
//		Session session = HibernateUtil.getSession();
//		Transaction transaction = session.beginTransaction();
//		session.merge(object);
//		session.flush();
//		transaction.commit();
//	}
	
	public static boolean deleteObject(Object object) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSession();
			HibernateUtil.refreshObject(object);
			transaction = session.beginTransaction();

			session.delete(object);
			session.flush();
			transaction.commit();
			return true;
		} catch (RuntimeException e) {
			try {
				transaction.rollback();
				session = sessionFactory.openSession();
				if(!deleteObject(object)) {
					JOptionPane.showMessageDialog(null, "Failed to Delete Objects");
					log.log(Level.SEVERE, "Couldn’t roll back Delete transaction", e);
				}
			} catch (RuntimeException rbe) {
				log.log(Level.SEVERE, "Couldn’t roll back Delete transaction", rbe);
			}
		}
		return false;
	}
	
	public static List<?> getObjects(Class<?> classVal) {
		Session session = HibernateUtil.getSession();
		if(classVal.getName().equals(Customer.class.getName())) {
			session.setCacheMode(CacheMode.IGNORE);
			sessionFactory.getCache().evictEntityRegion(Customer.class);
		} 
		Query query = session.createQuery("from " + classVal.getName());
		List<?> data =  query.list();
		return data;
	}
	
	public static void callNativeQuery(String query) {
		Session session = HibernateUtil.getSession();
		session.createSQLQuery(query);
	}

	public static void refreshObject(Object customer) {
		Session session = HibernateUtil.getSession();
		session.merge(customer);
		session.refresh(customer);
	}
 }
