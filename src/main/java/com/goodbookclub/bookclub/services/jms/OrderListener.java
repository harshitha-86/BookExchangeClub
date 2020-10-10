package com.goodbookclub.bookclub.services.jms;

import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.goodbookclub.bookclub.domains.Cart;
import com.goodbookclub.bookclub.domains.CartDetail;
import com.goodbookclub.bookclub.domains.Customer;
import com.goodbookclub.bookclub.domains.Order;
import com.goodbookclub.bookclub.domains.OrderDetails;
import com.goodbookclub.bookclub.domains.Product;
import com.goodbookclub.bookclub.enums.OrderStatus;
import com.goodbookclub.bookclub.repositories.CustomerRepository;
import com.goodbookclub.bookclub.repositories.OrderRepository;
import com.goodbookclub.bookclub.repositories.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OrderListener {
	
	private CustomerRepository customerRepository;
	private OrderRepository orderRepository;
	private ProductRepository productRepository;

	@Autowired
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Autowired
	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Autowired
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@JmsListener(destination = "text.orderqueue")
	public void onMessage(String order) {
		
		StringTokenizer st = new StringTokenizer(order);
		
		Integer cust_id = Integer.parseInt(st.nextToken());
		Integer prod_id = Integer.parseInt(st.nextToken());
		Integer quantity = Integer.parseInt(st.nextToken());
//		Integer cartDetails_id = Integer.parseInt(st.nextToken());
		
		// create a new order for customer;
		Customer cust = customerRepository.findById(cust_id).orElse(null);
		Product prod = productRepository.findById(prod_id).orElse(null);
		int limit =  prod.getQuantity();
		
		if(cust!=null && prod!=null) {
			Order orderr = new Order();
			orderr.setCustomer(cust);
			orderr.setOrderStatus(OrderStatus.NEW);
			orderr.setShipToAddress(cust.getAddress());
			
			OrderDetails orderDetails = new OrderDetails();
			orderDetails.setProduct(prod);
			if(limit<quantity) {
				log.error("quntity exceeded the available product limit.");
				orderr.setOrderStatus(OrderStatus.FAILED);
			}else
				log.info("order placed !!!");
			
			orderDetails.setQuantity(quantity);
			orderr.addToOrderDetails(orderDetails);
			orderRepository.save(orderr);
			
			//Remove the cart details
//			Cart cart = cust.getUser().getCart();
//			List<CartDetail> cartDetails = cart.getCartDetails();
//			for(CartDetail cd: cartDetails) {
//				if(cd.getId()==cartDetails_id) {
//					cart.removeCartDetail(cd);
//				}
//			}
			
		}
	}
}
