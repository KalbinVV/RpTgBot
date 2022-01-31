package org.qurao.rptgbot;

import java.util.ArrayList;

public class Location {
	private String name;
	private String description;
	private ArrayList<Item> items;
	
	Location(String name, String description){
		this.name = name;
		this.description = description;
		this.items = new ArrayList<Item>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Item> getItems() {
		return items;
	}
	
}
