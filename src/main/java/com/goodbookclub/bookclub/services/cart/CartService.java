package com.goodbookclub.bookclub.services.cart;

import java.util.List;

import com.goodbookclub.bookclub.domains.Cart;

public interface CartService {

	List<Cart> listOfCarts();
	Cart getCartById(Integer id);
	Cart saveOrUpdateCart(Cart cart);
	void deleteCart(Integer id);
}
