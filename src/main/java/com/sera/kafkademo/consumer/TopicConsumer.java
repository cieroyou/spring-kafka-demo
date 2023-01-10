package com.sera.kafkademo.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.adapter.ConsumerRecordMetadata;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class TopicConsumer {

    //    @KafkaListener(id="topic4-listener-id", topics = "topic4-listener", concurrency = "2", clientIdPrefix = "listener-id")
    @KafkaListener(id = "topic4-listener-id", topics = "topic4-listener")

    // thread 개수를 늘릴수록 빨라지고, concurrency 를 쉽게 늘릴 수 있다.
    public void listen(String message,
                       @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long timestamp,
                       @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                       @Header(KafkaHeaders.OFFSET) long offset,
                       ConsumerRecordMetadata metadata) {
        System.out.println("Listener. offset=" + metadata.offset() +
                ", partition=" + partition +
                ", timestamp=" + timestamp +
                ", offset=" + offset +
                 ",message=" + message);
    }
}
