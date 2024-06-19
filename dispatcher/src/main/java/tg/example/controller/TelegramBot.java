package tg.example.controller;

import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.List;
@Component
@NoArgsConstructor
@Log4j
public class TelegramBot implements LongPollingSingleThreadUpdateConsumer {
    @Autowired
    private UpdateController updateController;

    @Value("${bot.token}")
    public String botToken;


    @SneakyThrows
    @PostConstruct
    public void init(){
        TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication();
        botsApplication.registerBot(botToken, this);
        log.debug("Bot is running");
    }



    @Override
    public void consume(Update update) {
        log.debug(updateController);
        updateController.processUpdate(update);
    }



}
