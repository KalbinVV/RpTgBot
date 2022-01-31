package org.qurao.rptgbot;

public class Item{
	private String name;
	private String description;
	private Stats stats;
	private int capacity;
	
	Item(String name, String description, Stats stats, int capacity){
		this.name = name;
		this.description = description;
		this.stats = stats;
		this.capacity = capacity;
	}
	
	Item(Item item){
		this.name = item.getName();
		this.description = item.getDescription();
		this.stats = item.getStats();
		this.capacity = item.getCapacity();
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

	public Stats getStats() {
		return stats;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	
}
