package com.vertexnet.realestate.dao;

import java.util.List;

import com.vertexnet.realestatenet.model.bean.SecurityQuestion;

public class SecurityQuestionsDAO {

	@SuppressWarnings("unchecked")
	public static List<SecurityQuestion> getSecurityQuestions() {
		return (List<SecurityQuestion>) HibernateUtil.getObjects(SecurityQuestion.class);
	}

	public static void saveSecurityQuestions(List<SecurityQuestion> sqToAdd) {
		for(SecurityQuestion sq : sqToAdd) {
			HibernateUtil.saveObject(sq);
		}
	}

	public static void deleteSecurityQuestions(List<SecurityQuestion> sqToDelete) {
		for(SecurityQuestion sq : sqToDelete) {
			HibernateUtil.deleteObject(sq);
		}
	}

}
