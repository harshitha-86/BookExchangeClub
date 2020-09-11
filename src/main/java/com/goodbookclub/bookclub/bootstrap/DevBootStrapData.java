package com.goodbookclub.bookclub.bootstrap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.goodbookclub.bookclub.domains.Address;
import com.goodbookclub.bookclub.domains.Cart;
import com.goodbookclub.bookclub.domains.CartDetails;
import com.goodbookclub.bookclub.domains.Customer;
import com.goodbookclub.bookclub.domains.Order;
import com.goodbookclub.bookclub.domains.OrderDetails;
import com.goodbookclub.bookclub.domains.Product;
import com.goodbookclub.bookclub.domains.User;
import com.goodbookclub.bookclub.enums.OrderStatus;
import com.goodbookclub.bookclub.services.order.OrderService;
import com.goodbookclub.bookclub.services.product.ProductService;
import com.goodbookclub.bookclub.services.user.UserService;

@Component
public class DevBootStrapData implements ApplicationListener<ContextRefreshedEvent>{

	private UserService userService;
	private ProductService productService;
	private OrderService orderService;
	
	@Autowired
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		loadCustomers();
		loadProducts();
		loadCarts();
		loadOrderHistory();
	}
	
	private void loadCarts() {
        List<User> users = (List<User>) userService.listOfUsers();
        List<Product> products = (List<Product>) productService.listOfProducts();

        users.forEach(user -> {
//            user.setCart(new Cart());
            CartDetails cartDetail = new CartDetails();
            cartDetail.setProduct(products.get(0));
            cartDetail.setQuantity(2);
            user.getCart().addCartDetail(cartDetail);
//            user.setPassword("password");
//            System.out.println(user);
//            userService.saveOrUpdateUser(user);
        });
    }
	
	private void loadOrderHistory() {
        List<User> users = (List<User>) userService.listOfUsers();
        List<Product> products = (List<Product>) productService.listOfProducts();

        users.forEach(user ->{
            Order order = new Order();
            order.setCustomer(user.getCustomer());
            order.setOrderStatus(OrderStatus.SHIPPED);
            order.setShipToAddress(user.getCustomer().getAddress());

            products.forEach(product -> {
                OrderDetails orderDetail = new OrderDetails();
                orderDetail.setProduct(product);
                orderDetail.setQuantity(1);
                order.addToOrderDetails(orderDetail);
            });
            
            orderService.saveOrUpdate(order);
        });
    }
	
	private void loadProducts() {
		
		Product p1 = new Product();
		p1.setName("Laptop");
		p1.setDescription("This is a laptop description");
		p1.setImgUrl("images/laptop.png");
		productService.saveOrUpdateProduct(p1);
		
		Product p2 = new Product();
		p2.setName("Table");
		p2.setDescription("This is a table description");
		p2.setImgUrl("images/table.png");
		productService.saveOrUpdateProduct(p2);
		
		Product p3 = new Product();
		p3.setName("Chair");
		p3.setDescription("This is a chair description");
		p3.setImgUrl("images/chair.png");
		productService.saveOrUpdateProduct(p3);
		
		Product p4 = new Product();
		p4.setName("Mobile");
		p4.setDescription("This is a mobile description");
		p4.setImgUrl("images/mobile.png");
		productService.saveOrUpdateProduct(p4);
		
		Product p5 = new Product();
		p5.setName("Shirt");
		p5.setDescription("This is a shirt description");
		p5.setImgUrl("images/shirt.png");
		productService.saveOrUpdateProduct(p5);
		
	}
	
	private void loadCustomers() {
		User user1 = new User();
		user1.setUsername("shashank136");
		user1.setPassword("Password");
		user1.setCart(new Cart());
		user1.setRole("USER");
		
		Customer c1 = new Customer();
		c1.setFirstName("Shashank");
		c1.setLastName("Kumar"); 
		c1.setEmail("shashank136.sk@gmail.com");
		c1.setPhoneNumber("7032473030");
		c1.setAddress(new Address());
		c1.getAddress().setLine1("H.no. 69, Shanker Green Homes");
		c1.getAddress().setLine2("Ameenpur");
		c1.getAddress().setCity("Hyderabad");
		c1.getAddress().setState("Telangana");
		c1.getAddress().setZipcode("502032");
		
		user1.setCustomer(c1);
		userService.saveOrUpdateUser(user1);
		
		User user2 = new User();
		user2.setUsername("amit2006");
		user2.setPassword("Password");
		user2.setRole("USER");
		user2.setCart(new Cart());
		
		Customer c2 = new Customer();
		c2.setFirstName("Amit");
		c2.setLastName("Kumar");
		c2.setEmail("akumar@gmail.com");
		c2.setPhoneNumber("9893305270");
		c2.setAddress(new Address());
		c2.getAddress().setLine1("H.no. 69, Shanker Green Homes");
		c2.getAddress().setLine2("Ameenpur");
		c2.getAddress().setCity("Hyderabad");
		c2.getAddress().setState("Telangana");
		c2.getAddress().setZipcode("502032");
		
		user2.setCustomer(c2);
		userService.saveOrUpdateUser(user2);
		
		User user3 = new User();
		user3.setUsername("mohan270766");
		user3.setPassword("Password");
		user3.setCart(new Cart());
		user3.setRole("USER");
		
		Customer c3 = new Customer();
		c3.setFirstName("Mohan");
		c3.setLastName("Kumar");
		c3.setEmail("mohan270766@gmail.com");
		c3.setPhoneNumber("9871686026");
		c3.setAddress(new Address());
		c3.getAddress().setLine1("H.no. 69, Shanker Green Homes");
		c3.getAddress().setLine2("Ameenpur");
		c3.getAddress().setCity("Hyderabad");
		c3.getAddress().setState("Telangana");
		c3.getAddress().setZipcode("502032");
		
		user3.setCustomer(c3);
		userService.saveOrUpdateUser(user3);
		
		User user4 = new User();
		user4.setUsername("jyothi1410");
		user4.setPassword("Password");
		user4.setCart(new Cart());
		user4.setRole("USER");
		
		Customer c4 = new Customer();
		
		c4.setFirstName("Jyothi");
		c4.setLastName("Mohan");
		c4.setEmail("jyothi1014@gmail.com");
		c4.setPhoneNumber("9999624749");
		c4.setAddress(new Address());
		c4.getAddress().setLine1("H.no. 69, Shanker Green Homes");
		c4.getAddress().setLine2("Ameenpur");
		c4.getAddress().setCity("Hyderabad");
		c4.getAddress().setState("Telangana");
		c4.getAddress().setZipcode("502032");
		
		user4.setCustomer(c4);
		userService.saveOrUpdateUser(user4);
		
	}

}
