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

import com.goodbookclub.bookclub.domains.CartDetail;
import com.goodbookclub.bookclub.domains.User;
import com.goodbookclub.bookclub.services.user.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/users")
	public List<User> listUser(){
		return userService.listOfUsers();
	}
	
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable Integer id) {
		return userService.getUserById(id);
	}
	
	@GetMapping("/users/{id}/cart")
	public List<CartDetail> viewCart(@PathVariable Integer id){
		return userService.getCartbyUser(id);
	}
	
	@PostMapping("/users/new")
	public User addUser(@Valid @RequestBody User user) {
		log.info("Creating new User");
		return userService.saveOrUpdateUser(user);
	}
	
	@PutMapping("/users/edit/{id}")
	public User updateUser(@Valid @RequestBody User user, @PathVariable Integer id) {
		log.info("Updating user with id: "+id+" ...");
		return userService.saveOrUpdateUser(user);
	}
	
	@DeleteMapping("/users/delete/{id}")
	public void deleteUser(@PathVariable Integer id) {
		userService.deleteUser(id);
	}
}
