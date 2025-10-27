package com.example.demo.service;

import com.example.demo.model.Message;
import com.example.demo.producer.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class BusinessLogicService {

    @Autowired
    KafkaProducerService producer;

    private int count = 0;

    @Value("${demo.kafka.topic}")
    private String topicName;

    public void createAndSendMessage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите сообщение (или 'exit' для выхода):");

        while (true) {
            String messageContent = scanner.nextLine();

            if ("exit".equals(messageContent)) {
                break;
            }
            producer.sendMessage(topicName, new Message(count, messageContent));
            System.out.println("Сообщение отправлено: ");
            count++;
        }
        scanner.close();
    }
}
