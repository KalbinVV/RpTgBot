package org.qurao.rptgbot.commands.items;

import org.qurao.rptgbot.Bot;
import org.qurao.rptgbot.ICommand;
import org.qurao.rptgbot.MainStorage;
import org.qurao.rptgbot.RpTgBot;
import org.telegram.telegrambots.meta.api.objects.Message;

public class ClearItemsCommand implements ICommand{

	@Override
	public void run(Message message) {
		if(RpTgBot.getUsersStorage().isAdmin(message.getFrom().getUserName())) {
			String chatID = message.getChatId().toString();
			Bot bot = RpTgBot.getBot();
			MainStorage mainStorage = RpTgBot.getMainStorage();
			mainStorage.freeItems();
			bot.sendMsg(chatID, "Предметы успешно очищены!");
		}else {
			RpTgBot.getBot().sendMsg(message.getChatId().toString(), 
					"Данную команду может выполнить лишь мастер!");
		}
	}

}
