package com.goodbookclub.bookclub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.goodbookclub.bookclub.services.greeting.GreetingService;
import com.goodbookclub.bookclub.services.greeting.GreetingServiceImpl;

@SpringBootApplication
public class BookclubApplication {

	public static void main(String[] args) {
		ApplicationContext context  = SpringApplication.run(BookclubApplication.class, args);
		
//		GreetingService greetingService = (GreetingServiceImpl)context.getBean("greetingServiceImpl");
//		greetingService.greet();
	}

}
