package com.sera.kafkademo;

import com.sera.kafkademo.producer.NewTopicProducer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;

@SpringBootApplication
public class KafkaDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaDemoApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(NewTopicProducer newTopicProducer,
                                    KafkaMessageListenerContainer<String, String> kafkaMessageListenerContainer) {
        return args -> {
            newTopicProducer.async("topic4", "hello, topic4 container.");
            kafkaMessageListenerContainer.start();
            Thread.sleep(1000);
            System.out.println("------- pause --------");

            kafkaMessageListenerContainer.pause(); //container pause() 를 하고 난 후에는 밑중 hello, secondary topic4 container. 을 읽지 않는다.
            Thread.sleep(5000);
            newTopicProducer.async("topic4", "hello, secondary topic4 container.");

            kafkaMessageListenerContainer.resume();
            System.out.println("------- resume --------");
            Thread.sleep(5000);
            newTopicProducer.async("topic4", "hello, third topic4 container.");
        };
    }

}
