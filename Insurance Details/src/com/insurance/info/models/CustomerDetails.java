package com.insurance.info.models;

<<<<<<< HEAD
import java.sql.Date;
=======
>>>>>>> branch 'master' of https://github.com/rajatsingh8109422965/InsuranceDetails

public class CustomerDetails {

	private String name;
	private String mobileNo;
	private String address;
	private String email;
	private Date dateofIssue;
	private String periodOfInsurance;
	private String typeOfInsurance;
	private Date dateOfExpiryDate;

	public Date getDateOfExpiryDate() {

		return dateOfExpiryDate;
	}

	public void setDateOfExpiryDate(Date dateOfExpiryDate) {
		this.dateOfExpiryDate = dateOfExpiryDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPeriodOfInsurance() {
		return periodOfInsurance;
	}

	public void setPeriodOfInsurance(String periodOfInsurance) {
		this.periodOfInsurance = periodOfInsurance;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateofIssue() {
		return dateofIssue;
	}

	public void setDateofIssue(Date dateofIssue) {
		this.dateofIssue = dateofIssue;
	}

	public String getTypeOfInsurance() {
		return typeOfInsurance;
	}

	public void setTypeOfInsurance(String typeOfInsurance) {
		this.typeOfInsurance = typeOfInsurance;
	}

}
