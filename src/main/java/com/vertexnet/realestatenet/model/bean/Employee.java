package com.vertexnet.realestatenet.model.bean;

// Generated Jul 14, 2012 3:02:06 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Employee generated by hbm2java
 */
@Entity
@Table(name = "employee", catalog = "real_estate_net", uniqueConstraints = @UniqueConstraint(columnNames = "email_id"))
public class Employee implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer employeeId;
	private String name;
	private String emailId;
	private String password;
	private String address;
	private boolean adminAccess;
	private boolean appAccess;
	private Date startDate;
	private Date endDate;
	private String mobile;
	private String phone;
	private BigDecimal salary;
	private String securityAnswer;
	private Integer questionId;

	public Employee() {
	}

	public Employee(String emailId, String password, boolean adminAccess, boolean appAccess) {
		this.emailId = emailId;
		this.password = password;
		this.adminAccess = adminAccess;
		this.appAccess = appAccess;
	}

	public Employee(String name, String emailId, String password, String address, boolean adminAccess, boolean appAccess, Date startDate,
			Date endDate, String mobile, String phone, BigDecimal salary, String securityAnswer, Integer questionId) {
		this.name = name;
		this.emailId = emailId;
		this.password = password;
		this.address = address;
		this.adminAccess = adminAccess;
		this.appAccess = appAccess;
		this.startDate = startDate;
		this.endDate = endDate;
		this.mobile = mobile;
		this.phone = phone;
		this.salary = salary;
		this.securityAnswer = securityAnswer;
		this.questionId = questionId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "employee_id", unique = true, nullable = false)
	public Integer getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	@Column(name = "name", length = 25)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "email_id", unique = true, nullable = false, length = 25)
	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Column(name = "password", nullable = false, length = 12)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "address")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "admin_access", nullable = false)
	public boolean isAdminAccess() {
		return this.adminAccess;
	}

	public void setAdminAccess(boolean adminAccess) {
		this.adminAccess = adminAccess;
	}

	@Column(name = "app_access", nullable = false)
	public boolean isAppAccess() {
		return this.appAccess;
	}

	public void setAppAccess(boolean appAccess) {
		this.appAccess = appAccess;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date", length = 19)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date", length = 19)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "mobile", length = 12)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "phone", length = 12)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "salary")
	public BigDecimal getSalary() {
		return this.salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	@Column(name = "security_answer", length = 25)
	public String getSecurityAnswer() {
		return this.securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	@Column(name = "question_id")
	public Integer getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
