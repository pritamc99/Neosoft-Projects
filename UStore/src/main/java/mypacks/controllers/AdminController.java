package mypacks.controllers;


import java.util.List;

import javax.validation.Valid;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import mypacks.dao.DisplayItemsDAO;
import mypacks.model.Category;
import mypacks.model.Item;
import mypacks.model.Product;
import mypacks.service.AdminService;
import mypacks.service.DisplayItemsService;

@Controller
public class AdminController implements DefaultControllerInterface
{
	@Autowired
	AdminService adminServiceImpl;
	
	@Autowired 
	DisplayItemsService displayItemServiceImpl;
	
	@ModelAttribute
	public void common(Model model)
	{
		List<Category> c = adminServiceImpl.getCategoryOptionList();
		model.addAttribute("categoryList", c);
		List<Item> itemList=adminServiceImpl.getItemList();
		model.addAttribute("itemList",itemList);
		
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String viewAdmin(Model model) 
	{
		//model.addAttribute("admin",new Register());
		model.addAttribute("login1", "AdminLogout");
		return "admin";
    }
	
	@RequestMapping(value="/AdminLogout",method=RequestMethod.GET)
	public String logoutAdmin(Model model)
	{
		model.addAttribute("login1", "Login");
		return "index";
	}
	
	@RequestMapping(value = "/viewAddCategory", method = RequestMethod.GET)
    public String viewAddCategory(Model model) 
	{
		model.addAttribute("category",new Category());
		model.addAttribute("login1", "AdminLogout");
		return "addCategory";
    }
	
	@RequestMapping(value = "/viewAddProduct", method = RequestMethod.GET)
    public String viewAddProduct(Model model) 
	{
		/*List<Category> c = adminServiceImpl.getCategoryOptionList();
		model.addAttribute("categoryList", c);*/
		model.addAttribute("product",new Product());
		model.addAttribute("login1", "AdminLogout");
		return "addProduct";
    }
	
	@RequestMapping(value = "/viewAddItem", method = RequestMethod.GET)
    public String viewAddItem(Model model) 
	{
		/*List<Category> c = adminServiceImpl.getCategoryOptionList();
		model.addAttribute("categoryList", c);*/
		
		model.addAttribute("item",new Item());
		model.addAttribute("login1", "AdminLogout");
		return "addItem";
    }
	
	
	
	@RequestMapping(value="productSelectList",method=RequestMethod.POST)
	public @ResponseBody List<Product> display(@RequestParam int id)
	{
		List<Product> productList=adminServiceImpl.getProductOptionList(id);
		return productList;
	}
	
	@RequestMapping(value="/addCategory",method=RequestMethod.POST)
	public ModelAndView addCategory(@Valid @ModelAttribute("category") Category category,Model model,BindingResult result)
	{
		model.addAttribute("login1", "AdminLogout");
		if(result.hasErrors())
			return new ModelAndView("addCategory");
		adminServiceImpl.addNewCategory(category);
			return new ModelAndView("admin");
	}
	
	
	@RequestMapping(value="/addProduct",method=RequestMethod.POST)
	public ModelAndView addProduct(@Valid @ModelAttribute("product") Product product,Model model,BindingResult result)
	{
		model.addAttribute("login1", "AdminLogout");
		if(result.hasErrors())
			return new ModelAndView("addProduct");
		adminServiceImpl.addNewProduct(product);
		return new ModelAndView("admin");
	}
	
	@RequestMapping(value="/addItem",method=RequestMethod.POST)
	public ModelAndView addItem(@ModelAttribute("item") Item item,Model model,BindingResult result)
	{
		adminServiceImpl.addNewItem(item);
		model.addAttribute("login1", "AdminLogout");
		return new ModelAndView("admin");
	}
	
	@RequestMapping(value="/viewRemoveItem",method=RequestMethod.GET)
	public ModelAndView viewRemoveItem(Model model)
	{
		model.addAttribute("login1", "AdminLogout");
		return new ModelAndView("removeItem");
	}
	
	@RequestMapping(value="/removeItem",method=RequestMethod.POST)
	public @ResponseBody String removeItem(@RequestParam int id)
	{
		System.out.println("alalala");
		Item item=displayItemServiceImpl.getItem(id);
		adminServiceImpl.removeItem(item);
		return "kidhar nahi jana";
	}
	
	@RequestMapping(value="/viewUpdateItem",method=RequestMethod.GET)
	public ModelAndView viewUpdateItem(Model model)
	{
		model.addAttribute("item",new Item());
		model.addAttribute("login1", "AdminLogout");
		return new ModelAndView("updateItem");
	}
	
	@RequestMapping(value="/updateItem",method=RequestMethod.POST)
	public @ResponseBody Item updateItemFields(@RequestParam int id,Model model)
	{
		System.out.println("alalala");
		Item item=displayItemServiceImpl.getItem(id);
		model.addAttribute("login1", "AdminLogout");
		return item;
	}
	
	@RequestMapping(value="/finalUpdateitem",method=RequestMethod.POST)
	public ModelAndView updateItem(@ModelAttribute Item item,Model model, BindingResult result)
	{
		adminServiceImpl.updateItem(item);
		List<Item> itemList=adminServiceImpl.getItemList();
		model.addAttribute("itemList",itemList);
		model.addAttribute("login1", "AdminLogout");
		return new ModelAndView("updateItem");
	}
}
