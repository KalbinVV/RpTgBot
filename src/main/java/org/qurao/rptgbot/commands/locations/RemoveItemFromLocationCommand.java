package org.qurao.rptgbot.commands.locations;

import org.qurao.rptgbot.Bot;
import org.qurao.rptgbot.ICommand;
import org.qurao.rptgbot.Location;
import org.qurao.rptgbot.MainStorage;
import org.qurao.rptgbot.RpTgBot;
import org.telegram.telegrambots.meta.api.objects.Message;

public class RemoveItemFromLocationCommand implements ICommand{

	@Override
	public void run(Message message) {
		Bot bot = RpTgBot.getBot();
		String chatID = message.getChatId().toString();
		MainStorage mainStorage = RpTgBot.getMainStorage();
		if(RpTgBot.getUsersStorage().isAdmin(message.getFrom().getUserName())) {
			String[] args = message.getText().split(" ");
			if(args.length < 3) {
				bot.sendMsg(chatID, "Необходимо указать ID локации и ID предмета!"
						+ "\n /rmitemlocation IDLocation ID");
			}else {
				try {
					int idLocation = Integer.parseInt(args[1]);
					Location location = mainStorage.getLocationById(idLocation);
					if(location != null) {
						try {
							int id = Integer.parseInt(args[2]);
							location.getItems().remove(id);
							bot.sendMsg(chatID, "Предмет успешно удален с локации!");
						} catch (NumberFormatException ex) {
							bot.sendMsg(chatID, "Необходимо вводить числа!");
						} catch (IndexOutOfBoundsException ex) {
							bot.sendMsg(chatID, "Данного предмета не существует на локации!");
						}
					}else {
						bot.sendMsg(chatID, "Данной локации не существует!");
					}
				}catch (NumberFormatException ex) {
					bot.sendMsg(chatID, "Необходимо вводить числа!");
				}
			}
		}else {
			bot.sendMsg(chatID, "Только мастер может добавлять предметы в локации!");
		}
	}

}
