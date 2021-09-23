package com.dbs.web.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.dbs.web.constants.LoanConstants;


@Entity
public class Loan implements Serializable{

	@Id
	private String loanId;
	private String loanType;
	private double loanAmount;
	private double interestRate;
	private double period;
	@OneToOne
	@JoinColumn(name="Customer_Email")
	private Customer customer;
	@OneToOne
	@JoinColumn(name="employeeId")
	private Employee employee;

	@ManyToMany()
	@JoinTable(
			name= "LOAN_COLLATERAL",
			joinColumns = { @JoinColumn(name = "loanid") }, 
			inverseJoinColumns = { @JoinColumn(name = "collateralId") }
			)
	private List<Collateral> collaterals;
	private boolean isApproved = false;
	private String remarks;

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public String getLoanId() {
		return loanId;
	}


	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public double getPeriod() {
		return period;
	}

	public void setPeriod(double period) {
		this.period = period;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public List<Collateral> getCollaterals() {
		return collaterals;
	}


	public void setCollaterals(List<Collateral> collaterals) {
		this.collaterals = collaterals;
	}


	@Override
	public String toString() {
		return "Loan [loanId=" + loanId + ", loanType=" + loanType + ", loanAmount=" + loanAmount + ", interestRate="
				+ interestRate + ", period=" + period + ", customer=" + customer + ", employee=" + employee
				+ ", collaterals=" + collaterals + ", isApproved=" + isApproved + ", remarks=" + remarks + "]";
	}


}
