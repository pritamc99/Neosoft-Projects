package mypacks.service;

import java.util.List;

import mypacks.model.Category;
import mypacks.model.Item;
import mypacks.model.Product;

public interface AdminService 
{
	public List<Item> getItemList();
	
	public void addNewCategory(Category category);
	public void addNewProduct(Product product);
	public void addNewItem(Item item);
	
	public void removeItem(Item item);
	
	public void updateItem(Item item);
	
	public List<Category> getCategoryOptionList();
	public List<Product> getProductOptionList(int id);
}
