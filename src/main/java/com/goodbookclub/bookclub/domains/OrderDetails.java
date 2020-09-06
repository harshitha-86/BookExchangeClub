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
public class OrderDetails extends AbstractDomainClass {
	
	@ManyToOne
    private Order order;

    @OneToOne
    private Product product;

    private Integer quantity;
}
