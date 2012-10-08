package com.vertexnet.realestate.constants;

import java.util.Calendar;
import java.util.Date;

import com.vertexnet.realestatenet.model.bean.ReportBean;

public enum ReportTypes {
	
	/* Field List
	private boolean completeReport;
	private Date fromDate;
	private Date toDate;
	private boolean Customers;
	private boolean Agents;
	private boolean overDue;
	private boolean due;
	private boolean paid;
	*/

	CUSTOM("Custom", new ReportBean(true, null, null, true, true, true, true, true)), 
	COMPLETE("Complete", new ReportBean(true, null, null, true,	true, true, true, true)), 
	YEARLY("Yearly", new ReportBean(false, getLast(Calendar.YEAR), new Date(), true, true, true, true, true)), 
	MONTHLY("Monthly", new ReportBean(false, getLast(Calendar.MONTH), new Date(), true, true, true, true, true)), 
	DAILY("Daily", new ReportBean(false, getLast(Calendar.DATE), new Date(), true, true, true, true, true)), 
	COMPLETE_PROFIT("Complete Profit", new ReportBean(true, null, null, true, false, false, false, true)), 
	COMPLETE_LOSS("Complete Loss", new ReportBean(true, null, null, true, true, true, false, false)), 
	YEARLY_PROFIT("Yearly Profit", new ReportBean(false, getLast(Calendar.YEAR), new Date(), true, false, false, false, true)), 
	YEARLY_LOSS("Yearly Loss", new ReportBean(false, getLast(Calendar.YEAR), new Date(), true, true, true, false, false)), 
	MONTHLY_PROFIT("Monthly Profit", new ReportBean(false, getLast(Calendar.MONTH), new Date(), true, false, false, false, true)), 
	MONTHLY_LOSS("Monthly Loss", new ReportBean(false, getLast(Calendar.MONTH), new Date(), true, true, true, false, false)), 
	DAILY_PROFIT("Daily Profit", new ReportBean(false, getLast(Calendar.DATE), new Date(), true, false, false, false, true)), 
	DAILY_LOSS("Daily Loss", new ReportBean(false, getLast(Calendar.DATE), new Date(), true, true, true, false, false));
	
	private String name;
	private ReportBean reportBean;
	
	private ReportTypes(String name, ReportBean reportBean) {
		this.name = name;
		this.reportBean = reportBean;
	}

	public ReportBean getReportBean() {
		return reportBean;
	}

	@Override
	public String toString() {
		return name;
	}

	private static Date getLast(Integer value) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(value, calendar.get(value) - 1);
		return calendar.getTime();
	}
}
