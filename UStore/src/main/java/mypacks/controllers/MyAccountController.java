package mypacks.controllers;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import freemarker.core.ReturnInstruction.Return;
import mypacks.model.MyOrder;
import mypacks.model.OrderItem;
import mypacks.model.Register;
import mypacks.service.MyAccountService;

@Controller
public class MyAccountController implements DefaultControllerInterface
{
	@Autowired
	MyAccountService myAccountService;
	
	@RequestMapping(value="/myaccount",method=RequestMethod.GET)
	public ModelAndView ViewMyAccount(HttpServletRequest req,Model model)
	{
		String islogin="true"; 
		Register user=null;
		HttpSession session=req.getSession();
		if(islogin.equals((String) session.getAttribute("isUserLogin")))
		{
			user=(Register) session.getAttribute("user");
			model.addAttribute("user",user);
			return new ModelAndView("myAccount");
		}
		else
			return new ModelAndView("index");
	}
	
	@RequestMapping(value="/showMyOrders",method=RequestMethod.POST)
	public @ResponseBody List<MyOrder> getOrderList(@RequestParam("userId") int id)
	{
		System.out.println("in");
		List<MyOrder> orderList=myAccountService.getOrder(id);
		System.out.println("in last");
		return orderList;
	}
	
	@RequestMapping(value="/getOrderItems",method=RequestMethod.POST)
	public @ResponseBody List<OrderItem> viewOrderItems(@RequestParam("orderId") int orderId)
	{
		List<OrderItem> itemList=myAccountService.getOrderItem(orderId);
		return itemList;
	}
}
