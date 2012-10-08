package com.vertexnet.realestate.constants;

public enum UIConstants {
	LOGIN_BUTTON("loginButton"),
	FORGOT_PASS_LABEL("forgotPassLabel"),
	FORGOT_PASS_BUTTON("forgotPassButton"),
	CUSTOMER_ID_ADD("customerIdAdd"),
	
	CUSTOMER("makePayment","customerReport", "addCustomer", "editCustomer", "deleteCustomer", "saveCustomer"), 
	LAYOUT("layoutReport", "addLayout", "editLayout", "deleteLayout", "saveLayout"),
	PAYMENT_PLAN("paymentPlanReport","addPaymentPlan", "editPaymentPlan", "deletePaymentPlan", "savePaymentPlan"), 
	AGENT("agentReport", "addAgent", "editAgent", "deleteAgent", "saveAgent"),
	EMPLOYEE("employeeReport", "addEmployee", "editEmployee", "deleteEmployee", "saveEmployee"),
	ADMIN("resetAdmin", "", "", "", "saveAdminData"),
	ALL("allReport", "","","", "");
	
	private String makePayment;
	private String report;
	private String add;
	private String edit;
	private String delete;
	private String save;

	public String getMakePayment() {
		return makePayment;
	}

	public String getReport() {
		return report;
	}

	public String getAdd() {
		return add;
	}

	public String getEdit() {
		return edit;
	}

	public String getDelete() {
		return delete;
	}

	public String getSave() {
		return save;
	}
	
	private UIConstants(String makePayment, String report, String add, String edit, String delete, String save) {
		this(report, add, edit, delete, save);
		this.makePayment = makePayment;
	}

	UIConstants(String report, String add, String edit, String delete, String save) {
		this.report = report;
		this.add = add;
		this.edit = edit;
		this.delete = delete;
		this.save = save;
	}
	
	private String value;

	UIConstants(String mode) {
		this.value = mode;
	}

	public String getValue() {
		return value;
	}
}
