package com.sera.kafkademo.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class NewTopicProducer {
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public void async(String topic, String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate
                .send(topic, message);
        future.whenComplete(((stringStringSendResult, throwable) -> {
            if (throwable == null) {
                System.out.println("successfully send");
            } else {
                System.out.println("exception occurred");
            }
        }));
    }

}
