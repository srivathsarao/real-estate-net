package com.vertexnet.realestate.constants;

public enum ModelConstants {
	CUSTOMER_LIST("customerList"),
	ADD_REMOVE_CUSTOMER("addCustomer"),

	LAYOUT_LIST("layoutList"),
	LAYOUT_DETAILS("layoutDetails"),

	REFRESH_AGENT("refreshAgent"),
	AGENT_LIST("agentList"),
	
	PAYMENT_PLAN_LIST("paymentPlanList"),
	
	GET_SECURITY_QUESTIONS("getSecurityQuestions"),
	GET_PASSWORD("getPassword"),
	DO_LOGIN("doLogin"),
	
	LAYOUT_MODEL("layoutTable", "layoutReload"),
	PAYMENT_PLAN_MODEL("paymentPlanTable", "paymentPlanReload"),
	AGENT_MODEL("agentTable", "agentReload"),
	EMPLOYEE_MODEL("employeeTable", "employeeReload"),
	ADMIN_MODEL("adminTable","adminReload"),
;
	
	private String value;
	
	private String table;
	private String reload;
	
	private ModelConstants(String table, String reload) {
		this.table = table;
		this.reload = reload;
	}
	
	public String getTable() {
		return table;
	}

	public String getReload() {
		return reload;
	}

	ModelConstants(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
