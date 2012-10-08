package com.vertexnet.realestate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.vertexnet.realestatenet.model.bean.Employee;
import com.vertexnet.realestatenet.model.bean.SecurityQuestion;

public class LoginDAO {

	@SuppressWarnings("unchecked")
	public List<SecurityQuestion> getSecurityQuestions() {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(SecurityQuestion.class);
		return criteria.list();
	}

	public String getPassword(String userName, SecurityQuestion question, String answer) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("employeeCompoundKey.loginId", userName));
		criteria.add(Restrictions.eq("securityAnswer", answer));
		criteria.add(Restrictions.eq("securityQuestion", question));
		if(criteria.list().isEmpty()) {
			return "Incorrect User Id/Answer";
		}
		Employee employee = (Employee) criteria.list().get(0);
		return "Your Password is " + employee.getPassword();
	}

	public boolean doLogin(String userName, String password) {
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("employeeCompoundKey.loginId", userName));
		criteria.add(Restrictions.eq("password", password));
		if(criteria.list().isEmpty()) {
			return false;
		}
		return true;
	}

}
