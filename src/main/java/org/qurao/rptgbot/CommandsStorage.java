package org.qurao.rptgbot;

import java.util.HashMap;

import org.telegram.telegrambots.meta.api.objects.Message;

public class CommandsStorage {
	HashMap<String, ICommand> commands;
	
	public CommandsStorage(){
		commands = new HashMap<String, ICommand>();
	}
	
	public void handleCommand(Message message) {
		String commandText = message.getText();
		String[] args = commandText.split(" ");
		if(commands.containsKey(args[0].substring(1))) {
			try {
				commands.get(args[0].substring(1)).run(message);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}else {
			RpTgBot.getBot().sendMsg(message.getChatId().toString(), "Команды "
					+ commandText + " не существует!");
		}
	}
	
	public void registerCommand(String commandMsg, ICommand command) {
		commands.put(commandMsg, command);
	}
	
}
