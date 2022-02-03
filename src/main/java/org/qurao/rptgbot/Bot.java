package org.qurao.rptgbot;

import java.util.ArrayList;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Audio;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class Bot extends TelegramLongPollingBot {

	@Override
	public void onUpdateReceived(Update update) {
		Message message = update.getMessage();
		String userName = message.getFrom().getUserName();
		UsersStorage usersStorage = RpTgBot.getUsersStorage();
		if(message.hasText()) {
			System.out.println("["+userName+"]: " + message.getText());
			usersStorage.addPlayerIDIfNotExist(userName, message.getChatId().toString());
			if(!usersStorage.hasProfile(userName)
				&& !usersStorage.isAdmin(userName)) {
				sendMsg(message.getChatId().toString(), 
					"У вас нет профиля! Обратитесь к @qurao");
			}else {
				if(message.getText().startsWith("/")) {
					RpTgBot.getCommandsStorage().handleCommand(update.getMessage());
				}else {
					if(usersStorage.hasProfile(userName)) {
						PlayerProfile profile = usersStorage.getPlayerProfile(userName);
						for(String playerInLocation : usersStorage.getPlayersInLocation(
								profile.getLocationID()
								)) {
								if(!playerInLocation.equals(userName)) {
									sendMsg(usersStorage.getPlayerChatID(playerInLocation),
										userName + " (" + profile.getFullName() + ")\n"
												+ message.getText());
								}
						}
						for(String admin : usersStorage.getAdmins()) {
							sendMsg(usersStorage.getPlayerChatID(admin), "(" +
								RpTgBot.getMainStorage().getLocationById(profile.getLocationID()).getName()
								+ "/ID:" + profile.getLocationID() + ")(" + profile.getFullName() + ")\n" + userName + ": " + message.getText());
						}
					}else {
						sendMsg(message.getChatId().toString(), "У вас нет персонажа!");
					}
				}
			}
		}else if(message.hasPhoto()) {
			String chatID = message.getChatId().toString();
			if(usersStorage.isAdmin(userName)) {
				sendMsg(chatID, "Отправлено фото!");
				ArrayList<PhotoSize> photos = new ArrayList<PhotoSize>(message.getPhoto());
				PhotoSize photo = photos.get(photos.size() - 1);
				for(String player : usersStorage
					.getPlayersInLocation(usersStorage.getLocationIDToSendContent())) {
					sendImageMessage(usersStorage.getPlayerChatID(player),
						photo);
					sendMsg(chatID, "Сообщение увидел: " + player);
				}
			}else {
				sendMsg(message.getChatId().toString(), "Данное действие доступно только мастеру!");
			}
		}else if(message.hasAudio()) {
			String chatID = message.getChatId().toString();
			if(usersStorage.isAdmin(userName)) {
				sendMsg(chatID, "Отправлено аудио!");
				for(String player : usersStorage
					.getPlayersInLocation(usersStorage.getLocationIDToSendContent())) {
					sendAudioMessage(usersStorage.getPlayerChatID(player),
						message.getAudio());
					sendMsg(chatID, "Сообщение увидел: " + player);
				}
			}else {
				sendMsg(message.getChatId().toString(), "Данное действие доступно только мастеру!");
			}
		}else {
			sendMsg(message.getChatId().toString(), "Недопустимый формат сообщений!");
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
	
	public synchronized void sendImageMessage(String chatID, PhotoSize photo) {
		SendPhoto sendPhoto = new SendPhoto();
		sendPhoto.setPhoto(new InputFile(photo.getFileId()));
		sendPhoto.setChatId(chatID);
		try{
			execute(sendPhoto);
		} catch(TelegramApiException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void sendAudioMessage(String chatID, Audio audio) {
		SendAudio sendAudio = new SendAudio();
		sendAudio.setAudio(new InputFile(audio.getFileId()));
		sendAudio.setChatId(chatID);
		try {
			execute(sendAudio);
		} catch(TelegramApiException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
