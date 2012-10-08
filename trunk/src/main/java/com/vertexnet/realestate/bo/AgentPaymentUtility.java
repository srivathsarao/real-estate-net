package com.vertexnet.realestate.bo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.vertexnet.realestate.bo.PaymentUtility.PaymentPlanUI;
import com.vertexnet.realestatenet.model.bean.Agent;

public class AgentPaymentUtility {

	private List<TotalPaymentUtility> totalPayments;
	private Agent agent;

	public AgentPaymentUtility(Agent agent, List<TotalPaymentUtility> totalPayments) {
		this.agent = agent;
		this.totalPayments = totalPayments;
	}

	public BigDecimal getTotalPaymentFromTill(Date fromDate, Date toDate) {
		BigDecimal payments = new BigDecimal(0);
		for (TotalPaymentUtility totalPayment : totalPayments) {
			for (PaymentUtility payment : totalPayment.getPaymentUtilList()) {
				for (PaymentPlanUI planUI : payment.getPlanUI()) {
					if (planUI.getPayment() != null && planUI.getPayment().getAgent().equals(agent)) {
						if (planUI.getPaymentDate().after(fromDate) && planUI.getPaymentDate().before(toDate)) {
							BigDecimal amount = planUI.getInstallmentAmount().multiply(new BigDecimal(agent.getCommisionPercent()));
							amount = amount.divide(new BigDecimal(100));
							payments.add(amount);
						}
					}
				}
			}
		}
		return payments;
	}

	public BigDecimal getTotalPaymentTillDate() {
		BigDecimal payments = new BigDecimal(0);
		for (TotalPaymentUtility totalPayment : totalPayments) {
			for (PaymentUtility payment : totalPayment.getPaymentUtilList()) {
				for (PaymentPlanUI planUI : payment.getPlanUI()) {
					if (planUI.getPayment() != null && planUI.getPayment().getAgent().equals(agent)) {
						if (planUI.getPaymentDate().before(new Date())) {
							BigDecimal amount = planUI.getInstallmentAmount().multiply(new BigDecimal(agent.getCommisionPercent()));
							amount = amount.divide(new BigDecimal(100));
							payments = payments.add(amount);
						}
					}
				}
			}
		}
		return payments;
	}

	public Agent getAgent() {
		return agent;
	}
}
