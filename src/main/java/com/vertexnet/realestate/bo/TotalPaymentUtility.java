package com.vertexnet.realestate.bo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.vertexnet.realestate.bo.PaymentUtility.PaymentPlanUI;
import com.vertexnet.realestatenet.model.bean.Customer;
import com.vertexnet.realestatenet.model.bean.Site;

public class TotalPaymentUtility implements Comparable<TotalPaymentUtility> {
	List<PaymentUtility> paymentUtilList;
	private Customer customer;
	
	public TotalPaymentUtility(Customer customer) {
		this.customer = customer;
		paymentUtilList = new ArrayList<PaymentUtility>();
		if (customer.getSites() != null && !customer.getSites().isEmpty()) {
			for (Site sitePurchased : customer.getSites()) {
				PaymentUtility paymentUtility = new PaymentUtility(sitePurchased);
				paymentUtilList.add(paymentUtility);
			}
		}
	}
	
	public Boolean isPaymentPending() {
		if (paymentUtilList == null || paymentUtilList.isEmpty()) {
			return false;
		}
		for (PaymentUtility paymentUtility : paymentUtilList) {
			if (paymentUtility.isPaymentPending()) {
				return true;
			}
		}
		return false;
	}

	public BigDecimal getTotalPaymentFromTill(Date fromDate, Date toDate) {
		BigDecimal futurePendingAmount = new BigDecimal(0);
		if (paymentUtilList == null || paymentUtilList.isEmpty()) {
			return futurePendingAmount;
		}
		for (PaymentUtility paymentUtility : paymentUtilList) {
			futurePendingAmount = futurePendingAmount.add(paymentUtility.getTotalPaymentFromTill(fromDate, toDate));
		}
		return futurePendingAmount;
	}
	
	public BigDecimal getPendingAmountFromTill(Date fromDate, Date toDate) {
		BigDecimal futurePendingAmount = new BigDecimal(0);
		if (paymentUtilList == null || paymentUtilList.isEmpty()) {
			return futurePendingAmount;
		}
		for (PaymentUtility paymentUtility : paymentUtilList) {
			futurePendingAmount = futurePendingAmount.add(paymentUtility.getPendingAmountFromTill(fromDate, toDate));
		}
		return futurePendingAmount;
	}
	
	public BigDecimal getPendingAmountTillDate() {
		BigDecimal futurePendingAmount = new BigDecimal(0);
		if (paymentUtilList == null || paymentUtilList.isEmpty()) {
			return futurePendingAmount;
		}
		for (PaymentUtility paymentUtility : paymentUtilList) {
			futurePendingAmount = futurePendingAmount.add(paymentUtility.getPendingAmountTillDate());
		}
		return futurePendingAmount;
	}
	
	public BigDecimal getAmountPaidTillDate() {
		BigDecimal futurePendingAmount = new BigDecimal(0);
		if (paymentUtilList == null || paymentUtilList.isEmpty()) {
			return futurePendingAmount;
		}
		for (PaymentUtility paymentUtility : paymentUtilList) {
			futurePendingAmount = futurePendingAmount.add(paymentUtility.getAmountPaidTillDate());
		}
		return futurePendingAmount;
	}
	
	public BigDecimal getAmountPaidFromTill(Date fromDate, Date toDate) {
		BigDecimal futurePendingAmount = new BigDecimal(0);
		if (paymentUtilList == null || paymentUtilList.isEmpty()) {
			return futurePendingAmount;
		}
		for (PaymentUtility paymentUtility : paymentUtilList) {
			futurePendingAmount = futurePendingAmount.add(paymentUtility.getAmountPaidFromTill(fromDate, toDate));
		}
		return futurePendingAmount;
	}
	
	public BigDecimal getFuturePendingAmount() {
		BigDecimal futurePendingAmount = new BigDecimal(0);
		if (paymentUtilList == null || paymentUtilList.isEmpty()) {
			return futurePendingAmount;
		}
		for (PaymentUtility paymentUtility : paymentUtilList) {
			futurePendingAmount = futurePendingAmount.add(paymentUtility.getFuturePendingAmount());
		}
		return futurePendingAmount;
	}
	
	public BigDecimal getFuturePendingAmountFromTill(Date fromDate, Date toDate) {
		BigDecimal futurePendingAmount = new BigDecimal(0);
		if (paymentUtilList == null || paymentUtilList.isEmpty()) {
			return futurePendingAmount;
		}
		for (PaymentUtility paymentUtility : paymentUtilList) {
			futurePendingAmount = futurePendingAmount.add(paymentUtility.getFuturePendingAmountFromTill(fromDate, toDate));
		}
		return futurePendingAmount;
	}
	
	public Date getLastPaymentDueDate() {
		if (paymentUtilList == null || paymentUtilList.isEmpty()) {
			return null;
		}
		List<PaymentPlanUI> paymentUIList = new ArrayList<PaymentPlanUI>();
		for (PaymentUtility paymentUtility : paymentUtilList) {
			PaymentPlanUI nextPayment = paymentUtility.getNextPayment();
			if (nextPayment != null) {
				paymentUIList.add(nextPayment);
			}
		}
		Collections.sort(paymentUIList);
		if (!paymentUIList.isEmpty()) {
			return paymentUIList.get(0).getPaymentDate();
		}
		return null;
	}
	
	public List<PaymentUtility> getPaymentUtilList() {
		return paymentUtilList;
	}

	public Customer getCustomer() {
		return customer;
	}

	public int compareTo(TotalPaymentUtility totalPaymentUtility) {
		if (this.getLastPaymentDueDate().before(totalPaymentUtility.getLastPaymentDueDate())) {
			return -1;
		} else if (this.getLastPaymentDueDate().after(totalPaymentUtility.getLastPaymentDueDate())) {
			return 1;
		}
		return 0;
	}
}
