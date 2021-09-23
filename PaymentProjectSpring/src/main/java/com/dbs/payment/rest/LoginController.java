package com.dbs.payment.rest;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.payment.model.Customer;
import com.dbs.payment.model.CustomerUser;
import com.dbs.payment.repo.CustomerUserRepository;
import com.dbs.payment.service.CustomerService;
import com.dbs.payment.service.LoginService;

@RestController
public class LoginController {
	@Autowired
	LoginService service;
	@GetMapping("/login")
	public ResponseEntity<CustomerUser> login(@RequestParam int un ,@RequestParam String pass)
	{
		try {
	
		
		CustomerUser cu= this.service.getAllBycustomerId(un);
		if(cu.getUserid()==un && cu.getUserpassword().equals(pass)) {
			return ResponseEntity.status(HttpStatus.OK)
                    .body(cu);
		}
		
		
		else { return ResponseEntity.status(HttpStatus.OK)
                .body(null);
		
			
			
		}
		}
		catch(EntityNotFoundException e)
		{
			return ResponseEntity.status(HttpStatus.FORBIDDEN)
					.body(null);
		}
	}

}
