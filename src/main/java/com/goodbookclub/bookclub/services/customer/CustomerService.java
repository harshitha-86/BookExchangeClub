package com.goodbookclub.bookclub.services.customer;

import java.util.List;

import com.goodbookclub.bookclub.domains.Customer;

public interface CustomerService {
	
	List<Customer> listOfCustomers();
	Customer getCustomerById(Integer id);
	Customer saveOrUpdateCustomer(Customer customer);
	void deleteCustomer(int id);
}
