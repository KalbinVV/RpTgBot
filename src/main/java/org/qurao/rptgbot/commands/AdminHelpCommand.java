package org.qurao.rptgbot.commands;

import org.qurao.rptgbot.Bot;
import org.qurao.rptgbot.ICommand;
import org.qurao.rptgbot.RpTgBot;
import org.telegram.telegrambots.meta.api.objects.Message;

public class AdminHelpCommand implements ICommand{

	@Override
	public void run(Message message) {
		Bot bot = RpTgBot.getBot();
		String chatID = message.getChatId().toString();
		if(RpTgBot.getUsersStorage().isAdmin(message.getFrom().getUserName())) {
			bot.sendMsg(chatID, "Помощь по админским командам:\n"
					+ "/save - сохранить данные в файлы.\n"
					+ "/additem Сила Интеллект Удача Харизма Ловкость Вместимость"
					+ " Имя:Описание - создать новый предмет\n"
					+ "/items - посмотреть список всех доступных предметов\n"
					+ "/clearitems - очистить список всех доступных предметов\n"
					+ "/additemtoplayer Имя ID - добавить предмет игроку.\n"
					+ "/rmitemplayer Имя ID - удалить предмет игроку.\n"
					+ "/additemtolocation IDLocation ID - добавить предмет на локацию.\n"
					+ "/rmitemlocation IDLocation ID - удалить предмет с локации.\n"
					+ "/locations - посмотреть список всех доступных локаций.\n"
					+ "/addlocation Имя:Описание - добавить новую локацию.\n"
					+ "/setlocation Имя ID - установить локацию игроку\n"
					+ "/msgloc ID Сообщение - отправить сообщение на локацию.\n"
					+ "/msgp Игрок Сообщение - отправить сообщение игроку.\n"
					+ "/pinv Игрок - посмотреть инвентарь игрока.\n"
					+ "/addfeature Сила Интеллект Удача Харизма Ловкость Вместимость"
					+ " Имя:Описание - создать новую черту.\n"
					+ "/features - посмотреть список всех черт.\n"
					+ "/addplayerfeature Игрок ID - добавить игроку черту.\n"
					+ "/rmplayerfeature Игрок ID - удалить черту игроку\n."
					+ "/clearfeatures - очистить черты.\n"
					+ "/pcard Игрок - посмотреть карточку игрока.\n"
					+ "/dice Игрок min max тип(str, int, luck, chr, agi) - бросить кубик игроку.\n"
					+ "/dicew Игрок min max тип(str, int, luck, chr, agi) - бросить кубик игроку. (Без учета бонуса)");
		}else {
			bot.sendMsg(chatID, "Это команда доступна только мастеру!");
		}
	}

}
