package com.sera.kafkademo;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.clients.admin.TopicListing;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class KafkaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaDemoApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(AdminClient adminClient) {
        return args -> {
            Map<String, TopicListing> topics = adminClient.listTopics().namesToListings().get();
            topics.forEach((topicName, topicListing) -> {
                System.out.println(topicListing);

                try {
                    Map<String, TopicDescription> descriptionMap = adminClient.describeTopics(Collections.singleton(topicName)).all().get();
                    System.out.println(descriptionMap);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }

                adminClient.deleteTopics(Collections.singleton(topicName));
            });

        };
    }

}
