package com.goodbookclub.bookclub.domains;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

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
public class Product extends AbstractDomainClass{
	
	@NotEmpty
	private String name;	
	private String description;	
	@NotEmpty
	private String imgUrl;

}
