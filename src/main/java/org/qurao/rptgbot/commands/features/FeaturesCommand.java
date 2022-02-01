package org.qurao.rptgbot.commands.features;

import org.qurao.rptgbot.Bot;
import org.qurao.rptgbot.ICommand;
import org.qurao.rptgbot.MainStorage;
import org.qurao.rptgbot.RpTgBot;
import org.qurao.rptgbot.Stats;
import org.telegram.telegrambots.meta.api.objects.Message;

public class FeaturesCommand implements ICommand{

	@Override
	public void run(Message message) {
		if(RpTgBot.getUsersStorage().isAdmin(message.getFrom().getUserName())) {
			String chatID = message.getChatId().toString();
			Bot bot = RpTgBot.getBot();
			MainStorage mainStorage = RpTgBot.getMainStorage();
			bot.sendMsg(chatID, 
					"Общее количество черт: " + mainStorage.getFeaturesAmount());
			for(var entry : mainStorage.getFeauturesEntry()) {
				int featureID = entry.getKey();
				Stats stats = entry.getValue().getStats();
				bot.sendMsg(chatID, "ID: " + featureID + "\n" +
						"Название: " + entry.getValue().getName() +
						"\nОписание: " + entry.getValue().getDescription() +
						"\nХарактеристики:\n" + stats.getString()
						+ "\nВместительность: " + entry.getValue().getCapacity());
			}
		}else {
			RpTgBot.getBot().sendMsg(message.getChatId().toString(), 
					"Данную команду может выполнить лишь мастер!");
		}
	}

}