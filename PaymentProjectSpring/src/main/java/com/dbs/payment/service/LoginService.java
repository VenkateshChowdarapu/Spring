package com.dbs.payment.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.payment.model.Bank;
import com.dbs.payment.model.CustomerUser;
import com.dbs.payment.model.Transaction;
import com.dbs.payment.repo.CustomerUserRepository;

@Service
public class LoginService {
	@Autowired
	CustomerUserRepository repo;
	public CustomerUser getAllBycustomerId(int id){
		 try { 
	            Optional<CustomerUser> c=this.repo.findById(id);
	            return c.orElseThrow(()->{
	                return new EntityNotFoundException("Bank with "+id + " does not exist");
	            });
	            }
	        catch(IllegalArgumentException iae) {
	            return null;
	        }
	        }

}
