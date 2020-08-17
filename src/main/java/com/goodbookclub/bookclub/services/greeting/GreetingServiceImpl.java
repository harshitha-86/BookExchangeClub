package com.goodbookclub.bookclub.services.greeting;

import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class GreetingServiceImpl implements GreetingService {

	@Override
	public String greet() {
		// TODO Auto-generated method stub
		return "Welcome! to BookClub";
	}

}
