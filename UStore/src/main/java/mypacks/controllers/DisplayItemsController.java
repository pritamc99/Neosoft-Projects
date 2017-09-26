package mypacks.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import mypacks.model.Item;
import mypacks.service.DisplayItemsService;

@Controller
public class DisplayItemsController implements DefaultControllerInterface
{
	@Autowired
	DisplayItemsService displayItemsService;
	
	@RequestMapping(value="/displayItems",method=RequestMethod.POST)
	public @ResponseBody List<Item> displayItems(@RequestParam int id,Model model)
	{
		List<Item> itemList=displayItemsService.getItemList(id);
		return itemList;
	}
}
