package com.goodbookclub.bookclub.services.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodbookclub.bookclub.domains.Cart;
import com.goodbookclub.bookclub.domains.CartDetail;
import com.goodbookclub.bookclub.domains.Product;
import com.goodbookclub.bookclub.domains.User;
import com.goodbookclub.bookclub.repositories.CartRepository;
import com.goodbookclub.bookclub.repositories.CustomerRepository;
import com.goodbookclub.bookclub.repositories.ProductRepository;
import com.goodbookclub.bookclub.repositories.UserRepository;
import com.goodbookclub.bookclub.services.jms.SendOrderRequestService;
import com.goodbookclub.bookclub.services.security.EncryptionService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	private CustomerRepository customerRepository;
	private EncryptionService encryptionService;
	private CartRepository cartRepository;
	private ProductRepository productRepository;
	private SendOrderRequestService sendOrderRequestService;

	@Autowired
	public void setSendOrderRequestService(SendOrderRequestService sendOrderRequestService) {
		this.sendOrderRequestService = sendOrderRequestService;
	}

	@Autowired
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Autowired
	public void setCartRepository(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}

	@Autowired
	public void setEncryptionService(EncryptionService encryptionService) {
		this.encryptionService = encryptionService;
	}

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Autowired
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
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
		else {
			log.info("User found: "+ user);
		}
		return user;
	}

	public User updateUser(User update, Integer id) {
		User user = userRepository.findById(id).orElse(null);
		
		// update customer
		user.getCustomer().setId(update.getCustomer().getId());
		user.getCustomer().setFirstName(update.getCustomer().getFirstName());
		user.getCustomer().setLastName(update.getCustomer().getLastName());
		user.getCustomer().setEmail(update.getCustomer().getEmail());
		user.getCustomer().setPhoneNumber(update.getCustomer().getPhoneNumber());
		user.getCustomer().setAddress(update.getCustomer().getAddress());
		
		// update the user
		user.setId(update.getId());
		user.setUsername(update.getUsername());
		if(update.getPassword()!=null && !encryptionService.checkPassword(update.getPassword(), user.getEncryptedpassword())) {
			user.setPassword(update.getPassword());
			user.setEncryptedpassword(encryptionService.encryptString(update.getPassword()));
		}
		user.setRole(update.getRole());
		user.setEnabled(update.isEnabled());
		
		return user;
	}
	
	@Override
	public User saveOrUpdateUser(User user) {		
		User updatedUser = user;
		if(user.getId()!=null) {
			log.info("User updated: "+user);
			updatedUser = updateUser(user, user.getId());
		}else {
			user.setEncryptedpassword(encryptionService.encryptString(user.getPassword()));
			log.info("User saved: "+user);
		}
		return userRepository.save(updatedUser);
	}

	@Override
	@Transactional
	public void deleteUser(Integer id) {
		User user = userRepository.findById(id).orElse(null);
		if(user==null)
			log.error("User doesn't exist");
		else {
			log.info("User deleted: "+user);
			customerRepository.deleteById(user.getCustomer().getId());
			cartRepository.delete(user.getCart());
			userRepository.deleteById(id);
		}
	}

	@Override
	public List<CartDetail> getCartbyUser(Integer id) {

		List<CartDetail> cartDetails = null;
		User user = userRepository.findById(id).orElse(null);
		if(user!=null) {
			log.info("Cart Details for User with id: "+id+" being loaded");
			Cart cart = user.getCart();
			cartDetails = cart.getCartDetails();
			log.info("For user id: "+id+" total no. of cart items: "+cartDetails.size());
		}else {
			log.error("User doesn't exist with id: "+id);
		}
		
		return cartDetails;
	}

	@Override
	public CartDetail addToCart(Integer id, Integer prodId, Integer quantity) {
		
		User user = userRepository.findById(id).orElse(null);
		Product product = productRepository.findById(prodId).orElse(null);
		CartDetail cartDetail = null;
		if(user!=null && product!=null) {
			cartDetail = new CartDetail();
            cartDetail.setProduct(product);
            cartDetail.setQuantity(quantity);
            String key = new Random().ints(97, 123).limit(8).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
          	      .toString();
            cartDetail.setKey(key);
            
            Cart cart = user.getCart();
            cart.addCartDetail(cartDetail);
            cartRepository.save(cart);
            log.info("Product "+prodId+" added to cart successfully!!!");
		}else {
			log.error("Either the user or the product doesn't exist.");
		}
		
		return cartDetail;
	}

	@Override
	public void placeOrder(Integer id, Integer prod, Integer quantity, String key) {
		
		Integer cust_id = (userRepository.findById(id).orElse(null)).getCustomer().getId();
		sendOrderRequestService.sendOrderRequest(cust_id+" "+prod+" "+quantity);
		
		Cart cart = userRepository.findById(id).orElse(null).getCart();
		CartDetail cd=null;
		
		for(CartDetail cartDetail: cart.getCartDetails()) {
			if(cartDetail.getKey().equals(key)) {
				cd = cartDetail;
			}
		}
		if(cd!=null)
			cart.removeCartDetail(cd);

		cartRepository.save(cart);
		
	}

}
