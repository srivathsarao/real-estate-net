package com.vertexnet.realestate.controller;

import static com.vertexnet.realestate.constants.ModelConstants.DO_LOGIN;
import static com.vertexnet.realestate.constants.ModelConstants.GET_PASSWORD;
import static com.vertexnet.realestate.constants.ModelConstants.GET_SECURITY_QUESTIONS;
import static com.vertexnet.realestate.constants.UIConstants.FORGOT_PASS_BUTTON;
import static com.vertexnet.realestate.constants.UIConstants.FORGOT_PASS_LABEL;
import static com.vertexnet.realestate.constants.UIConstants.LOGIN_BUTTON;

import java.awt.AWTEvent;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import com.vertexnet.realestate.interfaces.Controller;
import com.vertexnet.realestate.model.LoginModel;
import com.vertexnet.realestate.view.frame.LoginForm;
import com.vertexnet.realestatenet.model.bean.SecurityQuestion;

public class LoginController implements Controller, PropertyChangeListener {

	private LoginForm view;
	private LoginModel model;

	public LoginController(LoginForm loginForm) {
		super();
		this.view = loginForm;
		this.model = new LoginModel(this);
	}

	public void handleEvent(AWTEvent evt) {
		String name = ((Component) evt.getSource()).getName();
		if (name.equals(FORGOT_PASS_LABEL.getValue())) {
			model.getSecurityQuestions();
		} else if (name.equals(FORGOT_PASS_BUTTON.getValue())) {
			String userId = view.getUserIdTextField().getText();
			String answer = new String(view.getPasswordField().getPassword());
			String question = view.getSecurityQuestionComboBox().getSelectedItem().toString();
			model.getPassword(userId, question, answer);
		} else if (name.equals(LOGIN_BUTTON.getValue())) {
			String userId = view.getUserIdLoginTextField().getText();
			String password = new String(view.getPasswordLoginTextField().getPassword());
			model.doLogin(userId, password);
		}
	}

	@SuppressWarnings("unchecked")
	public void propertyChange(PropertyChangeEvent evt) {
		String name = evt.getPropertyName();
		if (name.equals(DO_LOGIN.getValue())) {
			boolean val = (Boolean) evt.getNewValue();
			view.setSuccess(val);
		} else if (name.equals(GET_SECURITY_QUESTIONS.getValue())) {
			List<SecurityQuestion> securityQuestions = (List<SecurityQuestion>) evt.getNewValue();
			view.setSecurityQuestions(securityQuestions);
		} else if (name.equals(GET_PASSWORD.getValue())) {
			String issue = (String) evt.getNewValue();
			view.showPassword(issue);
		}
	}
}
