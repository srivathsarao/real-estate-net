package com.vertexnet.realestatenet.model.bean;

import java.util.Date;

public class ReportBean {
	private boolean completeReport;
	private Date fromDate;
	private Date toDate;
	private boolean Customers;
	private boolean Agents;
	private boolean overDue;
	private boolean due;
	private boolean paid;

	public ReportBean() {
	}

	public ReportBean(boolean completeReport, Date fromDate, Date toDate, boolean customers, boolean agents, boolean overDue, boolean due,
			boolean paid) {
		super();
		this.completeReport = completeReport;
		this.fromDate = fromDate;
		this.toDate = toDate;
		Customers = customers;
		Agents = agents;
		this.overDue = overDue;
		this.due = due;
		this.paid = paid;
	}

	public boolean isCompleteReport() {
		return completeReport;
	}

	public void setCompleteReport(boolean completeReport) {
		this.completeReport = completeReport;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public boolean isCustomers() {
		return Customers;
	}

	public void setCustomers(boolean customers) {
		Customers = customers;
	}

	public boolean isAgents() {
		return Agents;
	}

	public void setAgents(boolean agents) {
		Agents = agents;
	}

	public boolean isOverDue() {
		return overDue;
	}

	public void setOverDue(boolean overDue) {
		this.overDue = overDue;
	}

	public boolean isDue() {
		return due;
	}

	public void setDue(boolean due) {
		this.due = due;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}
}
