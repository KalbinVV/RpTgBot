package org.qurao.rptgbot;

import java.util.HashMap;
import java.util.HashSet;

public class UsersStorage {

	private HashMap<String, PlayerProfile> profiles;
	private HashSet<String> admins;
	private HashMap<String, String> chatIDs;
	private int locationIDToSendContent;
	
	public UsersStorage(){
		profiles = new HashMap<String, PlayerProfile>();
		admins = new HashSet<String>();
		chatIDs = new HashMap<String, String>();
		setLocationIDToSendContent(0);
	}
	
	public void addPlayerIDIfNotExist(String userName, String chatID) {
		if(!chatIDs.containsKey(userName)) chatIDs.put(userName, chatID);
	}
	
	public String getPlayerChatID(String userName) {
		return chatIDs.get(userName);
	}
	
	public void addPlayersProfile(String userName, PlayerProfile profile) {
		profiles.put(userName, profile);
	}
	
	public void addPlayerToAdmins(String userName) {
		admins.add(userName);
	}
	
	public HashSet<String> getAdmins(){
		return admins;
	}

	public boolean hasProfile(String userName) {
		return profiles.containsKey(userName);
	}
	
	public PlayerProfile getPlayerProfile(String userName) {
		return profiles.get(userName);
	}
	
	public HashSet<String> getPlayersInLocation(int id){
		HashSet<String> playersInLocation = new HashSet<String>();
		for(var entry : profiles.entrySet()) {
			if(entry.getValue().getLocationID() == id) {
				playersInLocation.add(entry.getKey());
			}
		}
		return playersInLocation;
	}
	
	public boolean isAdmin(String userName) {
		return admins.contains(userName);
	}

	public int getLocationIDToSendContent() {
		return locationIDToSendContent;
	}

	public void setLocationIDToSendContent(int locationIDToSendContent) {
		this.locationIDToSendContent = locationIDToSendContent;
	}
	
}
