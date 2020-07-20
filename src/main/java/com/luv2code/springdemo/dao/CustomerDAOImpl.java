package com.luv2code.springdemo.dao;

import com.luv2code.springdemo.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the hibernate session factory
	// for DAO can use it for talking the db
	@Autowired
	private SessionFactory sessionFactory;

	// let Spring manage starting and stopping the transactions
	public List<Customer> getCustomers() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query .. sort by last name
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", Customer.class);

		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();

		// return the results
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// save the customer ... finally LOL
		currentSession.save(theCustomer);
	}

	@Override
	public Customer getCustomer(int theId) {
		// get the current hibernate sessoin
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve from database using the primary key (id)
		Customer theCustomer = currentSession.get(Customer.class, theId);

		return theCustomer;
	}
}
