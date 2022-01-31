package org.qurao.rptgbot.commands;

import org.qurao.rptgbot.Bot;
import org.qurao.rptgbot.ICommand;
import org.qurao.rptgbot.RpTgBot;
import org.telegram.telegrambots.meta.api.objects.Message;

public class AddLocationCommand implements ICommand{

	@Override
	public void run(Message message) {
		Bot bot = RpTgBot.getBot();
		String chatID = message.getChatId().toString();
		if(RpTgBot.getUsersStorage().isAdmin(message.getFrom().getUserName())) {
			String[] args = message.getText().split(" ");
			if(args.length < 2) {
				bot.sendMsg(chatID, "Необходимо указать данные комнаты");
			}else {
				StringBuilder builder = new StringBuilder();
				for(int i = 1; i < args.length; i++) {
					builder.append(args[i]).append(" ");
				}
				String[] locationValues = builder.toString().trim().split(":");
				if(locationValues.length < 2) {
					bot.sendMsg(chatID, "Необходимо указать данные в формате Имя:Описание");
				}else {
					StringBuilder descBuilder = new StringBuilder();
					for(int i = 1; i < locationValues.length; i++) {
						descBuilder.append(locationValues[i]).append(":");
					}
					RpTgBot.getMainStorage().addLocation(locationValues[0], descBuilder.toString());
					bot.sendMsg(chatID, "Локация успешно создана!");
				}
			}
		}else {
			bot.sendMsg(chatID, "Только мастер может выполнять эту команду!");
		}
	}

}
