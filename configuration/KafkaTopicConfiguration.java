package com.sera.kafkademo.configuration;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfiguration {
    // AdminClient 생성 -> ApplicationRunner runner 에서 직접 만든 AdminClient 사용가능)
    @Bean
    public AdminClient adminClient(KafkaAdmin kafkaAdmin){
        return AdminClient.create(kafkaAdmin.getConfigurationProperties());
    }

    @Bean
    public NewTopic newTopic() {
        return TopicBuilder.name("new-topic").build();
    }

    @Bean
    public KafkaAdmin.NewTopics newTopics() {
        return new KafkaAdmin.NewTopics(
                TopicBuilder.name("new-topic.part1").build(),
                TopicBuilder.name("new-topic.part2")
                        .partitions(3)
                        .replicas(1)
                        .config(TopicConfig.RETENTION_MS_CONFIG, String.valueOf(1000 * 60 * 60)) // 1시간 Retention 을 가짐
                        .build()
        );
    }
}
