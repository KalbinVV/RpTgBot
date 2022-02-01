package org.qurao.rptgbot.commands;

import org.qurao.rptgbot.Bot;
import org.qurao.rptgbot.ICommand;
import org.qurao.rptgbot.PlayerProfile;
import org.qurao.rptgbot.RpTgBot;
import org.qurao.rptgbot.UsersStorage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class ActionCommand implements ICommand{

	@Override
	public void run(Message message) {
		Bot bot = RpTgBot.getBot();
		String chatID = message.getChatId().toString();
		UsersStorage usersStorage = RpTgBot.getUsersStorage();
		String userName = message.getFrom().getUserName();
		String[] args = message.getText().split(" ");
		if(args.length == 0) {
			bot.sendMsg(chatID, "Необходимо указать действие!");
		}else {
			StringBuilder builder = new StringBuilder();
			for(int i = 1; i < args.length; i++) {
				builder.append(args[i]).append(" ");
			}
			PlayerProfile profile = usersStorage.getPlayerProfile(userName);
			for(String admin : usersStorage.getAdmins()) {
				bot.sendMsg(usersStorage.getPlayerChatID(admin), "(Действие! Видно только мастеру.) (" +
				RpTgBot.getMainStorage().getLocationById(profile.getLocationID()).getName()
				+ "/ID:" + profile.getLocationID() + ") " + userName + ": " + builder.toString());
			}
		}
	}

}
