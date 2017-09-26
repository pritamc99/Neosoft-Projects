package mypacks.controllers;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.SecurityContextProvider;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionAttributeStore;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.SessionStatusMethodArgumentResolver;
import org.springframework.web.servlet.ModelAndView;

import mypacks.model.Item;
import mypacks.model.Login;
import mypacks.model.Register;
import mypacks.service.LoginService;

@Controller
@SessionAttributes(value={"userName","login1","isUserLogin","cartList","user","myMap","loginfo"})
public class LoginController implements DefaultControllerInterface
{
	@Autowired
	LoginService loginService;
	
	List<Item> cartList=new ArrayList<Item>();
	Map<Integer,Integer> myMap=new HashMap();
	
	
	@RequestMapping(value="/signIn", method=RequestMethod.GET)
	public ModelAndView fbLogin(@RequestParam("name") String name, Model model,HttpSession session)
	{
		model.addAttribute("login1", "Logout");
		model.addAttribute("userName", name);
		model.addAttribute("isUserLogin", "true");
		model.addAttribute("cartList",cartList);
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="/signIn", method=RequestMethod.POST)
	public ModelAndView checkLogin(@Valid @ModelAttribute("login") Login login, BindingResult result,ModelMap model/*,HttpSession session,HttpServletRequest req*/)
	{
		
		if(result.hasErrors())
		{
			model.addAttribute("login1", "Login");
			return new ModelAndView("login");
		}
		System.out.println(login.getPassword());
		boolean flag=loginService.checkLogin(login);
		if(flag==true)
		{
			Register user1=loginService.getUser(login);
			String user="Hi "+login.getUserName();
			model.addAttribute("loginfo",login);
			model.addAttribute("myMap",myMap);
			model.addAttribute("user",user1);
			model.addAttribute("userName", user);
			model.addAttribute("login1", "Logout");
			model.addAttribute("isUserLogin", "true");
			model.addAttribute("cartList",cartList);
			return new ModelAndView("index");
		}
		else
		{
			model.addAttribute("login1", "Login");
			model.addAttribute("error","No such User");
			return new ModelAndView("login");
		}
	}
	
	/*@RequestMapping(value="/Logout",method=RequestMethod.GET)
	public ModelAndView logout(ModelMap model,SessionStatus sessionStatus,HttpSession session,HttpServletRequest req,Model model)
	{
		
		sessionStatus.setComplete();HttpSession session
		model.addAttribute("login1", "Login");
		return new ModelAndView("index");
		
	}*/
	
	
}

@Controller
class LogoutController
{
	@RequestMapping(value="/Logout",method=RequestMethod.GET)
	public ModelAndView logout(ModelMap model,SessionStatus sessionStatus,HttpSession session/*,HttpServletRequest req,Model model*/)
	{
		
		session.invalidate();
		model.addAttribute("login1", "Login");
		return new ModelAndView("index");
	}	
}
