package org.qurao.rptgbot.commands;

import org.qurao.rptgbot.Bot;
import org.qurao.rptgbot.ICommand;
import org.qurao.rptgbot.Item;
import org.qurao.rptgbot.MainStorage;
import org.qurao.rptgbot.PlayerProfile;
import org.qurao.rptgbot.RpTgBot;
import org.telegram.telegrambots.meta.api.objects.Message;

public class DropItemCommand implements ICommand{

	@Override
	public void run(Message message) {
		Bot bot = RpTgBot.getBot();
		String chatID = message.getChatId().toString();
		String userName = message.getFrom().getUserName();
		MainStorage mainStorage = RpTgBot.getMainStorage();
		String[] args = message.getText().split(" ");
		PlayerProfile profile = RpTgBot.getUsersStorage().getPlayerProfile(userName);
		if(args.length < 2) {
			bot.sendMsg(chatID, "Необходимо указать ID предмета!");
		}
		try {
			int id = Integer.parseInt(args[1]);
			try {
				Item item = profile.getItems().get(id);
				profile.getItems().remove(id);
				mainStorage.getLocationById(profile.getLocationID()).getItems().add(item);
				bot.sendMsg(chatID, "Вы выбросили предмет на локацию: " + item.getName());
			} catch(IndexOutOfBoundsException ex) {
				bot.sendMsg(chatID, "Неверно указан ID предмета!");
			}
		} catch(NumberFormatException ex) {
			bot.sendMsg(chatID, "Необходимо указать число!");
		}
	}
	
}
