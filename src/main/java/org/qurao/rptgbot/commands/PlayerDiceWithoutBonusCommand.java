package org.qurao.rptgbot.commands;

import java.util.Random;

import org.qurao.rptgbot.Bot;
import org.qurao.rptgbot.ICommand;
import org.qurao.rptgbot.PlayerProfile;
import org.qurao.rptgbot.RpTgBot;
import org.qurao.rptgbot.Stats;
import org.qurao.rptgbot.UsersStorage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class PlayerDiceWithoutBonusCommand implements ICommand{

	@Override
	public void run(Message message) {
		UsersStorage usersStorage = RpTgBot.getUsersStorage();
		Bot bot = RpTgBot.getBot();
		String chatID = message.getChatId().toString();
		if(usersStorage.isAdmin(message.getFrom().getUserName())) {
			String[] args = message.getText().split(" ");
			if(args.length < 5) {
				bot.sendMsg(chatID, "/dice Игрок min max тип(str, int, luck,"
						+ "chr, agi)");
			}else {
				String player = args[1];
				if(usersStorage.hasProfile(player)) {
					String type = args[4];
					String typeName = "";
					PlayerProfile profile = usersStorage.getPlayerProfile(player);
					boolean incorrect = false;
					Stats stats = profile.getStats();
					int bonus = 0;
					if(type.equals("str")) {
						typeName = "Сила";
						bonus = stats.getStrength();
					}else if(type.equals("int")){
						typeName = "Интеллект";
						bonus = stats.getIntelligence(); 
					}else if(type.equals("luck")) {
						typeName = "Удача";
						bonus = stats.getIntelligence();
					}else if(type.equals("chr")){
						typeName = "Харизма";
						bonus = stats.getCharisma();
					}else if(type.equals("agi")) {
						typeName = "Ловкость";
						bonus = stats.getAgility();
					}else {
						incorrect = true;
					}
					if(incorrect) {
						bot.sendMsg(chatID,"Неверно указан тип кубика!");
					}else {
						try {
							int min = Integer.parseInt(args[2]);
							int max = Integer.parseInt(args[3]);
							int diff = max - min;
							int result = new Random().nextInt(diff + 1) + min;
							int calculatedResult = result + bonus;
							String resultMessage ="(Кубик без учета предметов/"+typeName+")(От "
									+ min + " до " + max + ")\n" + result + " + " + bonus
									+ " = " + calculatedResult; 
							bot.sendMsg(chatID, resultMessage);
							bot.sendMsg(usersStorage.getPlayerChatID(player),
									resultMessage);
						}catch(NumberFormatException ex) {
							bot.sendMsg(chatID, "Необходимо указать число!");
						}
					}
				}else {
					bot.sendMsg(chatID, "Данного пользователя не существует!");
				}
			}
		}else {
			bot.sendMsg(chatID, "Только мастер может кидать кубик!");
		}
	}

}
