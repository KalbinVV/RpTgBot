package org.qurao.rptgbot.commands;

import java.util.ArrayList;

import org.qurao.rptgbot.Bot;
import org.qurao.rptgbot.ICommand;
import org.qurao.rptgbot.Item;
import org.qurao.rptgbot.PlayerProfile;
import org.qurao.rptgbot.RpTgBot;
import org.qurao.rptgbot.Stats;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

public class LookAwayCommand implements ICommand{

	@Override
	public void run(Message message) {
		User user = message.getFrom();
		PlayerProfile profile = RpTgBot.getUsersStorage().getPlayerProfile(user.getUserName());
		ArrayList<Item> items = RpTgBot.getMainStorage().getLocationById(profile.getLocationID()).getItems();
		Bot bot = RpTgBot.getBot();
		String chatID = message.getChatId().toString();
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
		bot.sendMsg(chatID, "Вещи на локации:\n"+builder.toString().trim());
	}

}
