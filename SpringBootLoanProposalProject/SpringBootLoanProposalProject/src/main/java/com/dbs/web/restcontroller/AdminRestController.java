package com.dbs.web.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.web.beans.Collateral;
import com.dbs.web.beans.Customer;
import com.dbs.web.beans.Employee;
import com.dbs.web.response.ResponsePage;
import com.dbs.web.service.CollateralService;
import com.dbs.web.service.CustomerService;
import com.dbs.web.service.EmployeeService;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private EmployeeService employeeservice;
	
	@Autowired
	private CollateralService collateralService;
	
	@PostMapping("/emp")
	public ResponseEntity<ResponsePage> insertEmployee(
			@RequestBody Employee employee)
	{
		try {
			this.employeeservice.addEmployee(employee);
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponsePage(201, "Employee inserted"));
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponsePage(400, e.getMessage()));
		}
	}
	@PostMapping("/coll")
	public ResponseEntity<ResponsePage> insertColateral(
			@RequestBody Collateral collateral)
	{
		try {
			this.collateralService.addCollateral(collateral);
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponsePage(201, "Collateral inserted"));
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponsePage(400, e.getMessage()));
		}
	}
	@PostMapping("/customer")
	public ResponseEntity<ResponsePage> insertCustomer(
			@RequestBody Customer customer)
	{
		try {
			this.customerService.addCustomer(customer);
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponsePage(201, "Customer inserted"));
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponsePage(400, e.getMessage()));
		}
	}
	@PutMapping("/customer")
	public ResponseEntity<ResponsePage> updateCustomer(
			@RequestBody Customer customer)
	{
		try {
			this.customerService.updateCustomer(customer);
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponsePage(201, "Customer updated"));
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponsePage(400, e.getMessage()));
		}
	}
	@GetMapping("/collaterals")
	public List<Collateral> getAllCollaterals() throws Exception
	{
		return this.collateralService.getAllCollaterals();
	}
	
	@GetMapping("/emp/{empid}")
	public ResponseEntity<ResponsePage> approveLoans(@PathVariable String empid)
	{
		try {
			this.employeeservice.approveLoan(empid);
		}
		catch(Exception e )
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponsePage(400, e.getMessage()));
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponsePage(200, "Status Updated"));
	}
	
}
