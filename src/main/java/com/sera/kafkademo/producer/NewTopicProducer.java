package com.sera.kafkademo.producer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.RoutingKafkaTemplate;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class NewTopicProducer {
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    RoutingKafkaTemplate routingKafkaTemplate;

    @Autowired
    ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate;


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

    public void routingSend(String topic, String message) {
        routingKafkaTemplate.send(topic, message);
    }

    public void replyingSend(String topic, String message) throws ExecutionException, InterruptedException, TimeoutException {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, message);
        RequestReplyFuture<String, String, String> replyFuture
                = replyingKafkaTemplate.sendAndReceive(record);
        ConsumerRecord<String, String> consumerRecord = replyFuture.get(10, TimeUnit.SECONDS);
        System.out.println(consumerRecord.value());
    }
}
