package com.dbs.web.mvc;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import com.dbs.web.beans.Customer;
import com.dbs.web.beans.User;
import com.dbs.web.constants.LoanConstants;
import com.dbs.web.service.CustomerService;



@Controller

public class HomeController {

	@Autowired
	private CustomerService service;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@GetMapping("/register")
	public String signup(Map<String, String[]> map)
	{
		map.put("types", LoanConstants.ID_TYPE);
		return "register";
	}
	@GetMapping("/login")
	public String login()
	{
		
		return "login";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session)
	{
		session.removeAttribute("cust");
		session.removeAttribute("custemail");
		session.invalidate();
		return "redirect:/login";
	}
	@PostMapping("/login")
	public String validate(User user,
			HttpSession session,
			Map<String , String> map)
	{
		
		System.out.println("post user "+user);
		Customer customer;
		try {
			customer = this.service.findCustomerByEmail(user.getEmail());
			if(customer.getPassword().equals(user.getPassword())) {
				session.setAttribute("custemail", user.getEmail());
				session.setAttribute("cust", customer);
				return "redirect:/customers/"+customer.getCustomerEmailId();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("error", "Contact admin");
		}
		map.put("error", "Check your credentials");
		return "login";
	}
	@PostMapping("/add")
	public String insertCustomer(Customer customer, Map<String, String> map) {

		System.out.println(customer);
			try {
				if(this.service.addCustomer(customer))
				{
					return "redirect:/login";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				map.put("error", "Could not register, please try again later");
				return "register";
			}
			map.put("error", "Could not register, email id already exists");
			return "register";
	}
}
