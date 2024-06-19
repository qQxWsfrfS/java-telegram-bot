package tg.example;


import org.apache.log4j.BasicConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import tg.example.controller.TelegramBot;
import tg.example.service.StartBot;

@SpringBootApplication
public class DispatcherApplication {



    public static void main(String[] args){

        SpringApplication.run(DispatcherApplication.class);

    }
}
