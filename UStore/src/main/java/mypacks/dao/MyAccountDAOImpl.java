package mypacks.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mypacks.model.MyOrder;
import mypacks.model.OrderItem;
import mypacks.model.Product;

@Repository
public class MyAccountDAOImpl implements MyAccountDAO
{
	@Autowired 
	SessionFactory sessionFactory;
	
	@Override
	public List<MyOrder> getOrderList(int userId) 
	{
		Criteria criteria=sessionFactory.getCurrentSession()
							.createCriteria(MyOrder.class)
							.createAlias("user", "user")
							/*.createAlias("orderItem", "orderItem")*/
							.setProjection(Projections.projectionList()
									.add(Projections.property("orderId"),"orderId")
									.add(Projections.property("orderAmount"),"orderAmount"))
									/*.add(Projections.property("orderItem"),"orderItem")*/
									/*.add(Projections.property("orderItem.item"),"orderItem")*/
									//.add(Projections.property("orderAmount"),"orderAmount"))
									.setResultTransformer(Transformers.aliasToBean(MyOrder.class));
		criteria.add(Restrictions.eq("user.registerId", userId));
		List<MyOrder> orderList=criteria.list();
		for(MyOrder order:orderList)
		{
			System.out.println(order.getOrderId()+" "+order.getOrderAmount());
		}
		System.out.println("in");
		return orderList;
	}

	@Override
	public List<OrderItem> getOrderItemList(int orderId) 
	{
		Criteria criteria=sessionFactory.getCurrentSession()
								.createCriteria(OrderItem.class)
								.createAlias("orderId", "orderId")
								.setProjection(Projections.projectionList()
								.add(Projections.property("orderItemId"),"orderItemId")
								.add(Projections.property("quantity"),"quantity")
								.add(Projections.property("subTotal"),"subTotal")
								.add(Projections.property("item"),"item"))
								.setResultTransformer(Transformers.aliasToBean(OrderItem.class));
		criteria.add(Restrictions.eq("orderId.orderId", orderId));
		List<OrderItem> itemList=criteria.list();					
		for(OrderItem order:itemList)
		{
			System.out.println(order.getOrderItemId()+" "+order.getQuantity());
		}
		return itemList;
	}

}
