/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertexnet.realestate.view.dialog;

import static com.vertexnet.realestate.constants.CustomerConstants.*;
import static com.vertexnet.realestate.constants.UIConstants.CUSTOMER_ID_ADD;

import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.vertexnet.realestate.controller.CustomerDetailsController;
import com.vertexnet.realestate.dao.HibernateUtil;
import com.vertexnet.realestate.interfaces.Controller;
import com.vertexnet.realestate.interfaces.DefaultDialog;
import com.vertexnet.realestate.interfaces.MainView;
import com.vertexnet.realestate.interfaces.View;
import com.vertexnet.realestate.model.RealEstateUtilityModel;
import com.vertexnet.realestate.model.tablemodel.SitesTableModel;
import com.vertexnet.realestate.utility.PersistanceUtility;
import com.vertexnet.realestate.view.dialog.testing.MyTableModelListener;
import com.vertexnet.realestate.view.utility.CalenderCellEditor;
import com.vertexnet.realestate.view.utility.CalenderCellRenderer;
import com.vertexnet.realestate.view.utility.ComboBoxCellRenderer;
import com.vertexnet.realestate.view.utility.LayoutComboBoxCellEditor;
import com.vertexnet.realestate.view.utility.PaymentPlanComboBoxCellEditor;
import com.vertexnet.realestatenet.model.bean.Application;
import com.vertexnet.realestatenet.model.bean.Area;
import com.vertexnet.realestatenet.model.bean.Customer;
import com.vertexnet.realestatenet.model.bean.Layout;
import com.vertexnet.realestatenet.model.bean.Photo;
import com.vertexnet.realestatenet.model.bean.Site;

/**
 *
 * @author Srivathsa
 */
public class CustomerDialog extends DefaultDialog implements View {

	private static final long serialVersionUID = 1L;
	private CustomerDetailsController controller;
	private Customer customer;
	private List<Layout> layouts;
	private SitesTableModel tableModel = new SitesTableModel();
	
	/**
     * Creates new form CustomerDetailsPanel
	 * @param controller 
     */
    public CustomerDialog(CustomerDetailsController controller) {
    	this(controller, new Customer());
    }
    
	public CustomerDialog(CustomerDetailsController controllerr, Customer customer) {
        initComponents();

        this.customer = customer;

		this.controller = controllerr;
		this.layouts = RealEstateUtilityModel.layouts;
		setupDialog();
	}
    
    private void setUpAreas() {
    	DefaultComboBoxModel model = new DefaultComboBoxModel();
    	for(Area area : RealEstateUtilityModel.areas) {
    		model.addElement(area);
    	}
    	areasComboBox.setModel(model);
    	if(customer.getArea() != null) {
    		model.setSelectedItem(customer.getArea());
    	} else {
        	areasComboBox.setSelectedIndex(-1);
    	}
    }
    
	private void setupRenderer() {
		JComboBox jComboBox = new JComboBox();
		for (Layout layout : layouts) {
			jComboBox.addItem(layout);
		}
		JComboBox comboBox = new JComboBox();

		sitesTable.getColumnModel().getColumn(1).setCellRenderer(new ComboBoxCellRenderer());
		sitesTable.getColumnModel().getColumn(1).setCellEditor(new LayoutComboBoxCellEditor(jComboBox, comboBox));
		sitesTable.getColumnModel().getColumn(3).setCellRenderer(new ComboBoxCellRenderer());
		sitesTable.getColumnModel().getColumn(3).setCellEditor(new PaymentPlanComboBoxCellEditor(comboBox));

		sitesTable.getModel().addTableModelListener(new MyTableModelListener(sitesTable));

		sitesTable.getColumnModel().getColumn(5).setCellEditor(new CalenderCellEditor());
		sitesTable.getColumnModel().getColumn(5).setCellRenderer(new CalenderCellRenderer());
	}

