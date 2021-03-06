package com.dbs.payment.rest;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.payment.model.Customer;
import com.dbs.payment.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerRestController {
		
	@Autowired
	private CustomerService service;
	@GetMapping("/{cid}")
	public ResponseEntity<Object> getCustomer(@PathVariable String cid)
	
	{
		try
		{
			
		Customer c=this.service.findById(cid);
		return ResponseEntity.status(HttpStatus.OK)
				.body(c);
		
		}
		catch(EntityNotFoundException e)
		{
			System.out.println("error");
			return ResponseEntity.status(HttpStatus.OK)
					.body(" ");
		}
		
	}
	@PostMapping
	public ResponseEntity<String> updateCustomer(@RequestBody Customer customer){
		if(this.service.updateCustomer(customer)) {
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body("product updated with id"+customer.getCustomerid());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("Product not updated with id"+customer.getCustomerid());
	}
	
	

}
