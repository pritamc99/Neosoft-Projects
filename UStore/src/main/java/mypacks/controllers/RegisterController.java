package mypacks.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

import mypacks.model.Register;
import mypacks.service.LoginService;
import mypacks.service.RegisterService;

@Controller
public class RegisterController implements DefaultControllerInterface
{
	@Autowired
	RegisterService registerService;
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value="sineUp",method=RequestMethod.POST)
	public ModelAndView register(@Valid @ModelAttribute("register") Register register, BindingResult result)
	{
		boolean flag=false;
		
		// checking server side validation
		if(result.hasErrors())
			return new ModelAndView("register");
		
		Register encryptedUser=registerService.encryptUserPassword(register);
		flag=registerService.saveUser(encryptedUser);
		
		if(flag==true)
			return new ModelAndView("index");
		else
			return new ModelAndView("register");
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
    public String viewRegister(Model model) 
	{
		 model.addAttribute("register",new Register());
		return "register";
    }
	
	@RequestMapping(value="/saveSocialUser",method = RequestMethod.POST)
	public @ResponseBody void saveSocialuser(@RequestParam("name") String name,@RequestParam("email") String email,HttpSession session)
	{
	System.out.println(name+" "+email);
		Register socialUser=new Register();
		socialUser.setFirstName(name);
		socialUser.setLastName("      ");
		socialUser.setAddress("     ");
		socialUser.setUserName("      ");
		socialUser.setPassword("      ");
		socialUser.setEmailId(email);
		session.setAttribute("socialUserEmail", email);
		boolean isUserRegistered=registerService.checkSocialUserRegistered(socialUser);
	System.out.println(isUserRegistered);
		if(isUserRegistered==false)
		{
			boolean saveSocialUser=registerService.saveSocialUser(socialUser);
	System.out.println(saveSocialUser);
		}
		Register user=loginService.getSocialUser(email);
		session.setAttribute("user",user);
	}
}
