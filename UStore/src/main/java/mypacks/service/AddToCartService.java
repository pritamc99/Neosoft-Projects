package mypacks.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mypacks.model.OrderItem;
import mypacks.model.DeliveryAddress;
import mypacks.model.MyOrder;

public interface AddToCartService 
{
	public void addItem(int id,HttpServletRequest req);
	public boolean isUserLogin(HttpSession session);
	public boolean isItemInCart(int id,HttpSession session);
	public List<OrderItem> getOrderItem(Map<String, String> map, HttpSession session);
	public boolean addOrder(MyOrder order); 
	public boolean addDeliveryAddress(DeliveryAddress address);
	public DeliveryAddress getDeliveryAddress(String address,HttpSession httpSession);
}
