package com.customer.management.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.customer.management.entity.Customer;

@Repository
public class CustomerServiceImpl implements CustomerService {

	private SessionFactory sessionFactory;

	// create session
	private Session session;
	@Autowired
	CustomerServiceImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
	}
	
	@Transactional
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();

		// find all the records from the database table
		List<Customer> customers = session.createQuery("from Customer").list();

		tx.commit();

		return customers;
	}

	@Transactional
	public Customer findById(int theId) {
		// TODO Auto-generated method stub
		Customer customer = new Customer();
		Transaction tx = session.beginTransaction();

		// find record with Id from the database table
		customer = session.get(Customer.class, theId);

		tx.commit();

		return customer;

	}

	@Transactional
	public void save(Customer theCustomer) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();

		// save transaction
		session.saveOrUpdate(theCustomer);

		tx.commit();
	}

	@Transactional
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();

		// get transaction
		Customer student = session.get(Customer.class, theId);

		// delete record
		session.delete(student);

		tx.commit();
	}

}
