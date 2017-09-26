package mypacks.dao;

import java.util.List;
import java.util.Properties;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mypacks.model.Item;
import mypacks.model.OrderItem;
import mypacks.service.DisplayItemsService;

@Repository
public class DisplayItemsDAOImpl implements DisplayItemsDAO
{
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<Item> readItems(int id) 
	{
		String sid=Integer.toString(id);
		Criteria cr=sessionFactory.getCurrentSession().createCriteria(Item.class)
				.add(Restrictions.eq("productType", sid))
				.add(Restrictions.eq("itemStatus", true));
				/*.createAlias("product", "product");
		cr.add(Restrictions.like("product.productId", id));*/
		List<Item> itemList=cr.list();
		return itemList;
	}

	@Override
	public List<Item> readLetestItems() 
	{
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Item.class);
		criteria.addOrder(Order.desc("itemId"));
		criteria.setMaxResults(5);
		List<Item> letestItem=criteria.list();
		return letestItem;
	}

	
	@Override
	public List readTopsellerItems() 
	{
		 ProjectionList projections = Projections.projectionList();
			projections.add(Projections.sum("quantity").as("quantity"));
			projections.add(Projections.groupProperty("item").as("item"));
			
			Criteria criteria = sessionFactory.getCurrentSession()
								.createCriteria(OrderItem.class)
								.setProjection(projections)
								.addOrder(Order.desc("quantity"));		
			criteria.setMaxResults(3);
			List<OrderItem> results = criteria.setResultTransformer(new AliasToBeanResultTransformer(OrderItem.class)).list();
			return results;	
	}

	@Override
	public List<Item> readItems() 
	{
		List<Item> itemList=sessionFactory.getCurrentSession().createCriteria(Item.class).add(Restrictions.eq("itemStatus", true)).list();
		return itemList;
	}

	@Override
	public Item readItem(int id) 
	{
		Item item=(Item) sessionFactory.getCurrentSession().createCriteria(Item.class).add(Restrictions.idEq(id)).uniqueResult();
		return item;
	}

}
