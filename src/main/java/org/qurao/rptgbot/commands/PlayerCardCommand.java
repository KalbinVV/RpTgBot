package org.qurao.rptgbot.commands;

import org.qurao.rptgbot.Bot;
import org.qurao.rptgbot.Feature;
import org.qurao.rptgbot.ICommand;
import org.qurao.rptgbot.PlayerProfile;
import org.qurao.rptgbot.RpTgBot;
import org.qurao.rptgbot.Stats;
import org.qurao.rptgbot.UsersStorage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class PlayerCardCommand implements ICommand{

	@Override
	public void run(Message message) {
		Bot bot = RpTgBot.getBot();
		String chatID = message.getChatId().toString();
		UsersStorage usersStorage = RpTgBot.getUsersStorage();
		if(usersStorage.isAdmin(message.getFrom().getUserName())) {
			String[] args = message.getText().split(" ");
			if(args.length == 1) {
				bot.sendMsg(chatID, "Необходимо ввести имя игрока!");
			}else {
				String userName = args[1];
				if(usersStorage.hasProfile(userName)) {
					PlayerProfile profile = usersStorage.getPlayerProfile(userName);
					Stats stats = profile.getStats();
					Stats bonusStats = profile.getBonusFromItemsAndFeatures();
					StringBuilder builder = new StringBuilder();
					int featureID = 0;
					for(Feature feature : profile.getFeatures()) {
						builder.append("(ID:" + featureID+")\n")
						.append("Название: " + feature.getName())
						.append("\nОписание: " + feature.getDescription() + "\n").append(feature.getStats().getString())
						.append("\nВместимость: " + feature.getCapacity()).append("\n");
						featureID++;
					}
					RpTgBot.getBot().sendMsg(message.getChatId().toString(), "Карточка игрока "
							+ userName + ":\n"
							+ "Имя: " + profile.getFirstName() + 
							"\nФамилия: " + profile.getSecondName() + 
							"\nВозраст: " + profile.getAge() + 
							"\nОписание: " + profile.getDescription() +
							"\nХарактеристики:\n" + 
							"Cила: " + stats.getStrength() + " +(" + bonusStats.getStrength() + ")" 
							+ "\nХаризма: " + stats.getCharisma() + " +(" + bonusStats.getCharisma() + ")" +
							"\nИнтеллект: " + stats.getIntelligence() + " +(" + bonusStats.getIntelligence() + ")" 
							+ "\nУдача: " + stats.getLuck() + " +(" + bonusStats.getLuck() + ")"
							+ "\nЛовкость: " + stats.getAgility() + " +(" + bonusStats.getAgility() + ")"
							+ "\n\nЧерты:\n" + builder.toString().trim());
				}else {
					bot.sendMsg(chatID, "Данного игрока не существует!");
				}
			}
		}else {
			bot.sendMsg(chatID, "Только игрок может выполнить данную команду!");
		}
	}

}
