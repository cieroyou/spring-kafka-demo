package com.sera.kafkademo.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class NewTopicProducer {
    KafkaTemplate<String, String> kafkaTemplate;

    public NewTopicProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    //    @Autowired
//    RoutingKafkaTemplate routingKafkaTemplate;

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

    public void sync(String topic, String message) {
        try {
            kafkaTemplate.send(topic, message);
            System.out.println("successfully send");

        } catch (Exception e) {
            System.out.println("exception occurred");

        }
    }

//    public void routingSend(String topic, String message) {
//        routingKafkaTemplate.send(topic, message);
//    }
}
