package org.qurao.rptgbot.commands;

import org.qurao.rptgbot.ICommand;
import org.qurao.rptgbot.RpTgBot;
import org.telegram.telegrambots.meta.api.objects.Message;

public class SaveCommand implements ICommand{

	@Override
	public void run(Message message) {
		if(RpTgBot.getUsersStorage().isAdmin(message.getFrom().getUserName())) {
			RpTgBot.saveUsersToFile();
			RpTgBot.saveMainStorageToFile();
			RpTgBot.getBot().sendMsg(message.getChatId().toString(), 
					"Прогресс успешно сохранен!");
		}else {
			RpTgBot.getBot().sendMsg(message.getChatId().toString(), 
					"Это команда доступна только мастеру!");
		}
	}

}
