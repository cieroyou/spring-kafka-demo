package com.sera.kafkademo;

import com.sera.kafkademo.producer.NewTopicProducer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KafkaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaDemoApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(NewTopicProducer newTopicProducer) {
        return args -> {
            newTopicProducer.async("new-topic", "hello");
            newTopicProducer.sync("new-topic", "hello-sync");
            Thread.sleep(1000);
        };
    }

}
