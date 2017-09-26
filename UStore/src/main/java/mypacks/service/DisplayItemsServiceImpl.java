package mypacks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mypacks.dao.DisplayItemsDAO;
import mypacks.model.Item;
import mypacks.model.OrderItem;

@Service
@Transactional
public class DisplayItemsServiceImpl implements DisplayItemsService
{

	@Autowired
	DisplayItemsDAO displayItemsDAO;
	
	@Override
	public List<Item> getItemList(int id) 
	{
		List<Item> itemList=displayItemsDAO.readItems(id);
		return itemList;
	}

	@Override
	public List<Item> getLetestItemList() 
	{
		List<Item> letestItemList=displayItemsDAO.readLetestItems();
		return letestItemList;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List getTopSellerList() 
	{
		List<OrderItem> topSellerList=displayItemsDAO.readTopsellerItems();
		return topSellerList;
	}

	@Override
	public Item getItem(int id) 
	{
		Item item=displayItemsDAO.readItem(id);
		return item;
	}

}
