package com.goodbookclub.bookclub.repositories;

import org.springframework.data.repository.CrudRepository;

import com.goodbookclub.bookclub.domains.User;

public interface UserRepository extends CrudRepository<User, Integer>{

}
