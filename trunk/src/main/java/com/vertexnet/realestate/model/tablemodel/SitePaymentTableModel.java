package com.vertexnet.realestate.model.tablemodel;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.vertexnet.realestate.bo.PaymentUtility.PaymentPlanUI;
import com.vertexnet.realestatenet.model.bean.Agent;
import com.vertexnet.realestatenet.model.bean.Payment;
import com.vertexnet.realestatenet.model.bean.Site;

public class SitePaymentTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private String[] classNames = {"Installment Date", "Installment Name", "Installment Amount", "Agent", "Payment Date"};
	private List<PaymentPlanUI> utilList;
	private Site sitePurchased;
	private SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
	
	public SitePaymentTableModel(List<PaymentPlanUI> utilList, Site sitePurchased) {
		Collections.sort(utilList);
		this.utilList = utilList;
		this.sitePurchased=sitePurchased;
	}

	public int getColumnCount() {
		return classNames.length;
	}

	public int getRowCount() {
		if(utilList == null) {
			return 0;
		}
		return utilList.size();
	}

	@Override
	public String getColumnName(int column) {
		return classNames[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return super.getColumnClass(columnIndex);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		PaymentPlanUI util = utilList.get(rowIndex);
		switch (columnIndex) {
		case 3: if(util.getPayment() == null) {
					createPayment(util);
				}
				util.getPayment().setAgent((Agent) aValue);
				if (aValue != null) {
					if (((Agent) aValue).getPayments() == null || ((Agent) aValue).getPayments().isEmpty()) {
						((Agent) aValue).setPayments(new HashSet<Payment>());
					}
					((Agent) aValue).getPayments().add(util.getPayment());
				}
				return;
		case 4: if(util.getPayment() == null) {
					createPayment(util);
				}
				util.getPayment().setPaymentDate((Date) aValue);
		}
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		PaymentPlanUI util = utilList.get(rowIndex);
		switch (columnIndex) {
		case 0: return format.format(util.getPaymentDate());
		case 1: return util.getDetails().getInstallmentName();
		case 2: return util.getDetails().getInstallmentAmount();
		case 3: if(util.getPayment() != null) {
					return util.getPayment().getAgent();
				}
		case 4: if(util.getPayment() != null) {
					return util.getPayment().getPaymentDate();
				}
		}
		return null;
	}

	public void setSitePurchased(List<PaymentPlanUI> utilList) {
		this.utilList = utilList;
	}
	
	void createPayment(PaymentPlanUI util) {
		Payment payment = new Payment();
		payment.setInstallment(util.getDetails());
		payment.setSite(sitePurchased);
		sitePurchased.getPayments().add(payment);
		util.setPayment(payment);
	}
}
