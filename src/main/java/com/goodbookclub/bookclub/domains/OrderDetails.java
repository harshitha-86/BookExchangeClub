package com.goodbookclub.bookclub.domains;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
	@OnDelete(action = OnDeleteAction.CASCADE)
    private Order order;

    @OneToOne
    //When using Hibernate as JPA provider then @OnDelete delegates the delete operation on child entity
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    private Integer quantity;
}
