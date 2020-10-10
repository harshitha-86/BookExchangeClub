package com.goodbookclub.bookclub.services.order;

import java.util.List;

import com.goodbookclub.bookclub.domains.Customer;
import com.goodbookclub.bookclub.domains.Order;
import com.goodbookclub.bookclub.domains.OrderDetails;

public interface OrderService {

	List<Order> listOfOrders();
	Order getOrderById(Integer id);
	Order saveOrUpdate(Order order);
	void deleteOrder(Integer id);
	List<OrderDetails> getOrders(Customer customer);
}
