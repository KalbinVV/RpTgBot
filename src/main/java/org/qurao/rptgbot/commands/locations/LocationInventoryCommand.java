package org.qurao.rptgbot.commands.locations;

import java.util.ArrayList;

import org.qurao.rptgbot.Bot;
import org.qurao.rptgbot.ICommand;
import org.qurao.rptgbot.Item;
import org.qurao.rptgbot.MainStorage;
import org.qurao.rptgbot.RpTgBot;
import org.qurao.rptgbot.Stats;
import org.telegram.telegrambots.meta.api.objects.Message;

public class LocationInventoryCommand implements ICommand{

	@Override
	public void run(Message message) {
		Bot bot = RpTgBot.getBot();
		String chatID = message.getChatId().toString();
		MainStorage mainStorage = RpTgBot.getMainStorage();
		if(RpTgBot.getUsersStorage().isAdmin(message.getFrom().getUserName())) {
			String[] args = message.getText().split(" ");
			if(args.length == 1) {
				bot.sendMsg(chatID, "Необходимо ввести ID локации!");
			}else {
				try {
					int locationID = Integer.parseInt(args[1]);
					if(locationID < 0 || locationID >= mainStorage.getLocationsAmount()) {
						bot.sendMsg(chatID, "Неверный ID локации!");
					}else {
						ArrayList<Item> items = mainStorage.getLocationById(locationID).getItems();
						StringBuilder builder = new StringBuilder();
						int itemID = 0;
						for(Item item : items) {
							Stats stats = item.getStats();
							builder.append("ID: " + itemID + 
									"\n" + "Название: " + item.getName())
							.append("\nОписание: " + item.getDescription())
							.append("\n(С:" +stats.getStrength()+";И:" + stats.getIntelligence()
							+";Х:"+stats.getCharisma()+";У:"+stats.getLuck()
							+";Л:"+stats.getAgility() + ";В:" + item.getCapacity()+")").append("\n");;
							itemID++;
						}
						bot.sendMsg(chatID, "Вещи на локации "
						+mainStorage.getLocationById(locationID).getName() + " ("+ locationID + ") :\n"
								+ builder.toString());
					}
				} catch(NumberFormatException ex) {
					bot.sendMsg(chatID, "Необходимо ввести число!");
				}
			}
		}else {
			bot.sendMsg(chatID, "Это команда доступна только администратору!");
		}
		
	}

}
