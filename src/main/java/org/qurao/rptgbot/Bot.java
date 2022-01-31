package org.qurao.rptgbot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class Bot extends TelegramLongPollingBot {

	@Override
	public void onUpdateReceived(Update update) {
		Message message = update.getMessage();
		String userName = message.getFrom().getUserName();
		System.out.println("["+userName+"]: " + message.getText());
		UsersStorage usersStorage = RpTgBot.getUsersStorage();
		usersStorage.addPlayerIDIfNotExist(userName, message.getChatId().toString());
		if(!usersStorage.hasProfile(userName)
				&& !usersStorage.isAdmin(userName)) {
			sendMsg(message.getChatId().toString(), 
					"У вас нет профиля! Обратитесь к @qurao");
		}else {
			if(message.getText().startsWith("/")) {
				RpTgBot.getCommandsStorage().handleCommand(update.getMessage());
			}else {
				PlayerProfile profile = usersStorage.getPlayerProfile(userName);
				for(String playerInLocation : usersStorage.getPlayersInLocation(
						profile.getLocationID()
						)) {
					sendMsg(usersStorage.getPlayerChatID(playerInLocation),
							userName + " (" + profile.getFullName() + "): "
							+ message.getText());
				}
				for(String admin : usersStorage.getAdmins()) {
					sendMsg(usersStorage.getPlayerChatID(admin), "(" +
					RpTgBot.getMainStorage().getLocationById(profile.getLocationID()).getName()
					+ "/ID:" + profile.getLocationID() + ") " + userName + ": " + message.getText());
				}
			}
		}
	}

	@Override
	public String getBotUsername() {
		return "RpTgBot";
	}

	@Override
	public String getBotToken() {
		return "5296959574:AAEaMj2agpdkLZsujiwWJvcqEtFsYFjfsqM";
	}
	
	public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
        	e.printStackTrace();
        }
    }
	
	
}
