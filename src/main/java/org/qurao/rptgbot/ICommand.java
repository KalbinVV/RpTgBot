package org.qurao.rptgbot;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface ICommand {

	public void run(Message message);

	
}
