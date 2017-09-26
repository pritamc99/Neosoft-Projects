package mypacks.dao;

import java.util.List;

import mypacks.model.MyOrder;
import mypacks.model.OrderItem;

public interface MyAccountDAO 
{
	public List<MyOrder> getOrderList(int userId);
	public List<OrderItem> getOrderItemList(int orderId);
}
