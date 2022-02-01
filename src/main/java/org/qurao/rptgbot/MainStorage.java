package org.qurao.rptgbot;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class MainStorage {

	private HashMap<Integer, Location> locations;
	private HashMap<Integer, Item> items;
	private HashMap<Integer, Feature> features;
	private int locationsCounter;
	private int itemsCounter;
	private int featuresCounter;
	
	MainStorage(){
		locations = new HashMap<Integer, Location>();
		items = new HashMap<Integer, Item>();
		features = new HashMap<Integer, Feature>();
		locationsCounter = 0;
		itemsCounter = 0;
		featuresCounter = 0;
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
	
	public void addFeature(String name, String description, Stats stats, int capacity) {
		features.put(featuresCounter, new Feature(name, description,
				stats, capacity));
		featuresCounter++;
	}
	
	public boolean isFeatureExist(int id) {
		if(id < 0 || id >= features.size()) {
			return false;
		}else {
			return true;
		}
	}
	
	public Feature getFeautureById(int id) {
		return features.get(id);
	}
	
	public Feature createFeautureById(int id) {
		return new Feature(features.get(id));
	}
	
	public Set<Entry<Integer, Feature>> getFeauturesEntry() {
		return features.entrySet();
	}
	
	public int getLocationsAmount() {
		return locations.size();
	}
	
	public int getItemsAmount() {
		return items.size();
	}
	
	public int getFeaturesAmount() {
		return features.size();
	}
	
	public void freeItems() {
		items.clear();
		itemsCounter = 0;
	}
	
	public void freeFeatures() {
		features.clear();
		featuresCounter = 0;
	}
	
}
