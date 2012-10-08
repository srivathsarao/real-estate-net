package com.vertexnet.realestate.model;

import static com.vertexnet.realestate.constants.ModelConstants.DO_LOGIN;
import static com.vertexnet.realestate.constants.ModelConstants.GET_PASSWORD;
import static com.vertexnet.realestate.constants.ModelConstants.GET_SECURITY_QUESTIONS;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import com.vertexnet.realestate.dao.LoginDAO;
import com.vertexnet.realestate.interfaces.Controller;
import com.vertexnet.realestatenet.model.bean.SecurityQuestion;

public class LoginModel extends PropertyChangeSupport {
	
	private static final long serialVersionUID = 1L;
	private LoginDAO dao;
	private List<SecurityQuestion> securityQuestions;

	public LoginModel(Controller controller) {
		super(controller);
		this.addPropertyChangeListener((PropertyChangeListener)controller);
		this.dao = new LoginDAO();
	}

	public void getSecurityQuestions() {
		securityQuestions = dao.getSecurityQuestions();
		firePropertyChange(GET_SECURITY_QUESTIONS.getValue(), null, securityQuestions);
	}

	public void getPassword(String userName, String question, String answer) {
		SecurityQuestion securityQuestion = null;
		for(SecurityQuestion securityQuestionSel : securityQuestions) {
			if(securityQuestionSel.getQuestion().trim().equals(question.trim())) {
				securityQuestion = securityQuestionSel;
			}
		}
		String password = dao.getPassword(userName,  securityQuestion, answer);
		firePropertyChange(GET_PASSWORD.getValue(), null, password);
	}

	public void doLogin(String userName, String password) {
		boolean success = true;//dao.doLogin(userName, password);
		firePropertyChange(DO_LOGIN.getValue(), null, success);
	}
}
