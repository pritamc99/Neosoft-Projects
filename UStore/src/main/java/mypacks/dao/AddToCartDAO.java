package mypacks.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import mypacks.model.DeliveryAddress;
import mypacks.model.Item;
import mypacks.model.MyOrder;

public interface AddToCartDAO 
{
	public boolean updateItemQuantity(Item item);
	public Item getItemToInsert(int id);
	public boolean insertOrder(MyOrder order);
	public boolean insertDeliveryAddress(DeliveryAddress address);
	public List<DeliveryAddress> getDeliveryAddress(String deliveryAddress);
}
