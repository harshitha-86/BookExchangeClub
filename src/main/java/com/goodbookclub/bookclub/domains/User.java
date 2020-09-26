package com.goodbookclub.bookclub.domains;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
	
	@NotEmpty(message = "UserName cannot be Empty")
	@Size(min = 3, max = 12, message = "UserName should be of size min 3 and max 12")
	@Column(unique = true)
	private String username;
	
	@Transient
//	@NotEmpty(message = "Password cannot be Empty")
//	@Size(min = 6, max = 12, message = "Password should be of size min 6 and max 12")
	private String password; 
	private String encryptedpassword;
	private boolean enabled = true;
	
	@NotEmpty(message = "Role cannot be Empty")
	private String role;
	
	@JsonManagedReference
	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@ToString.Exclude
	private Customer customer;
	
	@ToString.Exclude
	@JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
		customer.setUser(this);
	}
	
	public void setCart(Cart cart) {
		this.cart = cart;
		cart.setUser(this);
	}
}
