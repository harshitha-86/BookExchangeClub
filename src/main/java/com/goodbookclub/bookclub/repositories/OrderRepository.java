package com.goodbookclub.bookclub.repositories;

import org.springframework.data.repository.CrudRepository;

import com.goodbookclub.bookclub.domains.Customer;
import com.goodbookclub.bookclub.domains.Order;

public interface OrderRepository extends CrudRepository<Order, Integer>{
	Order findByCustomer(Customer customer);
}
