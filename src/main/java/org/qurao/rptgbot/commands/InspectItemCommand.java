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

public class InspectItemCommand implements ICommand{

	@Override
	public void run(Message message) {
		User user = message.getFrom();
		PlayerProfile profile = RpTgBot.getUsersStorage().getPlayerProfile(user.getUserName());
		Bot bot = RpTgBot.getBot();
		String chatID = message.getChatId().toString();
		ArrayList<Item> arraysList = profile.getItems();
		try {
			int itemID = Integer.parseInt(message.getText().split(" ")[1]);
			try {
				Item item = arraysList.get(itemID);
				Stats stats = item.getStats();
				bot.sendMsg(chatID, "ID: " + itemID + "\n" +
						"Название: " + item.getName() +
						"\nОписание: " + item.getDescription() +
						"\n\nХарактеристики:" + "\nСила: " + stats.getStrength() +
						"\nИнтеллект: " + stats.getIntelligence() + "\nУдача: " +
						stats.getIntelligence() + "\nЛовкость: " + stats.getAgility() +
						"\nХаризма: " + stats.getCharisma()
						+ "\nВместительность: " + item.getCapacity());
			} catch(IndexOutOfBoundsException ex) {
				bot.sendMsg(chatID, "Неверно введено ID предмета!");
				
			}
		} catch(NumberFormatException ex) {
			bot.sendMsg(chatID, "Необходимо ввести число!");
		}
	}

}
