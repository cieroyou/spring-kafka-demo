package com.sera.kafkademo.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
public class TopicConsumer {


    @KafkaListener(id = "new-topic-request-id", topics = "new-topic-request")
    @SendTo
    public String listenTopicRequest(String message) {
        System.out.println(message);
        return "Pong Topic";
    }
}
