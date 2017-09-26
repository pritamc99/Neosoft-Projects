package mypacks.dao;

import java.util.List;

import mypacks.model.Category;
import mypacks.model.Item;
import mypacks.model.Product;

public interface AdminDAO 
{
	public void addCategory(Category category);
	public void addProduct(Product product);
	public void addItem(Item item);
	
	public void removeItem(Item item);
	
	public void updateItem(Item item);
	
	public List<Category> readCategoryOptionList();
	public List<Product> readProductOptionList(int id);
	public Category getCategoryByCatId(int id);
}