	@Override
	public void setupDialog() {
		firstNameTextField.setText(this.customer.getFirstName());
		fatherNameTextField.setText(this.customer.getFatherName());
		lastNameTextField.setText(this.customer.getLastName());
		emailTextField.setText(this.customer.getEmailId());
		phoneTextField.setText(this.customer.getPhone());
		mobileTextField.setText(this.customer.getMobile());
		if ( this.customer.getMembershipId() != 0 ) {
			idTextField.setText(new Integer(this.customer.getMembershipId()).toString());
		}
		
		addressTextArea.setText(this.customer.getAddress());
		
		Photo photoObject = this.customer.getPhoto();
		if(photoObject != null && photoObject.getPhoto() != null) {
			photoLabel.setIcon((Icon) PersistanceUtility.getObject(photoObject.getPhoto()));
		}
       
		Application applicationObject = this.customer.getApplication();
		if(applicationObject == null || applicationObject.getApplication() == null) {
			applicationAddLabel.setText("Add Application");
		}
		
        photoLabel.setEnabled(true);
       
        tableModel.setCustomer(customer);
        sitesTable.setModel(tableModel);
        sitesTable.repaint();

        setupRenderer();
        setUpAreas();
        
		HibernateUtil.getSession().evict(this.customer);
	}
	
