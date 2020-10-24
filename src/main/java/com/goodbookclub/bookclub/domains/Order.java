package com.goodbookclub.bookclub.domains;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.goodbookclub.bookclub.enums.OrderStatus;

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
// ORDER is service word in any sql. Exception appears because hibernate was generating *** statement for the order table but db expected table name not service word order.
@Table(name = "ORDER_HEADER")
public class Order extends AbstractDomainClass{

	@OneToOne
	@ToString.Exclude
	//When using Hibernate as JPA provider then @OnDelete delegates the delete operation on foreign key
    @OnDelete(action = OnDeleteAction.CASCADE)
	private Customer customer;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE) // If you have more then one list to Fetch.EAGER then remove the fetech type from @*ToMany and add this annotation.
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ToString.Exclude
	@JsonIgnore
    private List<OrderDetails> orderDetails = new ArrayList<>();
	
	@Embedded
	private Address shipToAddress;
	private OrderStatus orderStatus;
	private Date dateShipped;
	
	public void addToOrderDetails(OrderDetails orderDetail){
        orderDetail.setOrder(this);
        orderDetails.add(orderDetail);
    }

    public void removeOrderDetail(OrderDetails orderDetail){
        orderDetail.setOrder(null);
        orderDetails.remove(orderDetail);
    }
}
