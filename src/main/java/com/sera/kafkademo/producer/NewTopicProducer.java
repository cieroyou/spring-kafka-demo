package com.sera.kafkademo.producer;

import com.sera.kafkademo.model.Animal;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class NewTopicProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, Animal> kafkaJsonTemplate;

    public NewTopicProducer(KafkaTemplate<String, String> kafkaTemplate, KafkaTemplate<String, Animal> kafkaJsonTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaJsonTemplate = kafkaJsonTemplate;
    }


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

    public void async(String topic, Animal animal) {
       kafkaJsonTemplate.send(topic, animal);
    }
}
