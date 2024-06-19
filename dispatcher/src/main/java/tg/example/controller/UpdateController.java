package tg.example.controller;

import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import tg.example.utils.MessageUtils;

@Component
@Log4j
public class UpdateController {

    @Value("${bot.token}")
    private String botToken;

    private TelegramClient telegramClient;

    @Autowired
    private MessageUtils messageUtils;

    @PostConstruct
    public void init(){
        telegramClient = new OkHttpTelegramClient(botToken);
    }

    public void processUpdate(Update update){
        if (update==null){
            log.error("Received message is null");
            return;
        }

        if (update.getMessage()!=null){
            distributeMessageByType(update);
        } else {
            log.error("Received unsupported message type " + update);
        }
    }

    public void distributeMessageByType(Update update){
        Message message = update.getMessage();
        if (message.getText()!=null){
            processTextMessage(update);
        } else if (message.getDocument()!=null){
            processDocumentMessage(update);
        } else if (message.getPhoto()!=null){
            processPhotoMessage(update);
        } else {
            setUnsupportedTypeView(update);
        }
    }

    private void setUnsupportedTypeView(Update update) {
        SendMessage message = messageUtils.generateSendMessageWithText(update,
                "Неподдерживаемый тип сообщения");

        setView(message);
    }

    private void processPhotoMessage(Update update) {
        SendMessage message = messageUtils.generateSendMessageWithText(update,
                "Пришло фото");
        setView(message);
        
    }

    private void processDocumentMessage(Update update) {
        SendMessage message = messageUtils.generateSendMessageWithText(update,
                "Пришел документ");
        setView(message);

    }

    private void processTextMessage(Update update) {
        SendMessage message = messageUtils.generateSendMessageWithText(update,
                "Текстовое сообщение");
        setView(message);
    }

    private void setView(SendMessage sendMessage){
        try {
            telegramClient.execute(sendMessage);
        } catch (TelegramApiException ex){
            log.error(ex.toString());
        }
    }


}
