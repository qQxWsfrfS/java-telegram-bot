package tg.example.service;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface ConsumerService {
    void consumeMessageTextUpdate(Update update);
    void consumeMessagePhotoUpdate(Update update);
    void consumeMessageDocumentUpdate(Update update);
}
