package com.goodbookclub.bookclub.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.goodbookclub.bookclub.domains.Address;
import com.goodbookclub.bookclub.domains.Customer;
import com.goodbookclub.bookclub.services.customer.CustomerService;

@Component
public class DevBootStrapData implements ApplicationListener<ContextRefreshedEvent>{

	private CustomerService customerService;
	
	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		loadCustomers();
	}
	
	public void loadCustomers() {
		Customer c1 = new Customer();
		c1.setFirstName("Shashank");
		c1.setLastName("Kumar"); 
		c1.setEmail("shashank136.sk@gmail.com");
		c1.setPhoneNumber("7032473030");
		c1.setBillingAddress(new Address());
		c1.getBillingAddress().setLine1("H.no. 69, Shanker Green Homes");
		c1.getBillingAddress().setLine2("Ameenpur");
		c1.getBillingAddress().setCity("Hyderabad");
		c1.getBillingAddress().setState("Telangana");
		c1.getBillingAddress().setZipcode("502032");
		
		customerService.saveOrUpdateCustomer(c1);
		
		Customer c2 = new Customer();
		c2.setFirstName("Amit");
		c2.setLastName("Kumar");
		c2.setEmail("akumar@gmail.com");
		c2.setPhoneNumber("9893305270");
		c2.setBillingAddress(new Address());
		c2.getBillingAddress().setLine1("H.no. 69, Shanker Green Homes");
		c2.getBillingAddress().setLine2("Ameenpur");
		c2.getBillingAddress().setCity("Hyderabad");
		c2.getBillingAddress().setState("Telangana");
		c2.getBillingAddress().setZipcode("502032");
		
		customerService.saveOrUpdateCustomer(c2);
		
		Customer c3 = new Customer();

		c3.setFirstName("Mohan");
		c3.setLastName("Kumar");
		c3.setEmail("mohan270766@gmail.com");
		c3.setPhoneNumber("9871686026");
		c3.setBillingAddress(new Address());
		c3.getBillingAddress().setLine1("H.no. 69, Shanker Green Homes");
		c3.getBillingAddress().setLine2("Ameenpur");
		c3.getBillingAddress().setCity("Hyderabad");
		c3.getBillingAddress().setState("Telangana");
		c3.getBillingAddress().setZipcode("502032");
				
		customerService.saveOrUpdateCustomer(c3);
		
		Customer c4 = new Customer();
		
		c4.setFirstName("Jyothi");
		c4.setLastName("Mohan");
		c4.setEmail("jyothi1014@gmail.com");
		c4.setPhoneNumber("9999624749");
		c4.setBillingAddress(new Address());
		c4.getBillingAddress().setLine1("H.no. 69, Shanker Green Homes");
		c4.getBillingAddress().setLine2("Ameenpur");
		c4.getBillingAddress().setCity("Hyderabad");
		c4.getBillingAddress().setState("Telangana");
		c4.getBillingAddress().setZipcode("502032");
		
		customerService.saveOrUpdateCustomer(c4);
	}

}
