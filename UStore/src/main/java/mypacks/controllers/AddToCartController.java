package mypacks.controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.criteria.internal.OrderImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import mypacks.model.DeliveryAddress;
import mypacks.model.Item;
import mypacks.model.Login;
import mypacks.model.MyOrder;
import mypacks.model.OrderItem;
import mypacks.model.Register;
import mypacks.service.AddToCartServiceImpl;
import mypacks.service.LoginService;

@Controller

public class AddToCartController implements DefaultControllerInterface
{
	@Autowired
	AddToCartServiceImpl addToCartServiceImpl;
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value="/addToCart",method=RequestMethod.POST)
	public @ResponseBody String addToCart(HttpServletRequest req,Model model)
	{
		
		HttpSession session=req.getSession();
		String user=(String) session.getAttribute("isUserLogin");
		if(user != null)
		{
			int id=Integer.parseInt(req.getParameter("id"));
			if(addToCartServiceImpl.isItemInCart(id,session))
				return "item already exist in cart";
			addToCartServiceImpl.addItem(id,req);
			return "item added successfully";
		}
		else{
			return "please login first";
		}
	}
	
	@RequestMapping(value="/myCart",method=RequestMethod.GET)
	public ModelAndView viewCart(Model model,HttpSession session)
	{
		List<Item> cartList=(List<Item>) session.getAttribute("cartList");
		model.addAttribute("cartList",cartList);
		return new ModelAndView("cart");
	}
	
	@RequestMapping(value="/removeItemFromCart",method=RequestMethod.POST)
	public @ResponseBody void removeItemFromCart(@RequestParam int id,HttpServletRequest req,Model model){
		Item rmitem=null;
		HttpSession session=req.getSession();
		List<Item> itemList=(List<Item>) session.getAttribute("cartList");
		Iterator< Item> itr=itemList.iterator();
		while(itr.hasNext())
		{
			Item item=itr.next();
			if(item.getItemId()==id)
				rmitem=item;
		}
		itemList.remove(rmitem);
		model.addAttribute("cartList", itemList);
	}
	
	@RequestMapping(value="/checkOut",method=RequestMethod.POST)
	public @ResponseBody String checkOut(@RequestParam Map<String,String> map, HttpSession session)
	{
		MyOrder order=new MyOrder();
		List<OrderItem> a = addToCartServiceImpl.getOrderItem(map, session);
		for (OrderItem orderItem : a) {
			orderItem.setOrderId(order);
		}
		order.setOrderItem(a);
		order.setOrderAmount(Integer.parseInt(map.get("estimatedTotal")));
		order.setUser((Register)session.getAttribute("user"));
		/*if(addToCartServiceImpl.addOrder(order))
		return "true";
		*/
		session.setAttribute("order", order);
		return "false";
	}
	
	@RequestMapping(value="/orderPage")
	public ModelAndView showOrderPage(HttpSession session)
	{
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("orderPage");
		MyOrder order=(MyOrder) session.getAttribute("order");
		modelAndView.addObject(order);
		return modelAndView;
	}
	
	@RequestMapping(value="/placeOrder",method=RequestMethod.POST)
	public @ResponseBody String placeOrder(@RequestParam String deliveryAddress,HttpSession session){
		MyOrder order=(MyOrder) session.getAttribute("order");

		DeliveryAddress delAdd=addToCartServiceImpl.getDeliveryAddress(deliveryAddress,session);
		order.setDeliveryAddress(delAdd);
		order.setOrderDate(LocalDate.now());
		order.setExpectedDeliveryDate(LocalDate.now().plusDays(5));
		if(addToCartServiceImpl.addOrder(order))
		{
			List<Item> list=(List<Item>) session.getAttribute("cartList");
			list.clear();
			session.setAttribute("cartList", list);
			session.setAttribute("order",order);
			return "true";
		}
		return "false";
	}
	
	@RequestMapping(value="/saveAddress",method=RequestMethod.POST)
	public @ResponseBody boolean saveAddress(@RequestParam String deliveryAddress,HttpSession session)
	{
		
		boolean flag=false;
		Register user=(Register) session.getAttribute("user");
		DeliveryAddress delAdd=new DeliveryAddress();
		delAdd.setDeliveryAddress(deliveryAddress);
		delAdd.setUserId(user);
		flag=addToCartServiceImpl.addDeliveryAddress(delAdd);
		if(user.getRoleName().getRoleId()!=3)
			user=loginService.getUser((Login)session.getAttribute("loginfo"));
		else
		{
			String email=(String) session.getAttribute("socialUserEmail");
			user=loginService.getSocialUser(email);
		}
		System.out.println("ithe yetoy");
		session.setAttribute("user",user);
		System.out.println("ithe pan yetoy");
		return flag;
	}
	
	@RequestMapping(value="/orderInvoice",method=RequestMethod.GET)
	public ModelAndView showOrderInvoice()
	{
		
		return new ModelAndView("orderInvoice");
	}
	
}
