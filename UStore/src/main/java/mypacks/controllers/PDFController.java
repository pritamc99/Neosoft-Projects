package mypacks.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mypacks.model.MyOrder;

@Controller
public class PDFController 
{
	@RequestMapping(value="/downloadPdf",method=RequestMethod.GET)
	public ModelAndView ConvertPdf(HttpSession session)
	{
		MyOrder order=(MyOrder) session.getAttribute("order");
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("InvoicePdf");
		modelAndView.addObject("order", order);
		return modelAndView;
	}
}
