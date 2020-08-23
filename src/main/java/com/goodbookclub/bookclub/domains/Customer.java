package com.goodbookclub.bookclub.domains;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.goodbookclub.bookclub.customValidations.PhoneNumber;

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
	
	@NotEmpty(message = "FirstName cannot be Empty")
	@Size(min = 3)
	private String firstName;
	
	@NotEmpty(message = "LastName cannot be Empty")
	@Size(min = 3)
	private String lastName;
	
	@Email
	private String email;
	
	@PhoneNumber
	private String phoneNumber;
	
	@Embedded
	@NotNull(message = "Address cannot be Empty")
	Address address =  new Address(); 
	
	@JsonBackReference
	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@ToString.Exclude
	private User user;
	
	public void setUser(User user) {
		this.user = user;
	}

}
