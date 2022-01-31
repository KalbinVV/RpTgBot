package org.qurao.rptgbot.commands;

import java.util.ArrayList;

import org.qurao.rptgbot.Bot;
import org.qurao.rptgbot.ICommand;
import org.qurao.rptgbot.Item;
import org.qurao.rptgbot.PlayerProfile;
import org.qurao.rptgbot.RpTgBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

public class InventoryCommand implements ICommand{

	@Override
	public void run(Message message) {
		User user = message.getFrom();
		PlayerProfile profile = RpTgBot.getUsersStorage().getPlayerProfile(user.getUserName());
		Bot bot = RpTgBot.getBot();
		String chatID = message.getChatId().toString();
		StringBuilder builder = new StringBuilder();
		ArrayList<Item> items = profile.getItems();
		int itemID = 0;
		for(Item item :items) {
			builder.append("ID: " + itemID + "\n" + "Название: " + item.getName()).append("\n");
			itemID++;
		}
		bot.sendMsg(chatID, "Инвентарь "
				+ "(" + items.size() + "/" + profile.getCapacity() + "): \n"
				+builder.toString().trim());
	}

}
