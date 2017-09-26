package mypacks.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.support.SessionStatus;

import mypacks.model.Item;
import mypacks.service.DisplayItemsService;

public interface DefaultControllerInterface 
{
	@ModelAttribute
	default void commonAttribute(Model model,HttpSession httpSession)
	{
		String t=(String) httpSession.getAttribute("isUserLogin");
		if(t!=null)
			model.addAttribute("login1","Logout");
		else			
			model.addAttribute("login1", "Login");
				
	}
}
