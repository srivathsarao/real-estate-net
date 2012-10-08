/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertexnet.realestate.view.dialog;

import static com.vertexnet.realestate.constants.UIConstants.PAYMENT_PLAN;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.vertexnet.realestate.dao.HibernateUtil;
import com.vertexnet.realestate.interfaces.Controller;
import com.vertexnet.realestate.interfaces.DefaultDialog;
import com.vertexnet.realestate.model.RealEstateUtilityModel;
import com.vertexnet.realestate.model.tablemodel.InstallmentsTableModel;
import com.vertexnet.realestate.view.utility.MonthYearCellEditor;
import com.vertexnet.realestate.view.utility.MonthYearCellRenderer;
import com.vertexnet.realestatenet.model.bean.Installment;
import com.vertexnet.realestatenet.model.bean.Layout;
import com.vertexnet.realestatenet.model.bean.PaymentPlan;

/**
 * 
 * @author Srivathsa
 */
public class PaymentPlanDialog extends DefaultDialog {

	private static final long serialVersionUID = 1L;

	private PaymentPlan paymentPlan;
	private Controller controller;
	private InstallmentsTableModel tableModel = new InstallmentsTableModel();
	private List<Layout> layouts;

	/**
	 * Creates new form PaymentPlanDialog
	 */
	public PaymentPlanDialog(Controller controller, PaymentPlan paymentPlan, List<Layout> layouts) {
		initComponents();

		this.paymentPlan = paymentPlan;
		this.controller = controller;
		this.layouts = layouts;
		setupDialog();
	}

	public PaymentPlanDialog(Controller controller, List<Layout> layouts) {
		this(controller, new PaymentPlan(), layouts);
	}

	@Override
	public void setupDialog() {
		tableModel.setPaymentPlan(paymentPlan);

		// Set the Panel editor/Renderer on the 1st visible column
		TableColumn col = installmentsTable.getColumnModel().getColumn(3);
		col.setCellEditor(new MonthYearCellEditor());
		col.setCellRenderer(new MonthYearCellRenderer());

		// Set Column width for 3 columns
		installmentsTable.getColumnModel().getColumn(0).setMaxWidth(45);
		installmentsTable.getColumnModel().getColumn(1).setMinWidth(200);
		installmentsTable.getColumnModel().getColumn(3).setMinWidth(200);

		// Sort the Values as per Column 1
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel);
		List<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>();
		sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
		sorter.setSortKeys(sortKeys);
		installmentsTable.setRowSorter(sorter);

		// Set Column edit termination
		installmentsTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		installmentsTable.repaint();

		planNameTextField.setText(paymentPlan.getPlanName());

		DefaultComboBoxModel model = new DefaultComboBoxModel(layouts.toArray());
		layoutComboBox.setModel(model);
		if (paymentPlan.getLayout() != null) {
			layoutComboBox.setSelectedItem(paymentPlan.getLayout());
		}

