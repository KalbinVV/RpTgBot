package org.qurao.rptgbot.commands;

import org.qurao.rptgbot.Bot;
import org.qurao.rptgbot.ICommand;
import org.qurao.rptgbot.RpTgBot;
import org.qurao.rptgbot.UsersStorage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class SendMessageToPlayerCommand implements ICommand{

	@Override
	public void run(Message message) {
		Bot bot = RpTgBot.getBot();
		String chatID = message.getChatId().toString();
		UsersStorage usersStorage = RpTgBot.getUsersStorage();
		if(RpTgBot.getUsersStorage().isAdmin(message.getFrom().getUserName())) {
			String[] args = message.getText().split(" ");
			if(args.length < 3) {
				bot.sendMsg(chatID, "Необходимо указать имя игрок и сообщение!"
						+ "\n /additemtoplayer Имя ID");
			}
			String userName = args[1];
			if(usersStorage.hasProfile(userName)) {
				StringBuilder builder = new StringBuilder();
				for(int i = 2; i < args.length; i++) {
					builder.append(args[i]).append(" ");
				}
				String messageToSend = builder.toString().trim();
				bot.sendMsg(usersStorage.getPlayerChatID(userName),
						"(Видно лишь вам) ГМ: " + messageToSend);
				bot.sendMsg(chatID, "(Видно лишь вам) ГМ->" 
						+ userName + ": " + messageToSend);
			}else {
				bot.sendMsg(chatID, "Данного игрока не существует!");
			}
		}else {
			bot.sendMsg(chatID, "Только мастер может отправлять сообщения игрокам!");
		}
	}

}
