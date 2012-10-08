package com.vertexnet.realestate.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.vertexnet.realestatenet.model.bean.Agent;
import com.vertexnet.realestatenet.model.bean.Installment;
import com.vertexnet.realestatenet.model.bean.Payment;
import com.vertexnet.realestatenet.model.bean.PaymentPlan;
import com.vertexnet.realestatenet.model.bean.Site;

public class PaymentUtility {
	List<PaymentPlanUI> planUI;
	private Site sitePurchased;
	private PaymentPlan paymentPlan;

	public PaymentUtility(Site sitePurchased) {
		this.sitePurchased = sitePurchased;
		paymentPlan = sitePurchased.getPaymentPlan();
		Set<Payment> payments = sitePurchased.getPayments();

		planUI = new ArrayList<PaymentUtility.PaymentPlanUI>();
		if (paymentPlan == null || paymentPlan.getInstallments() == null) {
			return;
		}

		for (Installment installment : paymentPlan.getInstallments()) {
			List<Payment> perticularPayment = new ArrayList<Payment>();

			if (payments != null && !payments.isEmpty()) {
				for (Payment payment : payments) {
					if (payment.getInstallment().getInstallmentId().equals(installment.getInstallmentId())) {
						perticularPayment.add(payment);
					}
				}
			}

			for (int i = 0; i < installment.getNumRepeat(); i++) {
				PaymentPlanUI ui = new PaymentPlanUI();
				Date previousDate;
				if (i == 0) {
					if (sitePurchased.getPaymentStartDate() == null) {
						previousDate = new Date();
					} else {
						previousDate = sitePurchased.getPaymentStartDate();
					}

				} else {
					previousDate = planUI.get(i - 1).getPaymentDate();
				}
				Calendar previousDateCal = Calendar.getInstance();
				previousDateCal.setTime(previousDate);
				previousDateCal.add(Calendar.MONTH, installment.getMonthDiff());
				previousDateCal.add(Calendar.YEAR, installment.getYearDiff());

				ui.setPaymentDate(previousDateCal.getTime());
				ui.setInstallmentAmount(installment.getInstallmentAmount());

				ui.setDetails(installment);
				if ((perticularPayment.size() - 1) >= i) {
					ui.setPayment(perticularPayment.get(i));
				}
				planUI.add(ui);
				Collections.sort(planUI);
			}

		}
	}

	public BigDecimal getTotalPaymentTillToday() {
		BigDecimal totalPayment = new BigDecimal(0);
		if (planUI == null || planUI.isEmpty()) {
			return totalPayment;
		}
		for (PaymentPlanUI ui : planUI) {
			if (ui.getPaymentDate().before(new Date())) {
				totalPayment = totalPayment.add(ui.getInstallmentAmount());
			}
		}
		return totalPayment;
	}

	public BigDecimal getTotalPaymentFromTill(Date fromDate, Date ToDate) {
		BigDecimal totalPayment = new BigDecimal(0);
		if (planUI == null || planUI.isEmpty()) {
			return totalPayment;
		}
		for (PaymentPlanUI ui : planUI) {
			if (ui.getPaymentDate().after(fromDate) && ui.getPaymentDate().before(ToDate)) {
				totalPayment = totalPayment.add(ui.getInstallmentAmount());
			}
		}
		return totalPayment;
	}

	public Boolean isPaymentPending() {
		if (planUI == null || planUI.isEmpty()) {
			return false;
		}
		for (PaymentPlanUI ui : planUI) {
			if (ui.getPaymentDate().before(new Date())) {
				if (ui.getPayment() == null) {
					return true;
				}
			}
		}
		return false;
	}

	public BigDecimal getAmountPaidTillDate() {
		BigDecimal totalPayment = new BigDecimal(0);
		if (planUI == null || planUI.isEmpty()) {
			return totalPayment;
		}
		for (PaymentPlanUI ui : planUI) {
			if (ui.getPayment() != null) {
				if (ui.getPaymentDate().before(new Date())) {
					totalPayment = totalPayment.add(ui.getInstallmentAmount());
				} 
			}
		}
		return totalPayment;
	}
	
	public BigDecimal getAmountPaidFromTill(Date fromDate, Date toDate) {
		BigDecimal totalPayment = new BigDecimal(0);
		if (planUI == null || planUI.isEmpty()) {
			return totalPayment;
		}
		for (PaymentPlanUI ui : planUI) {
			if (ui.getPayment() != null) {
				if (ui.getPaymentDate().after(fromDate) && ui.getPaymentDate().before(toDate)) {
					totalPayment = totalPayment.add(ui.getInstallmentAmount());
				} 
			}
		}
		return totalPayment;
	}
	
	public BigDecimal getPendingAmountTillDate() {
		BigDecimal totalPayment = new BigDecimal(0);
		if (planUI == null || planUI.isEmpty()) {
			return totalPayment;
		}
		for (PaymentPlanUI ui : planUI) {
			if (ui.getPayment() == null) {
				if (ui.getPaymentDate().before(new Date())) {
					totalPayment = totalPayment.add(ui.getInstallmentAmount());
				} else {
					break;
				}
			}
		}
		return totalPayment;
	}
	
