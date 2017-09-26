package mypacks.dao;

import java.util.List;

import mypacks.model.Item;
import mypacks.model.OrderItem;

public interface DisplayItemsDAO 
{
	public Item readItem(int id);
	public List<Item> readItems();
	public List<Item> readItems(int id);
	public List<Item> readLetestItems();
	public List readTopsellerItems();
}
