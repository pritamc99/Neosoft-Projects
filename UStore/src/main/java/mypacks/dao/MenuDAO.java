package mypacks.dao;

import java.util.List;

import mypacks.model.Category;
import mypacks.model.Product;

public interface MenuDAO 
{
	public List<Category> getCategoryList();
	public List<Product> readProductList(int catId);
}
