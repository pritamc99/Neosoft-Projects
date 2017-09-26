package mypacks.controllers;



import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mypacks.model.Category;
import mypacks.model.Product;
import mypacks.service.MenuServiceImpl;



@Controller
public class MenuController implements DefaultControllerInterface
{
	@Autowired
	MenuServiceImpl menuServiceImpl;
	
	@RequestMapping(value="/category",method=RequestMethod.POST)	
	public @ResponseBody List<Category> viewCategory()
	{
		List<Category> catList= menuServiceImpl.getCategory();
		Iterator<Category> itr=catList.iterator();
		while(itr.hasNext())
		{
			Category cat=itr.next();
			System.out.println(cat);
		}
		return catList;
	}
	
	
	@RequestMapping(value="/product",method=RequestMethod.POST)
	public @ResponseBody List<Product> viewProduct(HttpServletRequest req)
	{
		int itemName=Integer.parseInt(req.getParameter("name"));
		List<Product> prodList=menuServiceImpl.getProductList(itemName);
		
		return prodList;
	}
}
