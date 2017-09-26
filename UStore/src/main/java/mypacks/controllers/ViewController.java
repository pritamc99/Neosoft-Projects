package mypacks.controllers;

import java.util.List;

import javax.jws.WebParam.Mode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mypacks.dao.LoginDAO;
import mypacks.model.Category;
import mypacks.model.Item;
import mypacks.model.Login;
import mypacks.model.OrderItem;
import mypacks.model.Product;
import mypacks.model.Register;
import mypacks.service.AdminService;
import mypacks.service.DisplayItemsService;



@Controller
public class ViewController implements DefaultControllerInterface
{
	@Autowired
	AdminService adminServiceImpl;
	
	@Autowired
	LoginDAO loginDAO;
	
	@Autowired
	DisplayItemsService displayItemServiceImpl;
	
	@ModelAttribute
	public void disp(Model model)
	{
		List<Item> letestItem=displayItemServiceImpl.getLetestItemList();
		List topSellerItem=displayItemServiceImpl.getTopSellerList();
		model.addAttribute("letestItem",letestItem);
		model.addAttribute("topSellerItem",topSellerItem);
		model.addAttribute("login1", "Login");
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
    public String init(Model model) 
	{
		//model.addAttribute("login1", "Login");
		return "index";
    }
	
	@RequestMapping(value = "/Login", method = RequestMethod.GET)
    public String viewLogin(Model model) 
	{
		//model.addAttribute("login1", "Login");
		model.addAttribute("login",new Login());
		return "login";
    }
}
