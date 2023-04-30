package com.goodbookclub.bookclub.services.jms;

import jakarta.jms.Queue;
import jakarta.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class SendOrderRequestServiceImpl implements SendOrderRequestService {

	private Queue orderRequestQueue;
	private JmsTemplate jmsTemplate;
	
	@Autowired
	public void setOrderRequestQueue(Queue orderRequestQueue) {
		this.orderRequestQueue = orderRequestQueue;
	}

	@Autowired
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	@Override
	public void sendOrderRequest(String order) {
		Destination destination = (Destination) this.orderRequestQueue;
		this.jmsTemplate.convertAndSend(destination, order);
	}

}
