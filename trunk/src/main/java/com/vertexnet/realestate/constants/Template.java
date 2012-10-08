package com.vertexnet.realestate.constants;

public enum Template {

	PAYMENT_REPORT("PaymentReport.vm"), 
	SITES_REPORT("SitesReport.vm"),
	EXPENDITURE_REPORT("ExpenditureReport.vm"), 
	TOTAL_EXPENDITURE_REPORT("TotalExpenditureReport.vm"), 
	TOTAL_PAYMENT_REPORT("TotalPaymentReport.vm");

	private String templateName;

	public String getTemplateName() {
		return templateName;
	}

	Template(String templateName) {
		this.templateName = templateName;
	}
}
