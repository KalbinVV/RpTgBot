package org.qurao.rptgbot.commands;

import org.qurao.rptgbot.ICommand;
import org.qurao.rptgbot.PlayerProfile;
import org.qurao.rptgbot.RpTgBot;
import org.qurao.rptgbot.Stats;
import org.telegram.telegrambots.meta.api.objects.Message;


public class CardCommand implements ICommand{
	@Override
	public void run(Message message) {
		PlayerProfile profile = RpTgBot.getUsersStorage().getPlayerProfile(
				message.getFrom().getUserName());
		Stats stats = profile.getStats();
		Stats bonusStats = profile.getBonusFromItems();
		RpTgBot.getBot().sendMsg(message.getChatId().toString(), "Карточка игрока "
				+ message.getFrom().getUserName() + ":\n"
				+ "Имя: " + profile.getFirstName() + 
				"\nФамилия: " + profile.getSecondName() + 
				"\nВозраст: " + profile.getAge() + 
				"\nОписание: " + profile.getDescription() +
				"\nХарактеристики:\n" + 
				"Cила: " + stats.getStrength() + " +(" + bonusStats.getStrength() + ")" 
				+ "\nХаризма: " + stats.getCharisma() + " +(" + bonusStats.getCharisma() + ")" +
				"\nИнтеллект: " + stats.getIntelligence() + " +(" + bonusStats.getIntelligence() + ")" 
				+ "\nУдача: " + stats.getLuck() + " +(" + bonusStats.getLuck() + ")"
				+ "\nЛовкость: " + stats.getAgility() + " +(" + bonusStats.getAgility() + ")");
	}

}
