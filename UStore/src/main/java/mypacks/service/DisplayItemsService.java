package mypacks.service;

import java.util.List;

import mypacks.model.Item;
import mypacks.model.OrderItem;

public interface DisplayItemsService 
{
	public Item getItem(int id);
	public List<Item> getItemList(int id);
	public List<Item> getLetestItemList();
	public List getTopSellerList();
}
