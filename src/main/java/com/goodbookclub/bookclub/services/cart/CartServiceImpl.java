package com.goodbookclub.bookclub.services.cart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodbookclub.bookclub.domains.Cart;
import com.goodbookclub.bookclub.repositories.CartRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CartServiceImpl implements CartService {
	
	private CartRepository cartRepository;

	@Autowired
	public void setCartRepository(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}

	@Override
	public List<Cart> listOfCarts() {
		List<Cart> carts = new ArrayList<>();
		cartRepository.findAll().forEach(carts::add);
		log.info("Total no. of carts: "+carts.size());
		return carts;
	}

	@Override
	public Cart getCartById(Integer id) {
		// TODO Auto-generated method stub
		Cart cart = cartRepository.findById(id).orElse(null);
		if(cart==null) {
			log.error("Cart doesn't exist with the id: "+id);
		}else {
			log.info("Found cart with id: "+id);
		}
		return cart; 
	}

	@Override
	public Cart saveOrUpdateCart(Cart cart) {
		if(cart.getId()==null) {
			log.info("Cart Saved: "+cart);
		}else {
			log.info("Cart updated: "+cart);
		}
		return cartRepository.save(cart);
	}

	@Override
	public void deleteCart(Integer id) {
		Cart cart = cartRepository.findById(id).orElse(null);
		if(cart==null) {
			log.error("Cart doesn't exist with the id: "+id);
		}else {
			log.info("Found cart with id: "+id);
			cartRepository.deleteById(id);
		}
	}

}
