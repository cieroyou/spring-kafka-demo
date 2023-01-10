package com.sera.kafkademo;

import com.sera.kafkademo.model.Animal;
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
            newTopicProducer.async("topic4-animal", new Animal("puppy-valid", 9));
            newTopicProducer.async("topic4-animal", new Animal("puppy", 15)); // 에러 발생(ListenerExecutionFailedException: Listener method could not be invoked with the incoming message)
        };
    }

}
