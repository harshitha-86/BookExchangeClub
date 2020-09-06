package com.goodbookclub.bookclub.services.order;

import java.util.List;

import com.goodbookclub.bookclub.domains.Order;

public interface OrderService {

	List<Order> listOfOrders();
	Order getOrderById(Integer id);
	Order saveOrUpdate(Order order);
	void deleteOrder(Integer id);
}
