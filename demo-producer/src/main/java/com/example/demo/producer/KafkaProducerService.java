package com.example.demo.producer;


import com.example.demo.avro.dto.MessageDto;
import com.example.demo.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;


@Service
public class KafkaProducerService {

    @Autowired
    private final KafkaTemplate<String, MessageDto> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, MessageDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, Message message) {

        CompletableFuture<SendResult<String, MessageDto>> future =
                kafkaTemplate.send(topic, mapToAvro(message));

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Сообщение успешно отправлено" + result);
            } else {
                System.out.println("Ошибка при отправке сообщения: " + ex.getMessage());
            }
        });
    }

    private MessageDto mapToAvro(Message message) {
        return new MessageDto(message.count(), message.content());
    }
}
