package com.goodbookclub.bookclub.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.goodbookclub.bookclub.domains.Customer;
import com.goodbookclub.bookclub.domains.OrderDetails;
import com.goodbookclub.bookclub.services.customer.CustomerService;
import com.goodbookclub.bookclub.services.order.OrderService;

@RestController
public class CustomerController {

	private CustomerService customerService;
	private OrderService orderService;

	@Autowired
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping("/customers")
	public List<Customer> listCustomer(){
		return customerService.listOfCustomers();
	}
	
	@GetMapping("/customers/{id}")
	public Customer getCustomer(@PathVariable Integer id) {
		return customerService.getCustomerById(id);
	}
	
	@GetMapping("/customers/{id}/orders")
	public List<OrderDetails> getOrderDetail(@PathVariable Integer id){
		return orderService.getOrders(customerService.getCustomerById(id));
	}
	
	@PostMapping("/customers/new")
	public Customer addCustomer(@Valid @RequestBody Customer customer) {
		return customerService.saveOrUpdateCustomer(customer);
	}
	
	@PutMapping("/customers/edit/{id}")
	public Customer updateCustomer(@Valid @RequestBody Customer customer) {
		return customerService.saveOrUpdateCustomer(customer);
	}
	
	@DeleteMapping("/customers/delete/{id}")
	public void deleteCustomer(@PathVariable Integer id) {
		customerService.deleteCustomer(id);
	}
	
}
