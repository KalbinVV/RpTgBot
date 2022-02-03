package org.qurao.rptgbot.commands;

import org.qurao.rptgbot.Bot;
import org.qurao.rptgbot.ICommand;
import org.qurao.rptgbot.RpTgBot;
import org.qurao.rptgbot.UsersStorage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class SendLocationIdToContentCommand implements ICommand{

	@Override
	public void run(Message message) {
		Bot bot = RpTgBot.getBot();
		String chatID = message.getChatId().toString();
		String userName = message.getFrom().getUserName();
		String[] args = message.getText().split(" ");
		UsersStorage usersStorage = RpTgBot.getUsersStorage();
		if(usersStorage.isAdmin(userName)) {
			if(args.length == 1) {
				bot.sendMsg(chatID, "Необходимо ввести индекс локации!");
			}else {
				try {
					int locationID = Integer.parseInt(args[1]);
					if(locationID >= RpTgBot.getMainStorage().getLocationsAmount()
							|| locationID < 0) {
						bot.sendMsg(chatID, "Неверно указано ID!");
					}else {
						usersStorage.setLocationIDToSendContent(locationID);
						bot.sendMsg(chatID, "ID для контента успешно установлен!");
					}
				} catch(NumberFormatException ex) {
					bot.sendMsg(chatID, "Необходимо ввести число!");
				}
			}
		}else {
			bot.sendMsg(chatID, "Данное действие доступно только мастеру!");
		}
	}

}
