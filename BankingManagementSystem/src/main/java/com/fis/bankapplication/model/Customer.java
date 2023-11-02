package com.fis.bankapplication.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {

	@Id
	private int accNo;
	private String custName;
	private long mobileNo;
	private String emailId;
	private long aadharNo;
	private String dob;
	private String residentialAdd;
	private String permanentAdd;
	private String accType;

	public int getAccNo() {
		return accNo;
	}

	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public long getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(long aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getResidentialAdd() {
		return residentialAdd;
	}

	public void setResidentialAdd(String residentialAdd) {
		this.residentialAdd = residentialAdd;
	}

	public String getPermanentAdd() {
		return permanentAdd;
	}

	public void setPermanentAdd(String permanentAdd) {
		this.permanentAdd = permanentAdd;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	@Override
	public String toString() {
		return "Customer [accNo=" + accNo + ", custName=" + custName + ", mobileNo=" + mobileNo + ", emailId=" + emailId
				+ ", aadharNo=" + aadharNo + ", dob=" + dob + ", residentialAdd=" + residentialAdd + ", permanentAdd="
				+ permanentAdd + ", accType=" + accType + "]";
	}

	public Customer(int accNo, String custName, long mobileNo, String emailId, long aadharNo, String dob,
			String residentialAdd, String permanentAdd, String accType) {
		super();
		this.accNo = accNo;
		this.custName = custName;
		this.mobileNo = mobileNo;
		this.emailId = emailId;
		this.aadharNo = aadharNo;
		this.dob = dob;
		this.residentialAdd = residentialAdd;
		this.permanentAdd = permanentAdd;
		this.accType = accType;
	}

	public Customer() {

	}

}
