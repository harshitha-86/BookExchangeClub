package com.goodbookclub.bookclub.services.jms;

import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.goodbookclub.bookclub.domains.Customer;
import com.goodbookclub.bookclub.domains.EmailContent;
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
	private KafkaTemplate<String, EmailContent> kafkaTemplate;
	private static final String TOPIC = "Order_Emails";

	@Autowired
	public void setKafkaTemplate(KafkaTemplate<String, EmailContent> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

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
		
		// create a new order for customer;
		Customer cust = customerRepository.findById(cust_id).orElse(null);
		Product prod = productRepository.findById(prod_id).orElse(null);
		int limit =  prod.getQuantity();
		
		if(cust!=null && prod!=null) {
			Order orderr = orderRepository.findByCustomer(cust);
			if(orderr==null) {
				orderr = new Order();
				orderr.setCustomer(cust);
			}
			orderr.setOrderStatus(OrderStatus.NEW);
			orderr.setShipToAddress(cust.getAddress());
			
			OrderDetails orderDetails = new OrderDetails();
			orderDetails.setProduct(prod);
			if(limit<quantity) {
				log.error("quntity exceeded the available product limit.");
				orderr.setOrderStatus(OrderStatus.FAILED);
			}else {
				prod.setQuantity(limit-quantity);
				productRepository.save(prod);
				log.info("order placed !!!");
				String fname = cust.getFirstName();
				String lname = cust.getLastName();
				String email = cust.getEmail();
				String body = "Hi "+fname+" "+lname+",\n\n"+"Your order for "+quantity+" - "+prod.getName()+" was placed successfully!!! \n\nRegards\n\nBookExchangeClub";
				EmailContent content = new EmailContent(fname, lname, email, body);
				kafkaTemplate.send(TOPIC, content);
			}
			orderDetails.setQuantity(quantity);
			orderr.addToOrderDetails(orderDetails);
			orderRepository.save(orderr);
			
		}
	}
}
