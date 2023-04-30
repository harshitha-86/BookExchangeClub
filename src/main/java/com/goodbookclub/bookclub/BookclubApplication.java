package com.goodbookclub.bookclub;

// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.ApplicationContext;

// import com.goodbookclub.bookclub.services.greeting.GreetingService;
// import com.goodbookclub.bookclub.services.greeting.GreetingServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication //(exclude = PropertyPlaceholderAutoConfiguration.class)
public class BookclubApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookclubApplication.class, args);
		// ApplicationContext context  = SpringApplication.run(BookclubApplication.class, args);
		
//		GreetingService greetingService = (GreetingServiceImpl)context.getBean("greetingServiceImpl");
//		greetingService.greet();
	}

}
