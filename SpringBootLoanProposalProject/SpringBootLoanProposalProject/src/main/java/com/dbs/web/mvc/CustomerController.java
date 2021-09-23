package com.dbs.web.mvc;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customers")
public class CustomerController {

	@GetMapping("/{email}")
	public String details(HttpSession session)
	{
		System.out.println("rediect "+session.getAttribute("custemail"));
		return "customer";
	}
	
}
