package com.vertexnet.realestate.reports;

import java.io.File;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.vertexnet.realestate.bo.TotalAgentPaymentUtility;
import com.vertexnet.realestate.bo.TotalCustomerPaymentUtility;
import com.vertexnet.realestate.bo.TotalPaymentUtility;
import com.vertexnet.realestatenet.model.bean.Agent;
import com.vertexnet.realestatenet.model.bean.Customer;
import com.vertexnet.realestatenet.model.bean.Employee;
import com.vertexnet.realestatenet.model.bean.Layout;
import com.vertexnet.realestatenet.model.bean.PaymentPlan;
import com.vertexnet.realestatenet.model.bean.ReportBean;

public class ReportBuilder {

	public void generateCustomersReport(List<Customer> customers) {
		try {
			VelocityEngine ve = new VelocityEngine();
			ve.init();

			// Template template =
			// ve.getTemplate("/templates/CustomerReport.vm");
			Template template = ve.getTemplate("/templates/CustomerReport.html");
			/* create a context and add data */
			VelocityContext context = new VelocityContext();

			List<TotalPaymentUtility> totalPaymentList = new ArrayList<TotalPaymentUtility>();
			for (Customer customer : customers) {
				totalPaymentList.add(new TotalPaymentUtility(customer));
			}

			context.put("TotalPaymentUtilityList", totalPaymentList);
			context.put("dateFormatter", new SimpleDateFormat("dd-MMM-yyyy"));
			context.put("currentdir", new File(".").getCanonicalPath());

			/* now render the template into a StringWriter */
			StringWriter writer = new StringWriter();
			template.merge(context, writer);
			/* show the World */
			System.out.println(writer.toString());

			DocumentUtility utility = new DocumentUtility();
			utility.setInputStream(writer.toString());
			utility.openPDF();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void generateLayoutsReport(List<Layout> layout) {
		try {
			VelocityEngine ve = new VelocityEngine();
			ve.init();

			Template template = ve.getTemplate("/templates/CustomerReport.vm");
			/* create a context and add data */
			VelocityContext context = new VelocityContext();
			context.put("customers", new LayoutUtil(layout).getLayoutDetails());
			context.put("currentdir", new File(".").getCanonicalPath());
			
			/* now render the template into a StringWriter */
			StringWriter writer = new StringWriter();
			template.merge(context, writer);
			/* show the World */
			System.out.println(writer.toString());

			DocumentUtility utility = new DocumentUtility();
			utility.setInputStream(writer.toString());
			utility.openPDF();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void generatePaymentPlansReport(List<PaymentPlan> paymentPlans) {
		try {
			VelocityEngine ve = new VelocityEngine();
			ve.init();

			Template template = ve.getTemplate("/templates/CustomerReport.vm");
			/* create a context and add data */
			VelocityContext context = new VelocityContext();
			context.put("customers", new PaymentPlanUtil(paymentPlans).getPaymentPlanDetails());
			context.put("currentdir", new File(".").getCanonicalPath());
			
			/* now render the template into a StringWriter */
			StringWriter writer = new StringWriter();
			template.merge(context, writer);
			/* show the World */
			System.out.println(writer.toString());

			DocumentUtility utility = new DocumentUtility();
			utility.setInputStream(writer.toString());
			utility.openPDF();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void generateAgentsReport(List<Agent> agents) {
		try {
			VelocityEngine ve = new VelocityEngine();
			ve.init();

			Template template = ve.getTemplate("/templates/AgentsReport.vm");
			/* create a context and add data */
			VelocityContext context = new VelocityContext();
			context.put("Agents", new AgentUtil(agents).getAgentDetails());
			context.put("currentdir", new File(".").getCanonicalPath());
			
			/* now render the template into a StringWriter */
			StringWriter writer = new StringWriter();
			template.merge(context, writer);
			/* show the World */
			System.out.println(writer.toString());

			DocumentUtility utility = new DocumentUtility();
			utility.setInputStream(writer.toString());
			utility.openPDF();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void generateEmployeesReport(List<Employee> employees) {
		try {
			VelocityEngine ve = new VelocityEngine();
			ve.init();

			Template template = ve.getTemplate("/templates/EmployeesReport.vm");
			/* create a context and add data */
			VelocityContext context = new VelocityContext();
			context.put("employees", new EmployeeUtil(employees).getEmployeeDetails());
			context.put("currentdir", new File(".").getCanonicalPath());
			
			/* now render the template into a StringWriter */
			StringWriter writer = new StringWriter();
			template.merge(context, writer);
			/* show the World */
			System.out.println(writer.toString());

			DocumentUtility utility = new DocumentUtility();
			utility.setInputStream(writer.toString());
			utility.openPDF();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void generateCompleteReport(ReportBean reportBean, List<Customer> customers, List<Agent> agents) {
		try {
			VelocityEngine ve = new VelocityEngine();
			ve.init();

			Template template = ve.getTemplate("/templates/CompleteReport.html");
			/* create a context and add data */
			VelocityContext context = new VelocityContext();

			context.put("reportBean", reportBean);
			List<TotalPaymentUtility> totalPaymentList = new ArrayList<TotalPaymentUtility>();
			for (Customer customer : customers) {
				totalPaymentList.add(new TotalPaymentUtility(customer));
			}

			TotalCustomerPaymentUtility totalCustomerPaymentUtility = new TotalCustomerPaymentUtility(totalPaymentList);
			context.put("totalCustomerPaymentUtil", totalCustomerPaymentUtility);

			List<TotalPaymentUtility> totalOverDueList = new ArrayList<TotalPaymentUtility>();
			for (TotalPaymentUtility totalPaymentUtility : totalPaymentList) {
				if (reportBean.isCompleteReport()) {
					BigDecimal totalPayment = totalPaymentUtility.getPendingAmountTillDate();
					if (totalPayment != null && (totalPayment.compareTo(BigDecimal.ZERO) > 0)) {
						totalOverDueList.add(totalPaymentUtility);
					}
				} else {
					BigDecimal totalPayment = totalPaymentUtility.getPendingAmountFromTill(reportBean.getFromDate(), reportBean.getToDate());
					if (totalPayment != null && (totalPayment.compareTo(BigDecimal.ZERO) > 0)) {
						totalOverDueList.add(totalPaymentUtility);
					}
				}
			}
			context.put("TotalOverDueList", totalOverDueList);

			List<TotalPaymentUtility> totalDueList = new ArrayList<TotalPaymentUtility>();
			for (TotalPaymentUtility totalPaymentUtility : totalPaymentList) {
				if (reportBean.isCompleteReport()) {
					BigDecimal totalPayment = totalPaymentUtility.getFuturePendingAmount();
					if (totalPayment != null && (totalPayment.compareTo(BigDecimal.ZERO) > 0)) {
						totalDueList.add(totalPaymentUtility);
					}
				} else {
					BigDecimal totalPayment = totalPaymentUtility.getFuturePendingAmountFromTill(reportBean.getFromDate(), reportBean.getToDate());
					if (totalPayment != null && (totalPayment.compareTo(BigDecimal.ZERO) > 0)) {
						totalDueList.add(totalPaymentUtility);
					}
				}
			}
			context.put("TotalDueList", totalDueList);

			List<TotalPaymentUtility> totalPaidList = new ArrayList<TotalPaymentUtility>();
			for (TotalPaymentUtility totalPaymentUtility : totalPaymentList) {
				if (reportBean.isCompleteReport()) {
					BigDecimal totalPayment = totalPaymentUtility.getAmountPaidTillDate();
					if (totalPayment != null && (totalPayment.compareTo(BigDecimal.ZERO) > 0)) {
						totalPaidList.add(totalPaymentUtility);
					}
				} else {
					BigDecimal totalPayment = totalPaymentUtility.getAmountPaidFromTill(reportBean.getFromDate(), reportBean.getToDate());
					if (totalPayment != null && (totalPayment.compareTo(BigDecimal.ZERO) > 0)) {
						totalPaidList.add(totalPaymentUtility);
					}
				}
			}
			Collections.sort(totalPaidList);
			context.put("TotalPaidList", totalPaidList);

			TotalAgentPaymentUtility totalAgentPaymentUtility = new TotalAgentPaymentUtility(agents, totalPaymentList);
			context.put("TotalAgentPaymentUtility", totalAgentPaymentUtility);

//		context.put("customerSelected", customersSelected);
//		//CustomerUtil customerUtil = new CustomerUtil(RealEstateUtilityModel.customers);
//		//context.put("customers", customerUtil.getCustomerDetails());
//		//context.put("customerTotalPayment", customerUtil.getTotalPayment());
//		
//		context.put("employeeSelected", employeesSelected);
//		EmployeeUtil employeeUtil = new EmployeeUtil(RealEstateUtilityModel.employees);
//		context.put("employees", employeeUtil.getEmployeeDetails());
//		context.put("employeeTotalPayment", employeeUtil.getTotalPayment());
//		
//		context.put("agentsSelected", agentsSelected);
//		AgentUtil agentUtil = new AgentUtil(RealEstateUtilityModel.agents);
//		context.put("Agents", agentUtil.getAgentDetails());
//		context.put("agentTotalPayment", agentUtil.getTotalPayment());

		
		context.put("currentdir", new File(".").getCanonicalPath());
		context.put("dateFormatter", new SimpleDateFormat("dd-MMM-yyyy"));
		
		/* now render the template into a StringWriter */
		StringWriter writer = new StringWriter();
		template.merge(context, writer);
		/* show the World */

		DocumentUtility utility = new DocumentUtility();
		utility.setInputStream(writer.toString());
		utility.openPDF();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
