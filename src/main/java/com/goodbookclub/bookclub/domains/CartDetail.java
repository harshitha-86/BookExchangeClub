package com.goodbookclub.bookclub.domains;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class CartDetail extends AbstractDomainClass{

	@ManyToOne
	@JsonBackReference
    private Cart cart;

    @OneToOne
    private Product product;

    private Integer quantity;
}