	/**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        firstNameLabel = new javax.swing.JLabel();
        firstNameTextField = new javax.swing.JTextField();
        fatherNameLabel = new javax.swing.JLabel();
        fatherNameTextField = new javax.swing.JTextField();
        lastNameLabel = new javax.swing.JLabel();
        lastNameTextField = new javax.swing.JTextField();
        emailLabel = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        phoneLabel = new javax.swing.JLabel();
        phoneTextField = new javax.swing.JTextField();
        mobileLabel = new javax.swing.JLabel();
        mobileTextField = new javax.swing.JTextField();
        pinLabel = new javax.swing.JLabel();
        photoLabel = new javax.swing.JLabel();
        addressLabel = new javax.swing.JLabel();
        addressScrollPane = new javax.swing.JScrollPane();
        addressTextArea = new javax.swing.JTextArea();
        closeButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        sitesTable = new com.vertexnet.realestate.view.utility.DefaultTable();
        sitesLabel = new javax.swing.JLabel();
        addSiteButton = new javax.swing.JButton();
        deleteSiteButton = new javax.swing.JButton();
        idLabel = new javax.swing.JLabel();
        idTextField = new javax.swing.JTextField();
        areasComboBox = new javax.swing.JComboBox();
        applicationAddLabel = new javax.swing.JLabel();

        firstNameLabel.setText("First Name : ");

        fatherNameLabel.setText("Fathers's Name : ");

        lastNameLabel.setText("Last Name : ");

        emailLabel.setText("Email Id : ");

        phoneLabel.setText("Phone : ");

        mobileLabel.setText("Mobile : ");

        pinLabel.setText("Area : ");

        photoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        photoLabel.setText("Click to Add");
        photoLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        photoLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                photoLabelMouseClicked(evt);
            }
        });

        addressLabel.setText("Address : ");

        addressTextArea.setColumns(20);
        addressTextArea.setRows(2);
        addressScrollPane.setViewportView(addressTextArea);

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        saveButton.setText("Save");
        saveButton.setName(CUSTOMER_TABLE);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        sitesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        sitesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sitesTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(sitesTable);

        sitesLabel.setText("Sites : ");

        addSiteButton.setText("Add");
        addSiteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSiteButtonActionPerformed(evt);
            }
        });

        deleteSiteButton.setText("Delete");
        deleteSiteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteSiteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 882, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(sitesLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addSiteButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteSiteButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addSiteButton)
                    .addComponent(deleteSiteButton)
                    .addComponent(sitesLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        idLabel.setText("Membership Id : ");

        idTextField.setName(CUSTOMER_ID_ADD.getValue());
        idTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                idTextFieldFocusLost(evt);
            }
        });

        applicationAddLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        applicationAddLabel.setText("Application Added");
        applicationAddLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        applicationAddLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                applicationAddLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addressLabel)
                            .addComponent(emailLabel)
                            .addComponent(mobileLabel)
                            .addComponent(lastNameLabel)
                            .addComponent(firstNameLabel))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lastNameTextField)
                                    .addComponent(emailTextField)
                                    .addComponent(mobileTextField))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(idLabel)
                                    .addComponent(fatherNameLabel)
                                    .addComponent(phoneLabel)
                                    .addComponent(pinLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(idTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                                    .addComponent(phoneTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                                    .addComponent(fatherNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                                    .addComponent(areasComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(addressScrollPane))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(photoLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(applicationAddLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(saveButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(closeButton)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {emailTextField, fatherNameTextField, firstNameTextField, idTextField, lastNameTextField, mobileTextField, phoneTextField});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(photoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(applicationAddLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(firstNameLabel)
                            .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lastNameLabel)
                            .addComponent(lastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fatherNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fatherNameLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(emailLabel)
                            .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(phoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(phoneLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mobileLabel)
                            .addComponent(mobileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pinLabel)
                            .addComponent(areasComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addressLabel)
                            .addComponent(addressScrollPane))))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(closeButton)
                    .addComponent(saveButton))
                .addGap(16, 16, 16))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {emailTextField, fatherNameTextField, firstNameTextField, lastNameTextField, mobileTextField, phoneTextField});

    }// </editor-fold>//GEN-END:initComponents

    private void addSiteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSiteButtonActionPerformed
    	Site site = new Site();
		if (customer.getSites().contains(site)) {
			return;
		}
		site.setCustomer(customer);
		customer.getSites().add(site);
		tableModel.fireTableDataChanged();
    }//GEN-LAST:event_addSiteButtonActionPerformed

    private void deleteSiteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteSiteButtonActionPerformed
		Map<Site, Boolean> selectionMap = tableModel.getSelectionMap();
		if (selectionMap.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please select row/s to Delete");
			return;
		}
		Iterator<Entry<Site, Boolean>> iterator = selectionMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Site site = iterator.next().getKey();
			customer.getSites().remove(site);
		}
		selectionMap.clear();
		tableModel.fireTableDataChanged();
    }//GEN-LAST:event_deleteSiteButtonActionPerformed

	private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_saveButtonActionPerformed
		StringBuffer buffer = new StringBuffer();
		String id = idTextField.getText();
		if (!id.isEmpty() && !id.equals("0")) {
			if (id.matches("[0-9]{1,9}")) {
				customer.setMembershipId(Integer.parseInt(id));
			} else {
				buffer.append("Id should be A Positive Number\n");
			}
		} else {
			buffer.append("Please Input Id\n");
		}
		if (!firstNameTextField.getText().isEmpty()) {
			customer.setFirstName(firstNameTextField.getText());
		} else {
			buffer.append("Please Input First Name\n");
		}
		if (!lastNameTextField.getText().isEmpty()) {
			customer.setLastName(lastNameTextField.getText());
		} else {
			buffer.append("Please Input Last Name\n");
		}
		if (!fatherNameTextField.getText().isEmpty()) {
			customer.setFatherName(fatherNameTextField.getText());
		} else {
			buffer.append("Please Input Father Name\n");
		}
		if (!emailTextField.getText().isEmpty()) {
			customer.setEmailId(emailTextField.getText());
		} else {
			buffer.append("Please Input Email\n");
		}
		if (!phoneTextField.getText().isEmpty()) {
			customer.setPhone(phoneTextField.getText());
		} else {
			buffer.append("Please Input Phone Number\n");
		}
		if (!mobileTextField.getText().isEmpty()) {
			customer.setMobile(mobileTextField.getText());
		} else {
			buffer.append("Please Input Mobile Number\n");
		}
		if (!addressTextArea.getText().isEmpty()) {
			customer.setAddress(addressTextArea.getText());
		} else {
			buffer.append("Please Input Address\n");
		}
		if (areasComboBox.getSelectedItem() != null && !areasComboBox.getSelectedItem().equals(new Area())) {
			customer.setArea(((Area) areasComboBox.getSelectedItem()));
		} else {
			buffer.append("Please Input Area\n");
		}
		if (!buffer.toString().isEmpty()) {
			JOptionPane.showMessageDialog(this, buffer.toString());
			return;
		}

		if (!customer.getSites().isEmpty()) {
			for (Site site : customer.getSites()) {
				StringBuffer sitesBuffer = new StringBuffer();
				if (site.getPurchaseId() <= 0) {
					sitesBuffer.append("Plot Number should be a positive number\n");
				}
				if (site.getLayout() == null) {
					sitesBuffer.append("Site Should have a Layout\n");
				}
				if (site.getPaymentPlan() == null) {
					sitesBuffer.append("Site Should have a Payment Plan\n");
				}
				if (site.getPaymentStartDate() == null) {
					sitesBuffer.append("Site Should have a Payment Date\n");
				}
				if(!sitesBuffer.toString().isEmpty()) {
					JOptionPane.showMessageDialog(this, sitesBuffer);
					return;
				}
			}
		}
		
		controller.handleEvent(evt);
	}// GEN-LAST:event_saveButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
		RealEstateUtilityModel.customers.remove(this.customer);
		if (this.customer.getCustomerId() != null) {
			this.customer = (Customer) HibernateUtil.getSession().get(Customer.class, this.customer.getCustomerId());
			RealEstateUtilityModel.customers.add(this.customer);
		}
		this.setVisible(false);
		this.dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    private void photoLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_photoLabelMouseClicked
		if(!saveButton.isVisible()) {
			return;
		}
    	try {
	    	File file = PersistanceUtility.getFile(this);
	    	if(file == null) {
	    		return;
	    	}
	    	InputStream is = new BufferedInputStream(new FileInputStream(file));
	    	Image resizeImage = PersistanceUtility.createResizedCopy(ImageIO.read(is), 155, 172, true);
	    	photoLabel.setIcon(new ImageIcon(resizeImage));
	    	
	    	Photo photoObject = customer.getPhoto();
	    	if(photoObject == null) {
	    		photoObject = new Photo();
	    		customer.setPhoto(photoObject);
	    		photoObject.setCustomer(customer);
	    	}
	    	//Set the Photo to Profile
	    	if(photoLabel.getIcon() != null) {
	    		photoObject.setPhoto(PersistanceUtility.getBytes(photoLabel.getIcon()));
	    	}
	    	
		} catch (IOException e) {
			e.printStackTrace();
		}
    }//GEN-LAST:event_photoLabelMouseClicked

    private void sitesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sitesTableMouseClicked
    }//GEN-LAST:event_sitesTableMouseClicked

    private void idTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_idTextFieldFocusLost
    	controller.handleEvent(evt);
    }//GEN-LAST:event_idTextFieldFocusLost

	private void applicationAddLabelMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_applicationAddLabelMouseClicked
    	Application applicationObject = customer.getApplication();
		if (applicationObject == null) {
			applicationObject = new Application();
			customer.setApplication(applicationObject);
			applicationObject.setCustomer(customer);
		}
		ApplicationDialog dialog = new ApplicationDialog(applicationObject);
		dialog.setModal(true);
		dialog.setVisible(true);
		if(applicationObject != null && applicationObject.getApplication() != null) {
			applicationAddLabel.setText("Application Added");
		}
	}// GEN-LAST:event_applicationAddLabelMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addSiteButton;
    private javax.swing.JLabel addressLabel;
    private javax.swing.JScrollPane addressScrollPane;
    private javax.swing.JTextArea addressTextArea;
    private javax.swing.JLabel applicationAddLabel;
    private javax.swing.JComboBox areasComboBox;
    private javax.swing.JButton closeButton;
    private javax.swing.JButton deleteSiteButton;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JLabel fatherNameLabel;
    private javax.swing.JTextField fatherNameTextField;
    private javax.swing.JLabel firstNameLabel;
    private javax.swing.JTextField firstNameTextField;
    private javax.swing.JLabel idLabel;
    private javax.swing.JTextField idTextField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JTextField lastNameTextField;
    private javax.swing.JLabel mobileLabel;
    private javax.swing.JTextField mobileTextField;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JTextField phoneTextField;
    private javax.swing.JLabel photoLabel;
    private javax.swing.JLabel pinLabel;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel sitesLabel;
    private com.vertexnet.realestate.view.utility.DefaultTable sitesTable;
    // End of variables declaration//GEN-END:variables

	public void setRealEstateFrame(MainView mainView) {
	}

	public void setController(Controller controller) {
		this.controller = (CustomerDetailsController) controller;
	}
	

	public Customer getCustomer() {
		return customer;
	}

	public void showSaveConfirmation() {
		JOptionPane.showMessageDialog(this, "Customer Saved Successfully");
	}

	public void showCustomerExistDialog() {
		JOptionPane.showMessageDialog(this, "Customer with Same Membership Id already exists");
	}

	public void showCustomerExistDialog(List<Site> newValue) {
		HashSet<Integer> set = new HashSet<Integer>();
		for(Site site: newValue) {
			set.add(site.getPurchaseId());
		}
		StringBuffer message = new StringBuffer("Plot Number ");
		for(Integer id: set) {
			message.append(id + " ");
		}
		message.append("already exists");
		JOptionPane.showMessageDialog(this, message.toString());
	}
}
