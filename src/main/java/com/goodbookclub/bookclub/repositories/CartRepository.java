package com.goodbookclub.bookclub.repositories;

import org.springframework.data.repository.CrudRepository;

import com.goodbookclub.bookclub.domains.Cart;

public interface CartRepository extends CrudRepository<Cart, Integer> {

}
