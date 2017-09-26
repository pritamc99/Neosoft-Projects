package mypacks.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mypacks.model.Category;
import mypacks.model.Item;
import mypacks.model.Product;

@Repository
public class AdminDAOImpl implements AdminDAO {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void addCategory(Category category) {
		try {
			sessionFactory.getCurrentSession().save(category);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addProduct(Product product) {
		System.out.println("in add prod dao");

		sessionFactory.getCurrentSession().save(product);

	}

	@Override
	public List<Category> readCategoryOptionList() {
		/*
		 * List<Category>
		 * catList=sessionFactory.getCurrentSession().createCriteria(Category.
		 * class).list();
		 */
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(Category.class)
				.setProjection(Projections.projectionList().add(Projections.property("catId"), "catId")
						.add(Projections.property("categoryName"), "categoryName"))
				.setResultTransformer(Transformers.aliasToBean(Category.class));
		List<Category> catList = cr.list();

		return catList;
	}

	@Override
	public Category getCategoryByCatId(int id) {
		Category cat = (Category) sessionFactory.getCurrentSession().createCriteria(Category.class)
				.add(Restrictions.eq("catId", id)).uniqueResult();
		return cat;
	}

	@Override
	public List<Product> readProductOptionList(int id) 
	{
		System.out.println(id);
		Criteria cr=sessionFactory.getCurrentSession().createCriteria(Product.class)
				.createAlias("category", "category")
				.setProjection(Projections.projectionList()
				.add(Projections.property("productId"),"productId")
				.add(Projections.property("productName"),"productName"))
				.setResultTransformer(Transformers.aliasToBean(Product.class));
		cr.add(Restrictions.like("category.catId", id));
				
		List<Product> productList=cr.list();
		return productList;
	}

	@Override
	public void addItem(Item item) 
	{
		sessionFactory.getCurrentSession().save(item);
		
	}

	@Override
	public void removeItem(Item item) 
	{
		sessionFactory.getCurrentSession().update(item);
	}

	@Override
	public void updateItem(Item item) 
	{
		sessionFactory.getCurrentSession().update(item);
	}

}
