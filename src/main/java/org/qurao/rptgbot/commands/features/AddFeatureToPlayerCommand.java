package org.qurao.rptgbot.commands.features;

import org.qurao.rptgbot.Bot;
import org.qurao.rptgbot.Feature;
import org.qurao.rptgbot.ICommand;
import org.qurao.rptgbot.MainStorage;
import org.qurao.rptgbot.RpTgBot;
import org.qurao.rptgbot.UsersStorage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class AddFeatureToPlayerCommand implements ICommand{

	@Override
	public void run(Message message) {
		Bot bot = RpTgBot.getBot();
		String chatID = message.getChatId().toString();
		UsersStorage usersStorage = RpTgBot.getUsersStorage();
		if(RpTgBot.getUsersStorage().isAdmin(message.getFrom().getUserName())) {
			String[] args = message.getText().split(" ");
			if(args.length < 3) {
				bot.sendMsg(chatID, "Необходимо указать имя игрока и ID черты!"
						+ "\n /additemtoplayer Имя ID");
			}else {
				String userName = args[1];
				if(usersStorage.hasProfile(userName)) {
					try {
						int id = Integer.parseInt(args[2]);
						MainStorage mainStorage = RpTgBot.getMainStorage();
						if(mainStorage.isFeatureExist(id)) {
							Feature feature = mainStorage.createFeautureById(id);
							usersStorage.getPlayerProfile(userName).getFeatures().add(feature);
							bot.sendMsg(chatID, "Черта успешно добавлена!");
							bot.sendMsg(usersStorage.getPlayerChatID(userName), 
								"Была добавлена черта: "
								+ feature.getName());
						}else {
							bot.sendMsg(chatID, "Данной черты не существует!");
						}
					} catch (NumberFormatException ex) {
						bot.sendMsg(chatID, "Необходимо вводить числа!");
					}
				}else {
					bot.sendMsg(chatID, "Данного игрока не существует!");
				}
			}
		}else {
			bot.sendMsg(chatID, "Только мастер может добавлять предметы игрокам!");
		}
	}

}