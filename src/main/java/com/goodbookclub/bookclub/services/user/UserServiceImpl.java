package com.goodbookclub.bookclub.services.user;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodbookclub.bookclub.domains.Customer;
import com.goodbookclub.bookclub.domains.User;
import com.goodbookclub.bookclub.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> listOfUsers() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		log.info("Total no. of users: "+users.size());
		return users;
	}

	@Override
	public User getUserById(Integer Id) {
		User user = userRepository.findById(Id).orElse(null);
		if(user==null)
			log.error("User doesn't exist with id: "+Id);
		else
			log.info("User found: "+ user);
		return user;
	}

	@Override
	public User saveOrUpdateUser(User user) {
		log.info("User saved/updated: "+user);
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public void deleteUser(Integer id) {
		User user = userRepository.findById(id).orElse(null);
		if(user==null)
			log.error("User doesn't exist");
		else {
			log.info("User deleted: "+user);
			userRepository.deleteById(id);
		}

	}

}
