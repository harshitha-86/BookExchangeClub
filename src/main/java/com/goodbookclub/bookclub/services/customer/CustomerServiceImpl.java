package com.goodbookclub.bookclub.services.customer;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodbookclub.bookclub.domains.Customer;
import com.goodbookclub.bookclub.repositories.CustomerRepository;
import com.goodbookclub.bookclub.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;
	private UserRepository userRepository;
	
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public List<Customer> listOfCustomers() {
		List<Customer> customers = new ArrayList<>();
		customerRepository.findAll().forEach(customers::add);
		log.info("Total no. of customers: "+customers.size());
		return customers;
	}

	@Override
	public Customer getCustomerById(Integer id) {
		Customer customer = customerRepository.findById(id).orElse(null);
		if(customer==null)
			log.error("Customer doesn't exist with id: "+id);
		else
			log.info("Customer found: "+ customer);
		return customer;
	}
	
	public Customer updateCustomer(Customer update, Integer id) {
		Customer customer = customerRepository.findById(id).orElse(null);
		
		// update user
		customer.getUser().setId(update.getUser().getId());
		customer.getUser().setUsername(update.getUser().getUsername());
		customer.getUser().setPassword(update.getUser().getPassword());
		customer.getUser().setEncryptedpassword(update.getUser().getEncryptedpassword());
		customer.getUser().setRole(update.getUser().getRole());
		customer.getUser().setEnabled(update.getUser().isEnabled());
		
		// update the customer
		customer.setId(update.getId());
		customer.setFirstName(update.getFirstName());
		customer.setLastName(update.getLastName());
		customer.setEmail(update.getEmail());
		customer.setPhoneNumber(update.getPhoneNumber());
		customer.setAddress(update.getAddress());
		
		return customer;
	}

	@Override
	public Customer saveOrUpdateCustomer(Customer customer) {
		Customer updatedCustomer = customer;
		if(customer.getId()!=null) {
			log.info("Customer updated: "+customer);
			updatedCustomer = updateCustomer(customer, customer.getId());
		}else {
			log.info("Customer saved: "+customer);
		}
		return customerRepository.save(updatedCustomer);
	}

	@Override
	@Transactional
	public void deleteCustomer(int id) {
		Customer customer = customerRepository.findById(id).orElse(null);
		if(customer==null)
			log.error("Customer doesn't exist");
		else {
			log.info("Customer deleted: "+customer);
			userRepository.deleteById(customer.getUser().getId());
			customerRepository.deleteById(id);
		}
	}

}
