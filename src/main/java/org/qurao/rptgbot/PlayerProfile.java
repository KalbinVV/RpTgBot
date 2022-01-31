package org.qurao.rptgbot;

import java.util.ArrayList;

public class PlayerProfile {
	
	private String firstName;
	private String secondName;
	private String description;
	private int age;
	private Stats stats;
	private int locationID;
	private ArrayList<Item> items;
	
	PlayerProfile(String firstName, String secondName, String description,
			int age, Stats stats, int locationID){
		this.firstName = firstName;
		this.secondName = secondName;
		this.description = description;
		this.age = age;
		this.stats = stats;
		this.locationID = locationID;
		items = new ArrayList<Item>();
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}
	
	public String getFullName() {
		return firstName + " " + secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Stats getStats() {
		return stats;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}

	public int getLocationID() {
		return locationID;
	}

	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}

	public ArrayList<Item> getItems() {
		return items;
	}
	
	public void addItem(Item item) {
		items.add(item);
	}
	
	public void removeItem(int index) {
		items.remove(index);
	}
	
	public Item removeAndGetItem(int index) {
		Item item = items.get(index);
		items.remove(index);
		return item;
	}
	
	public int getCapacity() {
		int capacity = 0;
		if(stats.getStrength() < 5) {
			switch(stats.getStrength()) {
				case 0:
					capacity = 2;
					break;
				case 1:
					capacity = 5;
					break;
				case 2:
					capacity = 9;
					break;
				case 3:
					capacity = 12;
					break;
				case 4:
					capacity = 14;
					break;
			}
		}else {
			capacity = 14 + (stats.getStrength() - 4) * 1;
		}
		for(Item item : items) {
			capacity += item.getCapacity();
		}
		return capacity;
	}
	
	public Stats getBonusFromItems() {
		Stats stats = new Stats(0, 0, 0, 0, 0);
		for(Item item : items) {
			stats.setStrength(item.getStats().getStrength() + stats.getStrength());
			stats.setIntelligence(item.getStats().getIntelligence() + stats.getIntelligence());
			stats.setLuck(item.getStats().getIntelligence() + stats.getLuck());
			stats.setCharisma(item.getStats().getCharisma() + stats.getCharisma());
			stats.setAgility(item.getStats().getAgility() + stats.getAgility());
		}
		return stats;
	}
	
}
