package com.goodbookclub.bookclub.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.goodbookclub.bookclub.domains.Address;
import com.goodbookclub.bookclub.domains.Customer;
import com.goodbookclub.bookclub.domains.User;
import com.goodbookclub.bookclub.services.user.UserService;

@Component
public class DevBootStrapData implements ApplicationListener<ContextRefreshedEvent>{

	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		loadCustomers();
	}
	
	public void loadCustomers() {
		User user1 = new User();
		user1.setUsername("shashank136");
		user1.setPassword("Password");
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
