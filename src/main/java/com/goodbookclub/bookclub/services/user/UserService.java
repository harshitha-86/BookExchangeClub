package com.goodbookclub.bookclub.services.user;

import java.util.List;

import com.goodbookclub.bookclub.domains.User;

public interface UserService {
	
	List<User> listOfUsers();
	User getUserById(Integer Id);
	User saveOrUpdateUser(User user);
	void deleteUser(Integer id);

}
