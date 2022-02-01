package org.qurao.rptgbot.commands;

import org.qurao.rptgbot.Bot;
import org.qurao.rptgbot.ICommand;
import org.qurao.rptgbot.RpTgBot;
import org.telegram.telegrambots.meta.api.objects.Message;

public class StartCommand implements ICommand{

	@Override
	public void run(Message message) {
		Bot bot = RpTgBot.getBot();
		String chatID = message.getChatId().toString();
		RpTgBot.getUsersStorage().addPlayerIDIfNotExist(message.getFrom().getUserName(),
				chatID);
		bot.sendMsg(chatID, "Добро пожаловать в игру!\n" +
				"Чтобы увидеть доступные вам команды - воспользуйтесь командой /help."
				+ "\nОжидайте дальнейших действий мастера.");
	}

}
