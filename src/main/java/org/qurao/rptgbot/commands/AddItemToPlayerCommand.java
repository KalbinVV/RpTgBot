package org.qurao.rptgbot.commands;

import org.qurao.rptgbot.Bot;
import org.qurao.rptgbot.ICommand;
import org.qurao.rptgbot.Item;
import org.qurao.rptgbot.MainStorage;
import org.qurao.rptgbot.RpTgBot;
import org.qurao.rptgbot.UsersStorage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class AddItemToPlayerCommand implements ICommand{

	@Override
	public void run(Message message) {
		Bot bot = RpTgBot.getBot();
		String chatID = message.getChatId().toString();
		UsersStorage usersStorage = RpTgBot.getUsersStorage();
		if(RpTgBot.getUsersStorage().isAdmin(message.getFrom().getUserName())) {
			String[] args = message.getText().split(" ");
			if(args.length < 3) {
				bot.sendMsg(chatID, "Необходимо указать имя игрок и ID предмета!"
						+ "\n /additemtoplayer Имя ID");
			}
			String userName = args[1];
			if(usersStorage.hasProfile(userName)) {
				try {
					int id = Integer.parseInt(args[2]);
					MainStorage mainStorage = RpTgBot.getMainStorage();
					if(mainStorage.isItemExists(id)) {
						Item item = mainStorage.createItemById(id);
						usersStorage.getPlayerProfile(userName).addItem(item);
						bot.sendMsg(chatID, "Предмет успешно добавлен!");
						bot.sendMsg(usersStorage.getPlayerChatID(userName), 
								"К вам в инвентарь был добавлен предмет: "
								+ item.getName());
					}else {
						bot.sendMsg(chatID, "Данного предмета не существует!");
					}
				} catch (NumberFormatException ex) {
					bot.sendMsg(chatID, "Необходимо вводить числа!");
				}
			}else {
				bot.sendMsg(chatID, "Данного игрока не существует!");
			}
		}else {
			bot.sendMsg(chatID, "Только мастер может добавлять предметы игрокам!");
		}
	}

}
