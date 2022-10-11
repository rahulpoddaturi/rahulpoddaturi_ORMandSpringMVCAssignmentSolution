package com.customer.management.service;

import java.util.List;

import com.customer.management.entity.Customer;

public interface CustomerService {
	
	public List<Customer> findAll();
	public Customer findById(int theId);

	public void save(Customer theCustomer);

	public void deleteById(int theId); 
	

}
