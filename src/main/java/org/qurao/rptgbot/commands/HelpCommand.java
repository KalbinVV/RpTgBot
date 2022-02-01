package org.qurao.rptgbot.commands;

import org.qurao.rptgbot.ICommand;
import org.qurao.rptgbot.RpTgBot;
import org.telegram.telegrambots.meta.api.objects.Message;

public class HelpCommand implements ICommand{

	@Override
	public void run(Message message) {
		String chatID = message.getChatId().toString();
		RpTgBot.getBot().sendMsg(chatID, "Помощь по командам бота:\n"
				+ "/card - открыть карточку своего персонажа.\n"
				+ "/inv - открыть свой инвентарь.\n"
				+ "/inspect ID- осмотреть предмет в инвентаре.\n"
				+ "/location - посмотреть текущею локацию.\n"
				+ "/lookaway - осмотреть вещи, лежащие на локации.\n"
				+ "/pickup ID - подобрать предмет в локации.\n"
				+ "/drop ID - выкинуть предмет с инвентаря на пол. Он окажется в вещах локации.\n"
				+ "/inspectloc ID - осмотреть предмет на локации, не подбирая его.\n"
				+ "/act Сообщение - отправить действие, которое увидит лишь мастер.\n"
				+ "Чтобы отыгрывать процесс - достаточно лишь написать сообщение в чат.\n"
				+ "Мастер увидит ваше сообщение и отправит ответный ролевой пост.\n"
				+ "Обратите внимание, что сообщение увидят все персонажа в данной локации, поэтому диалог между персонажами прозводится также\n" 
				+ "Если вы хотите совершить действие скрытое от всех - воспользуйтесь командой /act и действие увидит лишь мастер.");
	}

}
