package com.customer.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.customer.management.entity.Customer;
import com.customer.management.service.CustomerService;


@Controller
@RequestMapping("/customer")
public class CustomController {
	
	@Autowired
	private CustomerService  customerService;
	
	@RequestMapping("/list")
	public String listCustomer(Model theModel) {

		// get Students from db
		List<Customer> theCustomers = customerService.findAll();

		// add to the spring model
		theModel.addAttribute("Customers", theCustomers);

		return "list-customers";
	}
	@RequestMapping("/showFormForRegistration")
	public String showForm(Model theModel) {

		// create a Customer object
		Customer theCustomer = new Customer();

		// add Customer object to the model
		theModel.addAttribute("Customer", theCustomer);

		return "customer-form";
	}

	@RequestMapping("/processForm")
	public String processForm(@ModelAttribute("customer") Customer theCustomer) {

		// log the input data
		System.out.println("theCustomer: " + theCustomer.getFirstName() + " " + theCustomer.getLastName());

		return "customer-confirmation";
	}
	
	@PostMapping("/save")
	public String SaveCustomer(@RequestParam("id") int id,
							  @RequestParam("FirstName") String firstname,
							  @RequestParam("LastName") String lastname,
							  @RequestParam("Email") String email) {

		System.out.println(id);
		Customer theCustomer;
		if (id != 0) {
			theCustomer = customerService.findById(id);
			theCustomer.setFirstName(firstname);
			theCustomer.setLastName(lastname);
			theCustomer.setEmail(email);
		} else
			theCustomer = new Customer(firstname,lastname,email);
		
		customerService.save(theCustomer);

		
		return "redirect:/customer/list";

	}
	@RequestMapping("/delete")
	public String delete(@RequestParam("customerId") int theId) {

		
		customerService.deleteById(theId);

	    return "redirect:/customer/list";

	}
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId,Model theModel) {

	
		Customer theCustomer = customerService.findById(theId);
		theModel.addAttribute("Customer", theCustomer);
	
		return "customer-form";

	}
	
}


