package com.goodbookclub.bookclub.domains;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
public class User extends AbstractDomainClass{
	
	private String username;
	@Transient
	private String password;
	private String encryptedpassword;
	private boolean enabled = true;
	private String role;
	
	@JsonManagedReference
	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@ToString.Exclude
	private Customer customer;
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
		customer.setUser(this);
	}

}
