package tg.example.service.impl;

import lombok.extern.log4j.Log4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import tg.example.service.ConsumerService;
import tg.example.service.ProducerService;

import static tg.example.model.RabbitQueue.*;


@Service
@Log4j
public class ConsumerServiceImpl implements ConsumerService {

    private final ProducerService producerService;

    public ConsumerServiceImpl(ProducerService producerService){
        this.producerService=producerService;
    }

    @Override
    @RabbitListener(queues = TEXT_MESSAGE_UPDATE)
    public void consumeMessageTextUpdate(Update update) {
        log.debug("NODE: Text message is received");

        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage(
                message.getChatId().toString(),
                "Hello from node adsd"
        );
        producerService.produceAnswer(sendMessage);
    }

    @Override
    @RabbitListener(queues = PHOTO_MESSAGE_UPDATE)
    public void consumeMessagePhotoUpdate(Update update) {
        log.debug("NODE: Photo message is received");
    }

    @Override
    @RabbitListener(queues = DOC_MESSAGE_UPDATE)
    public void consumeMessageDocumentUpdate(Update update) {
        log.debug("NODE: Document message is received");
    }
}
