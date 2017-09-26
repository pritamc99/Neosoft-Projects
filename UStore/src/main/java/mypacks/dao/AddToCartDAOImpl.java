package mypacks.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mypacks.model.DeliveryAddress;
import mypacks.model.Item;
import mypacks.model.MyOrder;

@Repository
public class AddToCartDAOImpl implements AddToCartDAO
{
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Item getItemToInsert(int id) 
	{
		// TODO Auto-generated method stub
		Criteria cr=sessionFactory.getCurrentSession().createCriteria(Item.class);
		Item item=(Item) cr.add(Restrictions.eq("itemId", id)).uniqueResult();
		return item;
	}

	@Override
	public boolean insertOrder(MyOrder order) 
	{
		System.out.println("ye hai save ka return type"+sessionFactory.getCurrentSession().save(order));
		return true;
	}

	@Override
	public boolean insertDeliveryAddress(DeliveryAddress address) 
	{
		System.out.println("in dao");
		sessionFactory.getCurrentSession().save(address);
		return true;
	}

	@Override
	public List<DeliveryAddress> getDeliveryAddress(String deliveryAddress) {
		List<DeliveryAddress> address=(List<DeliveryAddress>) sessionFactory.getCurrentSession().createCriteria(DeliveryAddress.class).add(Restrictions.like("deliveryAddress", deliveryAddress)).list();
		return address;
	}

	@Override
	public boolean updateItemQuantity(Item item) 
	{
		sessionFactory.getCurrentSession().update(item);
		return true;
	}

}
