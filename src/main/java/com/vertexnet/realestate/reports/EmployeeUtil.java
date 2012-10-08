package com.vertexnet.realestate.reports;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.vertexnet.realestatenet.model.bean.Employee;

public class EmployeeUtil {

	private List<Employee> employees;
	private BigDecimal totalPayment;

	public EmployeeUtil(List<Employee> employees) {
		this.employees = employees;
		totalPayment = new BigDecimal(0);
	}

	public List<String> getEmployeeDetails() {
		List<String> empList = new ArrayList<String>();
		for(Employee employee :employees) {
			BigDecimal totalCustomerPayment = new BigDecimal(0);
			StringBuffer data = new StringBuffer();
			data.append("<tr>");
			data.append("<td bgcolor=\"#C0C0C0\" colspan=\"2\">Employee Name = ").append(employee.getName()).append(" ").append("</td>");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(employee.getStartDate());
			
			Date tillDate;
			if(employee.getEndDate() == null || (employee.getEndDate() != null && employee.getEndDate().after(new Date()))) {
				tillDate = new Date();
			} else {
				tillDate = employee.getEndDate();
			}
			data.append("</tr><tr>");
			while(calendar.getTime().before(tillDate)) {
				calendar.add(Calendar.MONTH, 1);
				data.append("<td>").append("Salary For the Month").append(new SimpleDateFormat(" MMM-yy").format(calendar.getTime())).append("</td>");
				data.append("<td>Rs. ").append(employee.getSalary()).append("</td>");
				totalCustomerPayment = totalCustomerPayment.add(employee.getSalary());
			}
			data.append("</tr>");
			data.append("<tr><td>");
			data.append("Total Salary</td>");
			data.append("<td>Rs. ").append(totalCustomerPayment).append("</td>");
			data.append("<tr>");
			empList.add(data.toString());
			totalPayment = totalPayment.add(totalCustomerPayment);
		}
		return empList;
	}

	public Object getTotalPayment() {
		return totalPayment;
	}

}
