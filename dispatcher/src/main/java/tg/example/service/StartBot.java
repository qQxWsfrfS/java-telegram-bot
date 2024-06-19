package tg.example.service;


import lombok.extern.log4j.Log4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import tg.example.controller.TelegramBot;

@Service
@Log4j
public class StartBot {

    @Value("${bot.token}")
    public String botToken;

    public void run() throws TelegramApiException {
        String botToken = this.botToken;
        TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication();
        botsApplication.registerBot(botToken, new TelegramBot());
        log.debug("Bot is running");


    }
}
