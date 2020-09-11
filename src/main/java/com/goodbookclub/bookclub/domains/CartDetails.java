package com.goodbookclub.bookclub.domains;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class CartDetails extends AbstractDomainClass{

	@ManyToOne
    private Cart cart;

    @OneToOne
    private Product product;

    private Integer quantity;
}
