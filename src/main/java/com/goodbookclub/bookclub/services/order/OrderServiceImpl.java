package com.goodbookclub.bookclub.services.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodbookclub.bookclub.domains.Customer;
import com.goodbookclub.bookclub.domains.Order;
import com.goodbookclub.bookclub.domains.OrderDetails;
import com.goodbookclub.bookclub.repositories.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;
	
	@Autowired
	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public List<Order> listOfOrders() {
		List<Order> orders = new ArrayList<>();
		orderRepository.findAll().forEach(orders::add);
		log.info("Total no. of Orders: "+orders.size());
		return orders;
	}

	@Override
	public Order getOrderById(Integer id) {
		Order order = orderRepository.findById(id).orElse(null);
		if(order==null) {
			log.error("Order doesn't exist with id: "+id);
		}else {
			log.info("Order found: "+order);
		}
		return order;
	}

	@Override
	public Order saveOrUpdate(Order order) {
		if(order.getId()==null) {
			log.info("Order Saved: "+order);
		}else {
			log.info("Order updated: "+order);
		}
		return orderRepository.save(order);
	}

	@Override
	public void deleteOrder(Integer id) {
		Order order = orderRepository.findById(id).orElse(null);
		if(order==null) {
			log.error("Order doesn't exist with id: "+id);
		}else {
			log.info("Order deleted with order id: "+id);
			orderRepository.deleteById(id);
		}
	}

	@Override
	public List<OrderDetails> getOrders(Customer customer) {
		List<OrderDetails> orders = null;
		if(customer!=null) {
			Order order = orderRepository.findByCustomer(customer);
			orders  = order.getOrderDetails();
		}else {
			log.error("Customer doesn't exist");
		}
		return orders;
	}

}
