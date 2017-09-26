package mypacks.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mypacks.model.Category;
import mypacks.model.Product;

@Repository
public class MenuDAOImpl implements MenuDAO
{
	@Autowired
	SessionFactory sessionFactory;

	
	@Override
	public List<Category> getCategoryList() 
	{
		Criteria cr=sessionFactory.getCurrentSession().createCriteria(Category.class).setProjection(
				Projections.projectionList().add(Projections.property("catId"), "catId").
				add(Projections.property("categoryName"), "categoryName"))
				.setResultTransformer(Transformers.aliasToBean(Category.class));
		
		List<Category> catList=cr.list();
		System.out.println("Phile");
		/*List<Category> catList=(List<Category>)sessionFactory.getCurrentSession().createCriteria(Category.class).list();*/
		
		System.out.println("nantar");
		return catList;
	}


	@Override
	public List<Product> readProductList(int catId) 
	{
		System.out.println("in daoimpl"+catId);
		/*List<Product> prodList=sessionFactory.getCurrentSession().createQuery("from Product where category='"+catId+"'").list();
		*/
		
		Criteria cr=sessionFactory.getCurrentSession().createCriteria(Product.class)
				.createAlias("category", "category").setProjection(
				Projections.projectionList().add(Projections.property("productId"),"productId")
				.add(Projections.property("productName"),"productName"))
				.setResultTransformer(Transformers.aliasToBean(Product.class));
		cr.add(Restrictions.like("category.catId",catId));
		
		List<Product> prodList=cr.list();
		Iterator itr1=prodList.iterator();
		while(itr1.hasNext())
		{
			Product category=(Product)itr1.next();
			catId=category.getProductId();
			System.out.println(catId);
		}
		return prodList;
	}

}
