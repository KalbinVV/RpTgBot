package org.qurao.rptgbot.commands;

import org.qurao.rptgbot.Bot;
import org.qurao.rptgbot.ICommand;
import org.qurao.rptgbot.Location;
import org.qurao.rptgbot.MainStorage;
import org.qurao.rptgbot.RpTgBot;
import org.qurao.rptgbot.UsersStorage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class SetPlayerLocationCommand implements ICommand{

	@Override
	public void run(Message message) {
		Bot bot = RpTgBot.getBot();
		String chatID = message.getChatId().toString();
		UsersStorage usersStorage = RpTgBot.getUsersStorage();
		if(RpTgBot.getUsersStorage().isAdmin(message.getFrom().getUserName())) {
			String[] args = message.getText().split(" ");
			if(args.length < 3) {
				bot.sendMsg(chatID, "Необходимо указать имя игрока и ID локации!"
						+ "\n /setlocation Имя ID");
			}
			String userName = args[1];
			if(usersStorage.hasProfile(userName)) {
				try {
					int id = Integer.parseInt(args[2]);
					MainStorage mainStorage = RpTgBot.getMainStorage();
					Location location = mainStorage.getLocationById(id);
					if(location != null) {
						usersStorage.getPlayerProfile(userName).setLocationID(id);
						bot.sendMsg(chatID, "Локация игрока успешно изменена!");
						bot.sendMsg(usersStorage.getPlayerChatID(userName), 
								"Вы были перемещены на локацию: " + location.getName());
						
					}else {
						bot.sendMsg(chatID, "Данной локации не существует!");
					}
				} catch (NumberFormatException ex) {
					bot.sendMsg(chatID, "Необходимо вводить числа!");
				}
			}else {
				bot.sendMsg(chatID, "Данного игрока не существует!");
			}
		}else {
			bot.sendMsg(chatID, "Только мастер может изменять локации игроков!");
		}
	}

}
