package com.vertexnet.realestate.reports;

import com.vertexnet.realestatenet.model.bean.Customer;

public class CustomerUtil {
	private Customer customer;
	
	public CustomerUtil(Customer customer) {
		this.customer = customer;
	}
	
	public String getName() {
		return customer.getFirstName() + " " + customer.getLastName();
	}
	
	public String getPhone() {
		return customer.getPhone();
	}

	public String getMobile() {
		return customer.getMobile();
	}
	
	public Integer getMembershipId() {
		return customer.getMembershipId();
	}
	
	
//	public List<String> getCustomerDetails() {
//		List<String> customerData = new ArrayList<String>();
//		for(Customer customer : customers) {
//			StringBuffer data = new StringBuffer();
//			data.append("<tr>");
//			data.append("<td bgcolor=\"#C0C0C0\" colspan=\"2\">Customer Name = ").append(customer.getFirstName()).append(" ").append(customer.getLastName()).append("</td>");
//			data.append("</tr>");
//			data.append("<tr><td bgcolor=\"#C0C0C0\" colspan=\"2\"><p>Customer Id = ").append(customer.getMembershipId()).append("</td></tr>\n");;
//			for(SitePurchased sitePurchased : customer.getSitePurchased()) {
//				
//				data.append("<tr><td>&nbsp; Site =").append(sitePurchased.getSiteId());
//				data.append(", Layout = ").append(sitePurchased.getLayout()).append("</td><td /></tr>\n");
//				
//				if(sitePurchased.getPaymentPlan() == null) {
//					data.append("<tr><td>&nbsp;&nbsp; Please select a Payment Plan for this Site</td></tr>");
//					continue;
//				}
//				for(PaymentPlanDetails paymentPlanDetails : sitePurchased.getPaymentPlan().getPaymentPlanDetails()) {
//					Payment paymentForPlan = null;
//					for(Payment payment : sitePurchased.getPayments()) {
//						if(paymentPlanDetails.equals(payment.getPaymentPlanDetails())) {
//							paymentForPlan = payment;
//							break;
//						}
//					}
//					Calendar calendar = Calendar.getInstance();
//					if(sitePurchased.getPaymentStartDate() != null) {
//						calendar.setTime(sitePurchased.getPaymentStartDate());
//					}
//					calendar.add(Calendar.MONTH, paymentPlanDetails.getMonthDiff());
//					calendar.add(Calendar.YEAR, paymentPlanDetails.getYearDiff());
//					
//					Calendar calender2 = Calendar.getInstance();
//					if(calendar.after(calender2)) {
//						data.append("<tr><td>&nbsp;&nbsp; Future Payment, ").append(paymentPlanDetails.getInstallmentName());
//						data.append(" Amount =</td>");
//						data.append("<td>");
//						data.append(paymentPlanDetails.getInstallmentAmount()).append("</td></tr>\n");
//					} else {
//						data.append("<tr><td>&nbsp;&nbsp; Payment ").append(paymentPlanDetails.getInstallmentName());
//						data.append(", Actual Amount =").append(paymentPlanDetails.getInstallmentAmount());
//						data.append(", Amount Paid = </td>");
//						data.append("<td>");
//						data.append(paymentForPlan.getAmount());
//						data.append(" On").append(paymentForPlan.getPaymentDate()).append("</td></tr>\n");
//						totalPayment = totalPayment.add(paymentForPlan.getAmount());
//					}
//				}
//			}
//			customerData.add(data.toString());
//		}
//		return customerData;
//	}

//	public BigDecimal getTotalPayment() {
////		return totalPayment;
//	}
	
	class Site {
		
	}
}
