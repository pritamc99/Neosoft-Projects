package mypacks.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="OrderItem")
public class OrderItem 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="order_item_id")
	private int orderItemId;
	
	@OneToOne(/*targetEntity=Item.class,*/cascade=CascadeType.ALL)
	/*@JoinColumn(columnDefinition="item_id",referencedColumnName="itemId")*/
	private Item item;
	
	@Column(name="quantity")
	private Long quantity;
	
	@Column(name="sub_total")
	private long subTotal;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="order_id")
	private MyOrder orderId;

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	

	public long getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(long subTotal) {
		this.subTotal = subTotal;
	}

	public MyOrder getOrderId() {
		return orderId;
	}

	public void setOrderId(MyOrder orderId) {
		this.orderId = orderId;
	}
	
	
}
