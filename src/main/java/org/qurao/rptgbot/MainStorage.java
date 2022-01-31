package org.qurao.rptgbot;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class MainStorage {

	private HashMap<Integer, Location> locations;
	private HashMap<Integer, Item> items;
	private int locationsCounter;
	private int itemsCounter;
	
	MainStorage(){
		locations = new HashMap<Integer, Location>();
		items = new HashMap<Integer, Item>();
		locationsCounter = 0;
		itemsCounter = 0;
	}
	
	public void addLocation(String name, String description) {
		locations.put(locationsCounter, new Location(name, description));
		locationsCounter++;
	}
	
	public Location getLocationById(int id) {
		return locations.getOrDefault(id, null);
	}
	
	public Set<Entry<Integer, Location>> getLocationsEntry() {
		return locations.entrySet();
	}
	
	public void addItem(String name, String description, Stats stats, int capacity) {
		items.put(itemsCounter, new Item(name, description,
				stats, capacity));
		itemsCounter++;
	}
	
	public boolean isItemExists(int id) {
		if(id < 0 || id >= items.size()) {
			return false;
		}else {
			return true;
		}
	}
	
	public Item getItemByID(int id) {
		return items.get(id);
	}
	
	public Item createItemById(int id) {
		return new Item(items.get(id));
	}
	
	public Set<Entry<Integer, Item>> getItemsEntry() {
		return items.entrySet();
	}
	
	public int getLocationsAmount() {
		return locations.size();
	}
	
	public int getItemsAmount() {
		return items.size();
	}
	
	public void freeItems() {
		items.clear();
		itemsCounter = 0;
	}
	
}
