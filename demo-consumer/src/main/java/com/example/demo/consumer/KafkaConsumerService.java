package com.example.demo.consumer;

import com.example.demo.avro.dto.MessageDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private final static String TOPIC = "${demo.kafka.topic}";

    private final static String GROUP = "${demo.kafka.group}";

    @KafkaListener(topics = TOPIC, groupId = GROUP)
    public void listen(@Payload MessageDto messageDto, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
        System.out.println("Receiving message from Kafka :: messageDto :: {}" + messageDto + " from partition: " + partition);

    }
}
