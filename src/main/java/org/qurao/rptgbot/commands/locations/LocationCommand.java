package org.qurao.rptgbot.commands.locations;

import org.qurao.rptgbot.Bot;
import org.qurao.rptgbot.ICommand;
import org.qurao.rptgbot.Location;
import org.qurao.rptgbot.RpTgBot;
import org.qurao.rptgbot.UsersStorage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class LocationCommand implements ICommand{

	@Override
	public void run(Message message) {
		String userName = message.getFrom().getUserName();
		String chatID = message.getChatId().toString();
		UsersStorage usersStorage = RpTgBot.getUsersStorage();
		int locationID = usersStorage.getPlayerProfile(userName).getLocationID();
		Bot bot = RpTgBot.getBot();
		Location location = RpTgBot.getMainStorage().getLocationById(locationID);
		StringBuilder builder = new StringBuilder();
		for(String player : usersStorage.getPlayersInLocation(locationID)) {
			builder.append(player).append(" (" 
				+ usersStorage.getPlayerProfile(player).getFullName() + ")\n");
		}
		bot.sendMsg(chatID, "ID: " + locationID + "\n" +
				"Название: " + location.getName() +
				"\nОписание: " + location.getDescription()
				+"\nИгроки на локации:\n" + builder.toString().trim());
	}

}
