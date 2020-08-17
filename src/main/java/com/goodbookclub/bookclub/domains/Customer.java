package com.goodbookclub.bookclub.domains;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class Customer extends AbstractDomainClass{
	
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	
	@Embedded
	Address address =  new Address(); 
	
	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST} )
	private User user;

}
