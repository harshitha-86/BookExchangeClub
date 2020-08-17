package com.goodbookclub.bookclub.repositories;

import org.springframework.data.repository.CrudRepository;

import com.goodbookclub.bookclub.domains.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
