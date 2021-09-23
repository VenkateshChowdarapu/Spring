package com.dbs.web.beans.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dbs.web.beans.Loan;

public interface LoanRepository  extends CrudRepository<Loan, String>{

	//loan.getEmployee().getEmployeeId()
	public List<Loan> findAllByEmployeeEmployeeId(String empid);
	
	
}
