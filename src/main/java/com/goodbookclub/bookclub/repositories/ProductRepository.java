package com.goodbookclub.bookclub.repositories;

import org.springframework.data.repository.CrudRepository;

import com.goodbookclub.bookclub.domains.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
