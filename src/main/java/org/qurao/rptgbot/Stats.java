package org.qurao.rptgbot;

public class Stats {
	
	private int strength;
	private int intelligence;
	private int luck;
	private int charisma;
	private int agility;
	
	public Stats(int strength, int intelligence, int luck, int charisma, int agility){
		this.strength = strength;
		this.intelligence = intelligence;
		this.luck = luck;
		this.charisma = charisma;
		this.agility = agility;
	}
	
	public int getStrength() {
		return strength;
	}
	
	public void setStrength(int strength) {
		this.strength = strength;
	}
	
	public int getIntelligence() {
		return intelligence;
	}
	
	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}
	
	public int getLuck() {
		return luck;
	}
	
	public void setLuck(int luck) {
		this.luck = luck;
	}
	
	public int getCharisma() {
		return charisma;
	}
	
	public void setCharisma(int charisma) {
		this.charisma = charisma;
	}

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}
	
}
