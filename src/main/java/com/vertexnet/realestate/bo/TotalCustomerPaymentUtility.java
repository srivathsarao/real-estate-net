package com.vertexnet.realestate.bo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TotalCustomerPaymentUtility {

	private List<TotalPaymentUtility> totalPaymentUtilityList;

	public TotalCustomerPaymentUtility(List<TotalPaymentUtility> totalPaymentUtilityList) {
		this.totalPaymentUtilityList = totalPaymentUtilityList;
	}

	public BigDecimal getPendingAmountFromTill(Date fromDate, Date toDate) {
		BigDecimal futurePendingAmount = new BigDecimal(0);
		if (totalPaymentUtilityList == null || totalPaymentUtilityList.isEmpty()) {
			return futurePendingAmount;
		}
		for (TotalPaymentUtility totalPaymentUtility : totalPaymentUtilityList) {
			futurePendingAmount = futurePendingAmount.add(totalPaymentUtility.getPendingAmountFromTill(fromDate, toDate));
		}
		return futurePendingAmount;
	}
	
	public BigDecimal getPendingAmountTillDate() {
		BigDecimal futurePendingAmount = new BigDecimal(0);
		if (totalPaymentUtilityList == null || totalPaymentUtilityList.isEmpty()) {
			return futurePendingAmount;
		}
		for (TotalPaymentUtility totalPaymentUtility : totalPaymentUtilityList) {
			futurePendingAmount = futurePendingAmount.add(totalPaymentUtility.getPendingAmountTillDate());
		}
		return futurePendingAmount;
	}
	
	public BigDecimal getAmountPaidTillDate() {
		BigDecimal futurePendingAmount = new BigDecimal(0);
		if (totalPaymentUtilityList == null || totalPaymentUtilityList.isEmpty()) {
			return futurePendingAmount;
		}
		for (TotalPaymentUtility totalPaymentUtility : totalPaymentUtilityList) {
			futurePendingAmount = futurePendingAmount.add(totalPaymentUtility.getAmountPaidTillDate());
		}
		return futurePendingAmount;
	}
	
	public BigDecimal getAmountPaidFromTill(Date fromDate, Date toDate) {
		BigDecimal futurePendingAmount = new BigDecimal(0);
		if (totalPaymentUtilityList == null || totalPaymentUtilityList.isEmpty()) {
			return futurePendingAmount;
		}
		for (TotalPaymentUtility totalPaymentUtility : totalPaymentUtilityList) {
			futurePendingAmount = futurePendingAmount.add(totalPaymentUtility.getAmountPaidFromTill(fromDate, toDate));
		}
		return futurePendingAmount;
	}
	
	public BigDecimal getFuturePendingAmount() {
		BigDecimal futurePendingAmount = new BigDecimal(0);
		if (totalPaymentUtilityList == null || totalPaymentUtilityList.isEmpty()) {
			return futurePendingAmount;
		}
		for (TotalPaymentUtility totalPaymentUtility : totalPaymentUtilityList) {
			futurePendingAmount = futurePendingAmount.add(totalPaymentUtility.getFuturePendingAmount());
		}
		return futurePendingAmount;
	}
	
	public BigDecimal getFuturePendingAmountFromTill(Date fromDate, Date toDate) {
		BigDecimal futurePendingAmount = new BigDecimal(0);
		if (totalPaymentUtilityList == null || totalPaymentUtilityList.isEmpty()) {
			return futurePendingAmount;
		}
		for (TotalPaymentUtility totalPaymentUtility : totalPaymentUtilityList) {
			futurePendingAmount = futurePendingAmount.add(totalPaymentUtility.getFuturePendingAmountFromTill(fromDate, toDate));
		}
		return futurePendingAmount;
	}
}
