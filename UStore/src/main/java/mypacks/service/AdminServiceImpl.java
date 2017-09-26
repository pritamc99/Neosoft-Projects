package mypacks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mypacks.dao.AdminDAO;
import mypacks.dao.DisplayItemsDAO;
import mypacks.model.Category;
import mypacks.model.Item;
import mypacks.model.Product;

@Service
@Transactional
public class AdminServiceImpl implements AdminService
{
	@Autowired
	AdminDAO adminDAOImpl;
	
	@Autowired
	DisplayItemsDAO displayItemsDAOImpl;
	
	@Override
	public void addNewCategory(Category category) 
	{
		adminDAOImpl.addCategory(category);
	}

	@Override
	public void addNewProduct(Product product) 
	{
		Category category=adminDAOImpl.getCategoryByCatId(product.getCategory().getCatId());
		product.setCategory(category);
		
		System.out.println("in add prod serv");
		adminDAOImpl.addProduct(product);
	}

	@Override
	public List<Category> getCategoryOptionList() 
	{
		List<Category> catList = adminDAOImpl.readCategoryOptionList();
		return catList;
	}

	@Override
	public List<Product> getProductOptionList(int id) 
	{
		System.out.println(id);
		List<Product> productList=adminDAOImpl.readProductOptionList(id);
		return productList;
	}

	@Override
	public void addNewItem(Item item) 
	{
		item.setItemStatus(true);
		adminDAOImpl.addItem(item);
	}

	@Override
	public List<Item> getItemList() 
	{
		List<Item> itemList=displayItemsDAOImpl.readItems();
		return itemList;
	}

	@Override
	public void removeItem(Item item) 
	{
		item.setItemStatus(false);
		adminDAOImpl.removeItem(item);
	}

	@Override
	public void updateItem(Item item) 
	{
		item.setItemStatus(true);
		adminDAOImpl.removeItem(item);
	}
	
}
