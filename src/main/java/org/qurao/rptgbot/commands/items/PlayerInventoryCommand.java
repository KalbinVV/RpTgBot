package org.qurao.rptgbot.commands.items;

import java.util.ArrayList;


import org.qurao.rptgbot.Bot;
import org.qurao.rptgbot.ICommand;
import org.qurao.rptgbot.Item;
import org.qurao.rptgbot.PlayerProfile;
import org.qurao.rptgbot.RpTgBot;
import org.qurao.rptgbot.Stats;
import org.qurao.rptgbot.UsersStorage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class PlayerInventoryCommand implements ICommand{

	@Override
	public void run(Message message) {
		Bot bot = RpTgBot.getBot();
		String chatID = message.getChatId().toString();
		UsersStorage usersStorage = RpTgBot.getUsersStorage();
		String[] args = message.getText().split(" ");
		if(args.length == 1) {
			bot.sendMsg(chatID, "Необходимо ввести имя игрока!");
		}else {
			String userName = args[1];
			if(usersStorage.hasProfile(userName)) {
				PlayerProfile profile = RpTgBot.getUsersStorage().getPlayerProfile(userName);
				StringBuilder builder = new StringBuilder();
				ArrayList<Item> items = profile.getItems();
				int itemID = 0;
				for(Item item : items) {
					Stats stats = item.getStats();
					builder.append("ID: " + itemID + "\n" + "Название: " + item.getName())
					.append("\nОписание: " + item.getDescription())
					.append("\n(С:" +stats.getStrength()+";И:" + stats.getIntelligence()
					+";Х:"+stats.getCharisma()+";У:"+stats.getLuck()
					+";Л:"+stats.getAgility() + ";В:" + item.getCapacity()+")").append("\n");
					itemID++;
				}
				bot.sendMsg(chatID, "Инвентарь игрока " + userName 
				+ " (" + items.size() + "/" + profile.getCapacity() + "): \n"
				+builder.toString().trim());
			}else {
				bot.sendMsg(chatID, "Данного игрока не существует!");
			}
		}
	}

}
