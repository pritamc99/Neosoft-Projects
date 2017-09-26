package mypacks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mypacks.dao.MenuDAO;
import mypacks.model.Category;
import mypacks.model.Product;

@Service
@Transactional
public class MenuServiceImpl implements MenuService
{
	@Autowired
	MenuDAO menuDAOImpl;
	
	@Override
	public List<Category> getCategory() 
	{
		System.out.println("Service madhe");
		List<Category> catList= menuDAOImpl.getCategoryList();
		return catList;
	}

	@Override
	public List<Product> getProductList(int catId) 
	{
		System.out.println("in service");
		List<Product> prodList=menuDAOImpl.readProductList(catId);
		return prodList;
	}

}
