package mypacks.service;

import java.util.List;

import mypacks.model.MyOrder;
import mypacks.model.OrderItem;

public interface MyAccountService 
{
	public List<MyOrder> getOrder(int userId);
	public List<OrderItem> getOrderItem(int orderId);
}
