package com.goodbookclub.bookclub.services.user;

import java.util.List;

import com.goodbookclub.bookclub.domains.CartDetail;
import com.goodbookclub.bookclub.domains.User;

public interface UserService {
	
	List<User> listOfUsers();
	User getUserById(Integer Id);
	User saveOrUpdateUser(User user);
	void deleteUser(Integer id);
	List<CartDetail> getCartbyUser(Integer id);
	CartDetail addToCart(Integer id, Integer prodId, Integer quantity);
	void placeOrder(Integer id, Integer prod, Integer quantity, String key);
}
