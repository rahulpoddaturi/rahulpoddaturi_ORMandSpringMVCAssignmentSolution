package com.customer.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class WelcomeController {
	@RequestMapping("/")
	//@PatchMapping("/customer/list")
	public RedirectView redirectPage()
	{
		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl("/customer/list");
	        return redirectView;
		//return "main-menu";
	}

}
