package mypacks.model;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "MyOrder")
public class MyOrder {
	@Id
	@Column(name = "order_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;

	@OneToOne(targetEntity = Register.class, cascade = CascadeType.ALL)
	@JoinColumn(columnDefinition = "UserId", referencedColumnName = "RegisterId")
	private Register user;

	@OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL)
	private List<OrderItem> orderItem;

	@Column(name = "order_amount")
	private int orderAmount;

	@OneToOne()
	@JoinColumn(name="delivery_address")
	private DeliveryAddress deliveryAddress;

	@Column(name = "order_status")
	private String orderStatus;

	@Column(name = "delivery_status")
	private String deliveryStatus;
	
	@Column(name="order_date")
	private LocalDate orderDate;
	
	@Column(name="expected_delivery_date")
	private LocalDate expectedDeliveryDate;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Register getUser() {
		return user;
	}

	public void setUser(Register user) {
		this.user = user;
	}

	public List<OrderItem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}

	public int getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}

	public DeliveryAddress getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDate getExpectedDeliveryDate() {
		return expectedDeliveryDate;
	}

	public void setExpectedDeliveryDate(LocalDate expectedDeliveryDate) {
		this.expectedDeliveryDate = expectedDeliveryDate;
	}

	



	
}
