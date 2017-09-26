package mypacks.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

import mypacks.dao.AddToCartDAO;
import mypacks.model.DeliveryAddress;
import mypacks.model.Item;
import mypacks.model.MyOrder;
import mypacks.model.OrderItem;
import mypacks.model.Register;

@Service
@Transactional
public class AddToCartServiceImpl implements AddToCartService
{
	
	@Autowired
	AddToCartDAO addToCartDAOImpl;

	@Override
	public void addItem(int id,HttpServletRequest req) 
	{
		HttpSession session=req.getSession();
		List<Item> cartList=(ArrayList<Item>)session.getAttribute("cartList");
		Item item=addToCartDAOImpl.getItemToInsert(id);
		cartList.add(item);
		session.setAttribute("cartList", cartList);
	}

	@Override
	public boolean isUserLogin(HttpSession session) 
	{
		
		/*HttpSession session=req.getSession();*/
		String isUserLogin=(String) session.getAttribute("isuserLogin");
		System.out.println(isUserLogin);
		if(isUserLogin==null)
			return false;
		else
			return true;
	}

	@Override
	public boolean isItemInCart(int id,HttpSession session) 
	{
		boolean isItemInCart=false;
		List<Item> cartList=(ArrayList<Item>)session.getAttribute("cartList");
		Item item=addToCartDAOImpl.getItemToInsert(id);
		for(Item item1:cartList)
		{
			if(item1.getItemId()==item.getItemId())
			{
				isItemInCart=true;
				break;
			}
		}
		return isItemInCart;
	}

	@Override
	public List<OrderItem> getOrderItem(Map<String, String> map, HttpSession session) 
	{
		List<OrderItem> orderItemList=new ArrayList<>();
		
		for(Map.Entry<String, String> entry:map.entrySet())
		{
			OrderItem orderItem=new OrderItem();
			if(!(entry.getKey().equals("estimatedTotal")))
			{	
			Item item=addToCartDAOImpl.getItemToInsert(Integer.parseInt(entry.getKey()));
			long quantity=Long.parseLong(entry.getValue());
			orderItem.setQuantity(quantity);
			orderItem.setSubTotal(quantity*item.getPrice());
			orderItem.setItem(item);
			orderItemList.add(orderItem);
			}
		}
		return orderItemList;
	}

	@Override	// TODO Auto-generated method stub
	
	public boolean addOrder(MyOrder order) 
	{
		if(addToCartDAOImpl.insertOrder(order))
		{
			List<OrderItem> list=order.getOrderItem();
			for(OrderItem item:list)
			{
				long quantity=item.getQuantity();
				item.getItem().setQuantity(item.getItem().getQuantity()-quantity);
				addToCartDAOImpl.updateItemQuantity(item.getItem());
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean addDeliveryAddress(DeliveryAddress address) 
	{
		System.out.println("in service");
		addToCartDAOImpl.insertDeliveryAddress(address);
		return true;
	}

	@Override
	public DeliveryAddress getDeliveryAddress(String address,HttpSession httpSession) 
	{
		DeliveryAddress delAdd=null;
		List<DeliveryAddress> deliveryAddress=addToCartDAOImpl.getDeliveryAddress(address);
		Register user=(Register) httpSession.getAttribute("user");
		
		for(DeliveryAddress add:deliveryAddress)
		{
			if(user.getRegisterId()==add.getUserId().getRegisterId())
			{
				delAdd=add;
			}
		}
		return delAdd;
	}
	
}
