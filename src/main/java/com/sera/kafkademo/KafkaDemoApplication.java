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
            newTopicProducer.routingSend("new-topic", "hello-routing-send");
            Thread.sleep(1000); // 슬립을 해야 hell-sync 까지 성공적으로 message 를 publish 함 없으면 두 메세지 모두 보내지 못하고 앱 종료
        };
    }

}