	public BigDecimal getPendingAmountFromTill(Date fromDate, Date toDate) {
		BigDecimal totalPayment = new BigDecimal(0);
		if (planUI == null || planUI.isEmpty()) {
			return totalPayment;
		}
		for (PaymentPlanUI ui : planUI) {
			if (ui.getPayment() == null) {
				if (ui.getPaymentDate().after(fromDate) && ui.getPaymentDate().before(toDate)) {
					totalPayment = totalPayment.add(ui.getInstallmentAmount());
				} else {
					break;
				}
			}
		}
		return totalPayment;
	}
	
	public BigDecimal getFuturePendingAmountFromTill(Date fromDate, Date toDate) {
		BigDecimal totalPayment = new BigDecimal(0);
		if (planUI == null || planUI.isEmpty()) {
			return totalPayment;
		}
		for (PaymentPlanUI ui : planUI) {
			if (ui.getPayment() == null) {
				if (ui.getPaymentDate().before(new Date()) && ui.getPaymentDate().before(fromDate) && ui.getPaymentDate().after(toDate)) {
					totalPayment = totalPayment.add(ui.getInstallmentAmount());
				} else {
					break;
				}
			}
		}
		return totalPayment;
	}
	
	public BigDecimal getFuturePendingAmount() {
		BigDecimal totalPayment = new BigDecimal(0);
		if (planUI == null || planUI.isEmpty()) {
			return totalPayment;
		}
		for (PaymentPlanUI ui : planUI) {
			if (ui.getPayment() == null) {
				if (ui.getPaymentDate().after(new Date())) {
					totalPayment = totalPayment.add(ui.getInstallmentAmount());
				} else {
					break;
				}
			}
		}
		return totalPayment;
	}

	public PaymentPlanUI getNextPayment() {
		if (planUI == null || planUI.isEmpty()) {
			return null;
		}
		for (PaymentPlanUI ui : planUI) {
			if (ui.getPayment() == null) {
				return ui;
			}
		}
		return null;
	}

	public BigDecimal getRemainingAmount() {
		BigDecimal amount = new BigDecimal(0);
		if (planUI == null || planUI.isEmpty()) {
			return amount;
		}
		for (PaymentPlanUI ui : planUI) {
			if (ui.getPayment() == null) {
				amount = amount.add(ui.getDetails().getInstallmentAmount());
			}
		}
		return amount;
	}

	public Date getPlanEndDate() {
		if (planUI == null || planUI.isEmpty()) {
			return null;
		}
		Date date = planUI.get(planUI.size() - 1).getPaymentDate();
		return date;
	}

	public String getAgents() {
		Set<Agent> agents = new HashSet<Agent>();
		StringBuffer string = new StringBuffer();
		for (PaymentPlanUI ui : planUI) {
			if (ui.getPayment() != null) {
				agents.add(ui.getPayment().getAgent());
			}
		}
		int i = 0;
		for (Agent agent : agents) {
			string.append(agent.getName());
			if (i != agents.size() - 1) {
				string.append(", ");
			}
		}
		return string.toString();
	}

	public List<PaymentPlanUI> getPlanUI() {
		return planUI;
	}

	public void setPlanUI(List<PaymentPlanUI> planUI) {
		this.planUI = planUI;
	}

	public class PaymentPlanUI implements Comparable<PaymentPlanUI> {
		private Installment details;
		private Payment payment;
		private Date paymentDate;
		private BigDecimal amount;

		public Date getPaymentDate() {
			return paymentDate;
		}

		public void setPaymentDate(Date paymentDate) {
			this.paymentDate = paymentDate;
		}

		public Installment getDetails() {
			return details;
		}

		public void setDetails(Installment details) {
			this.details = details;
		}

		public Payment getPayment() {
			return payment;
		}

		public void setPayment(Payment payment) {
			this.payment = payment;
		}

		public BigDecimal getInstallmentAmount() {
			return amount;
		}

		public void setInstallmentAmount(BigDecimal amount) {
			this.amount = amount;
		}

		public boolean isBeforeToday() {
			if (payment != null) {
				if (payment.getPaymentDate().before(new Date())) {
					return true;
				}
			} else if (getPaymentDate().before(new Date())) {
				return true;
			}
			return false;
		}

		public int compareTo(PaymentPlanUI planUtil) {
			if (this.getPaymentDate().before(planUtil.getPaymentDate())) {
				return -1;
			} else if (this.getPaymentDate().after(planUtil.getPaymentDate())) {
				return 1;
			}
			return 0;
		}
	}

	public Site getSitePurchased() {
		return sitePurchased;
	}

	public PaymentPlan getPaymentPlan() {
		return paymentPlan;
	}
}
