package org.qurao.rptgbot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import org.qurao.rptgbot.commands.*;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

public class RpTgBot {
	
	private static Bot bot;
	private static CommandsStorage commandsStorage;
	private static UsersStorage usersStorage;
	private static MainStorage mainStorage;
	
	public static void main(String[] args) {
		try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            bot = new Bot();
            if(!new File("users.json").exists()) {
            	usersStorage = new UsersStorage();
            	usersStorage.addPlayersProfile("Qurao", 
            		new PlayerProfile("1", "1", "1", 21, 
            				new Stats(0, 0, 0, 0, 0), 0));
            	usersStorage.addPlayerToAdmins("Qurao");
            }else {
            	loadUsersFromFile();
            }
            if(!new File("storage.json").exists()) {
            	mainStorage = new MainStorage();
            	getMainStorage().addLocation("Системная локация", "Первая локация.");
            }else {
            	loadMainStorageFromFile();
            }
            commandsStorage = new CommandsStorage();
            registerCommands();
            botsApi.registerBot(getBot());
            System.out.println("Bot enabled!");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
	}
	
	private static void registerCommands() {
		commandsStorage.registerCommand("start", new StartCommand());
        commandsStorage.registerCommand("card", new CardCommand());
        commandsStorage.registerCommand("save", new SaveCommand());
        commandsStorage.registerCommand("locations", new LocationsCommand());
        commandsStorage.registerCommand("items", new ItemsCommand());
        commandsStorage.registerCommand("addlocation", new AddLocationCommand());
        commandsStorage.registerCommand("additem", new AddItemCommand());
        commandsStorage.registerCommand("additemtoplayer", new AddItemToPlayerCommand());
        commandsStorage.registerCommand("inv", new InventoryCommand());
        commandsStorage.registerCommand("inspect", new InspectItemCommand());
        commandsStorage.registerCommand("inspectloc", new InspectInLocationItemCommand());
        commandsStorage.registerCommand("additemtolocation", new AddItemToLocationCommand());
        commandsStorage.registerCommand("lookaway", new LookAwayCommand());
        commandsStorage.registerCommand("drop", new DropItemCommand());
        commandsStorage.registerCommand("pickup", new PickUpCommand());
        commandsStorage.registerCommand("location", new LocationCommand());
        commandsStorage.registerCommand("clearitems", new ClearItemsCommand());
        commandsStorage.registerCommand("setlocation", new SetPlayerLocationCommand());
        commandsStorage.registerCommand("msgloc", new SendMessageToLocationCommand());
        commandsStorage.registerCommand("msgp", new SendMessageToPlayerCommand());
	}

	public static Bot getBot() {
		return bot;
	}

	public static CommandsStorage getCommandsStorage() {
		return commandsStorage;
	}

	public static UsersStorage getUsersStorage() {
		return usersStorage;
	}
	
	public static MainStorage getMainStorage() {
		return mainStorage;
	}
	
	public static void saveUsersToFile(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Writer writer = null;
        try{
            File file = new File("users.json");
            writer = new OutputStreamWriter(
            		new FileOutputStream(file), StandardCharsets.UTF_8);
            gson.toJson(usersStorage, writer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	
	public static void saveMainStorageToFile(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Writer writer = null;
        try{
            File file = new File("storage.json");
            writer = new OutputStreamWriter(
            		new FileOutputStream(file), StandardCharsets.UTF_8);
            gson.toJson(getMainStorage(), writer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	

	@SuppressWarnings("serial")
	public static void loadUsersFromFile(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileReader reader = null;
        try{
            reader = new FileReader("users.json");
            JsonReader jsonReader = new JsonReader(reader);
            usersStorage = gson.fromJson(jsonReader, new TypeToken<UsersStorage>
                    (){}.getType());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	
	@SuppressWarnings("serial")
	public static void loadMainStorageFromFile(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileReader reader = null;
        try{
            reader = new FileReader("storage.json");
            JsonReader jsonReader = new JsonReader(reader);
            mainStorage = gson.fromJson(jsonReader, new TypeToken<MainStorage>
                    (){}.getType());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	
}
