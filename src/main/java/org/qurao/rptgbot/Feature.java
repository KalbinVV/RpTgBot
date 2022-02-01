package org.qurao.rptgbot;

public class Feature {

	private Stats stats;
	private String name;
	private String description;
	private int capacity;

	public Feature(String name, String description, Stats stats, int capacity){
		this.name = name;
		this.stats = stats;
		this.description = description;
		this.capacity = capacity;
	}
	
	public Feature(Feature feature){
		this.name = feature.getName();
		this.description = feature.getDescription();
		this.stats = feature.getStats();
		this.capacity = feature.getCapacity();
	}
	
	public Stats getStats() {
		return stats;
	}

	public String getName() {
		return name;
	}

	public int getCapacity() {
		return capacity;
	}

	public String getDescription() {
		return description;
	}
	
}
