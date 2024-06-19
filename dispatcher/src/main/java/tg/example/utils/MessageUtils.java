package tg.example.utils;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;

@Component
public class MessageUtils {
    public  SendMessage generateSendMessageWithText(Update update, String text){
        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage(message.getChatId().toString(), text);
        return sendMessage;


    }
}
