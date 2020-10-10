package com.goodbookclub.bookclub.config;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JMSConfig {
	
	public static final String textMsgQueue = "text.messagequeue";
	public static final String orderQueue = "text.orderqueue";

    @Bean
    public Queue textMessageQueue(){
        return new ActiveMQQueue(textMsgQueue);
    }
    
    @Bean
    public Queue orderRequestQueue(){
    	return new ActiveMQQueue(orderQueue);
    }
}
