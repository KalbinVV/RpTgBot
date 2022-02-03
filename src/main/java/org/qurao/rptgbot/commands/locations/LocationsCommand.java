package org.qurao.rptgbot.commands.locations;

import org.qurao.rptgbot.Bot;
import org.qurao.rptgbot.ICommand;
import org.qurao.rptgbot.MainStorage;
import org.qurao.rptgbot.RpTgBot;
import org.qurao.rptgbot.UsersStorage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class LocationsCommand implements ICommand{

	@Override
	public void run(Message message) {
		if(RpTgBot.getUsersStorage().isAdmin(message.getFrom().getUserName())) {
			String chatID = message.getChatId().toString();
			Bot bot = RpTgBot.getBot();
			UsersStorage usersStorage = RpTgBot.getUsersStorage();
			MainStorage mainStorage = RpTgBot.getMainStorage();
			bot.sendMsg(chatID, "Общее число локаций: " + mainStorage.getLocationsAmount());
			for(var entry : mainStorage.getLocationsEntry()) {
				int locationID = entry.getKey();
				StringBuilder builder = new StringBuilder();
				for(String player : usersStorage
						.getPlayersInLocation(locationID)) {
					builder.append(player).append(" (" 
						+ usersStorage.getPlayerProfile(player).getFullName() + ")\n");
				}
				bot.sendMsg(chatID, "ID: " + locationID + "\n" +
						"Название: " + entry.getValue().getName() +
						"\nОписание: " + entry.getValue().getDescription() +
						"\nИгроки на локации:\n" + builder.toString().trim());
			}
		}else {
			RpTgBot.getBot().sendMsg(message.getChatId().toString(), 
					"Данную команду может выполнить лишь мастер!");
		}
	}

}