		HibernateUtil.getSession().evict(this.paymentPlan);
	}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        planNameLabel = new javax.swing.JLabel();
        planNameTextField = new javax.swing.JTextField();
        layoutLabel = new javax.swing.JLabel();
        layoutComboBox = new javax.swing.JComboBox();
        installmentDetailsLabel = new javax.swing.JLabel();
        installmentsScrollPane = new javax.swing.JScrollPane();
        installmentsTable = new com.vertexnet.realestate.view.utility.DefaultTable();
        deleteButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        planNameLabel.setText("Plan Name : ");

        planNameTextField.setMaximumSize(null);
        planNameTextField.setMinimumSize(new java.awt.Dimension(200, 20));
        planNameTextField.setPreferredSize(null);

        layoutLabel.setText("Layout : ");

        layoutComboBox.setMaximumSize(null);
        layoutComboBox.setMinimumSize(new java.awt.Dimension(200, 20));
        layoutComboBox.setPreferredSize(null);
        layoutComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                layoutComboBoxActionPerformed(evt);
            }
        });

        installmentDetailsLabel.setText("Installment Details : ");

        installmentsScrollPane.setMaximumSize(null);
        installmentsScrollPane.setMinimumSize(null);

        installmentsTable.setModel(tableModel);
        installmentsScrollPane.setViewportView(installmentsTable);

        deleteButton.setText("Delete");
        deleteButton.setMargin(null);
        deleteButton.setMaximumSize(null);
        deleteButton.setMinimumSize(null);
        deleteButton.setPreferredSize(null);
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        addButton.setText("Add");
        addButton.setMargin(null);
        addButton.setMaximumSize(null);
        addButton.setMinimumSize(null);
        addButton.setPreferredSize(null);
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        closeButton.setText("Close");
        closeButton.setMargin(null);
        closeButton.setMaximumSize(null);
        closeButton.setMinimumSize(null);
        closeButton.setPreferredSize(null);
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        saveButton.setText("Save");
        saveButton.setMargin(null);
        saveButton.setMaximumSize(null);
        saveButton.setMinimumSize(null);
        saveButton.setName(PAYMENT_PLAN.getSave());
        saveButton.setPreferredSize(null);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(624, Short.MAX_VALUE)
                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(planNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(layoutComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(planNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(layoutLabel, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(476, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(installmentDetailsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(installmentsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(planNameLabel)
                    .addComponent(planNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(layoutLabel)
                    .addComponent(layoutComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(installmentDetailsLabel)
                    .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(installmentsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {addButton, deleteButton, installmentDetailsLabel});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {planNameLabel, planNameTextField});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {layoutComboBox, layoutLabel});

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_addButtonActionPerformed
		Installment installment = new Installment();
		if (paymentPlan.getInstallments().contains(installment)) {
			return;
		}
		installment.setPaymentPlan(paymentPlan);
		paymentPlan.getInstallments().add(installment);
		tableModel.fireTableDataChanged();
	}// GEN-LAST:event_addButtonActionPerformed

	private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_deleteButtonActionPerformed
		Map<Installment, Boolean> selectionMap = tableModel.getSelectionMap();
		if (selectionMap.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please select row/s to Delete");
			return;
		}
		Iterator<Entry<Installment, Boolean>> iterator = selectionMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Installment installment = iterator.next().getKey();
			paymentPlan.getInstallments().remove(installment);
		}
		selectionMap.clear();
		tableModel.fireTableDataChanged();
	}// GEN-LAST:event_deleteButtonActionPerformed

	private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_closeButtonActionPerformed
		RealEstateUtilityModel.paymentPlans.remove(this.paymentPlan);
		if (this.paymentPlan.getPlanId() != null) {
			this.paymentPlan = (PaymentPlan) HibernateUtil.getSession().get(PaymentPlan.class, this.paymentPlan.getPlanId());
			RealEstateUtilityModel.paymentPlans.add(this.paymentPlan);
		}
		this.setVisible(false);
		this.dispose();
	}// GEN-LAST:event_closeButtonActionPerformed

	private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_saveButtonActionPerformed
		if (planNameTextField.getText() == null || planNameTextField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please Input Payment Plan Name");
			return;
		}
		if (paymentPlan.getInstallments() == null || paymentPlan.getInstallments().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please Add Installments");
			return;
		}
		for (Installment details : paymentPlan.getInstallments()) {
			if (details.getInstallmentName() == null || details.getInstallmentName().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please Add Installment Name");
				return;
			}
			if (details.getInstallmentAmount() == null || details.getInstallmentAmount().equals(0)) {
				JOptionPane.showMessageDialog(this, "Please Add Installment Amount");
				return;
			}
			if (details.getNumRepeat() == 0) {
				JOptionPane.showMessageDialog(this, "Please Add Number of Times Payment should Repeat");
				return;
			}
		}
		paymentPlan.setPlanName(planNameTextField.getText());
		paymentPlan.setLayout((Layout) layoutComboBox.getSelectedItem());
		((Layout) layoutComboBox.getSelectedItem()).getPaymentPlans().add(paymentPlan);
		controller.handleEvent(evt);
	}// GEN-LAST:event_saveButtonActionPerformed

	private void layoutComboBoxActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_layoutComboBoxActionPerformed
		paymentPlan.setLayout((Layout) layoutComboBox.getSelectedItem());
	}// GEN-LAST:event_layoutComboBoxActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton addButton;
	private javax.swing.JButton closeButton;
	private javax.swing.JButton deleteButton;
	private javax.swing.JLabel installmentDetailsLabel;
	private javax.swing.JScrollPane installmentsScrollPane;
	private com.vertexnet.realestate.view.utility.DefaultTable installmentsTable;
	private javax.swing.JComboBox layoutComboBox;
	private javax.swing.JLabel layoutLabel;
	private javax.swing.JLabel planNameLabel;
	private javax.swing.JTextField planNameTextField;
	private javax.swing.JButton saveButton;

	// End of variables declaration//GEN-END:variables

	public Layout getLayOut() {
		return ((Layout) layoutComboBox.getSelectedItem());
	}

	public void showSaveConfirmation() {
		JOptionPane.showMessageDialog(this, "Payment Plan Created Successfully");
		this.setVisible(false);
		this.dispose();
	}
}
