package org.qurao.rptgbot.commands.features;

import java.util.ArrayList;

import org.qurao.rptgbot.Bot;
import org.qurao.rptgbot.Feature;
import org.qurao.rptgbot.ICommand;
import org.qurao.rptgbot.RpTgBot;
import org.qurao.rptgbot.UsersStorage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class RemoveFeatureFromPlayerCommand implements ICommand{


	@Override
	public void run(Message message) {
		Bot bot = RpTgBot.getBot();
		String chatID = message.getChatId().toString();
		UsersStorage usersStorage = RpTgBot.getUsersStorage();
		if(RpTgBot.getUsersStorage().isAdmin(message.getFrom().getUserName())) {
			String[] args = message.getText().split(" ");
			if(args.length < 3) {
				bot.sendMsg(chatID, "Необходимо указать имя игрока и ID черты!"
							+ "\n /rmfeauture Имя ID");
			}else {
				String userName = args[1];
				if(usersStorage.hasProfile(userName)) {
					try {
						int id = Integer.parseInt(args[2]);
						ArrayList<Feature> features = usersStorage.getPlayerProfile(userName).getFeatures();
						Feature feature = features.get(id);
						usersStorage.getPlayerProfile(userName).getFeatures().remove(id);
						bot.sendMsg(chatID, "Черта успешно удалена!");
						bot.sendMsg(usersStorage.getPlayerChatID(userName),
								"Черта удалена: " + feature.getName());
					} catch (NumberFormatException ex) {
						bot.sendMsg(chatID, "Необходимо вводить числа!");
					} catch (IndexOutOfBoundsException ex) {
						bot.sendMsg(chatID, "Данного ID не существует!");
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
