package mypacks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mypacks.dao.MyAccountDAO;
import mypacks.model.MyOrder;
import mypacks.model.OrderItem;

@Service
@Transactional
public class MyAccountServiceImpl implements MyAccountService
{
	@Autowired
	MyAccountDAO myAccountDAO;
	
	@Override
	public List<MyOrder> getOrder(int userId) 
	{
		List<MyOrder> orderList=myAccountDAO.getOrderList(userId);
		System.out.println("in");
		return orderList;
	}

	@Override
	public List<OrderItem> getOrderItem(int orderId) 
	{
		List<OrderItem> itemList=myAccountDAO.getOrderItemList(orderId);
		return itemList;
	}

}
