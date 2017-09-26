package mypacks.service;

import java.util.List;

import mypacks.model.Category;
import mypacks.model.Product;

public interface MenuService 
{
	public List<Category> getCategory();
	public List<Product> getProductList(int categoryName);
}
