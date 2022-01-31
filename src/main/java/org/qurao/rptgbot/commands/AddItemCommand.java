package org.qurao.rptgbot.commands;

import org.qurao.rptgbot.Bot;
import org.qurao.rptgbot.ICommand;
import org.qurao.rptgbot.RpTgBot;
import org.qurao.rptgbot.Stats;
import org.telegram.telegrambots.meta.api.objects.Message;

public class AddItemCommand implements ICommand{

	@Override
	public void run(Message message) {
		Bot bot = RpTgBot.getBot();
		String chatID = message.getChatId().toString();
		if(RpTgBot.getUsersStorage().isAdmin(message.getFrom().getUserName())) {
			String[] args = message.getText().split(" ");
			if(args.length < 8) {
				bot.sendMsg(chatID, "Необходимо указать данные предмета!\n"
						+ "Формат: Сила Интеллект Удача Харизма Ловкость Вместимость Имя:Описание");
			}else {
				
				StringBuilder builder = new StringBuilder();
				for(int i = 7; i < args.length; i++) {
					builder.append(args[i]).append(" ");
				}
				String[] itemsValues = builder.toString().trim().split(":");
				if(itemsValues.length < 2) {
					bot.sendMsg(chatID, "Необходимо указать данные в формате Имя:Описание");
				}else {
					try {
						int strengthValue = Integer.parseInt(args[1]);
						int intValue = Integer.parseInt(args[2]);
						int luckValue = Integer.parseInt(args[3]);
						int charValue = Integer.parseInt(args[4]);
						int agilValue = Integer.parseInt(args[5]);
						int capacValue = Integer.parseInt(args[6]);
						String itemName = itemsValues[0];
						StringBuilder descBuilder = new StringBuilder();
						for(int i = 1; i < itemsValues.length; i++) {
							descBuilder.append(itemsValues[i]).append(":");
						}
						RpTgBot.getMainStorage().addItem(itemName, descBuilder.toString().trim(),
								new Stats(strengthValue, intValue, luckValue,
										charValue, agilValue), capacValue);
						bot.sendMsg(chatID, "Предмет успешно добавлен!");
					} catch (NumberFormatException ex) {
						bot.sendMsg(chatID, "Необходимо вводить числа!");
					}
				}
			}
		}else {
			bot.sendMsg(chatID, "Только мастер может выполнять эту команду!");
		}
	}

}