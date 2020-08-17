package com.goodbookclub.bookclub.domains;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Address {
	
	private String line1;
	private String line2;
	private String city;
	private String state;
	private String zipcode;

}
