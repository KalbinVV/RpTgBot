package org.qurao.rptgbot.commands;

import org.qurao.rptgbot.Bot;
import org.qurao.rptgbot.ICommand;
import org.qurao.rptgbot.Location;
import org.qurao.rptgbot.RpTgBot;
import org.qurao.rptgbot.UsersStorage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class SendMessageToLocationCommand implements ICommand{

	@Override
	public void run(Message message) {
		Bot bot = RpTgBot.getBot();
		String chatID = message.getChatId().toString();
		UsersStorage usersStorage = RpTgBot.getUsersStorage();
		if(RpTgBot.getUsersStorage().isAdmin(message.getFrom().getUserName())) {
			String[] args = message.getText().split(" ");
			if(args.length < 3) {
				bot.sendMsg(chatID, "Необходимо указать ID комнаты и сообщение!\n"
						+ "/msgloc ID Сообщение");
			}else {
				StringBuilder builder = new StringBuilder();
				for(int i = 2; i < args.length; i++) {
					builder.append(args[i]).append(" ");
				}
				String messageToSend = builder.toString().trim();
				try {
					int locationID = Integer.parseInt(args[1]);
					Location location = RpTgBot.getMainStorage().getLocationById(locationID);
					if(location != null) {
						for(String playerInLocation : usersStorage.getPlayersInLocation(locationID)) {
							bot.sendMsg(usersStorage.getPlayerChatID(playerInLocation), 
									"ГМ: " + messageToSend);
						}
						bot.sendMsg(chatID, 
								"(Видит вся локация) ГМ->(" + location.getName() 
								+ "/ID: " + locationID + "): " 
						+ messageToSend);
					}else {
						bot.sendMsg(chatID, "Данной локации не существует!");
					}
				} catch(NumberFormatException ex) {
					bot.sendMsg(chatID, "Необходимо ввести число!");
				}
			}
		}else {
			bot.sendMsg(chatID, "Только мастер может отправлять сообщения в локацию!");
		}
		
	}

}
