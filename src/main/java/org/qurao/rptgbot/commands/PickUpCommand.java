package org.qurao.rptgbot.commands;

import java.util.ArrayList;

import org.qurao.rptgbot.Bot;
import org.qurao.rptgbot.ICommand;
import org.qurao.rptgbot.Item;
import org.qurao.rptgbot.Location;
import org.qurao.rptgbot.MainStorage;
import org.qurao.rptgbot.PlayerProfile;
import org.qurao.rptgbot.RpTgBot;
import org.telegram.telegrambots.meta.api.objects.Message;

public class PickUpCommand implements ICommand{

	@Override
	public void run(Message message) {
		Bot bot = RpTgBot.getBot();
		String chatID = message.getChatId().toString();
		String userName = message.getFrom().getUserName();
		MainStorage mainStorage = RpTgBot.getMainStorage();
		String[] args = message.getText().split(" ");
		PlayerProfile profile = RpTgBot.getUsersStorage().getPlayerProfile(userName);
		if(args.length < 2) {
			bot.sendMsg(chatID, "Необходимо указать ID предмета!");
		}
		try {
			int id = Integer.parseInt(args[1]);
			try {
				Location location = mainStorage.getLocationById(profile.getLocationID());
				Item item = location.getItems().get(id);
				ArrayList<Item> playerItems = profile.getItems();
				if(playerItems.size() >= profile.getCapacity()) {
					bot.sendMsg(chatID, "Вы достигли предела предметов!");
				}else {
					profile.getItems().add(item);
					mainStorage.getLocationById(profile.getLocationID()).getItems().remove(item);
					bot.sendMsg(chatID, "Вы подобрали предмет: " + item.getName());
				}
			} catch(IndexOutOfBoundsException ex) {
				bot.sendMsg(chatID, "Неверно указан ID предмета!");
			}
		} catch(NumberFormatException ex) {
			bot.sendMsg(chatID, "Необходимо указать число!");
		}
	}
	
}
