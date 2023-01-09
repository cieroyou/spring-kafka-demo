package com.sera.kafkademo.configuration;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.core.RoutingKafkaTemplate;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Configuration
public class RoutingKafkaTemplateConfiguration {

    @Bean
    public RoutingKafkaTemplate routingKafkaTemplate() {
        return new RoutingKafkaTemplate(factories());
    }

    private Map<Pattern, ProducerFactory<Object, Object>> factories() {
        Map<Pattern, ProducerFactory<Object, Object>> factories = new LinkedHashMap<>();
        factories.put(Pattern.compile(".*"), defaultProducerFactory());
        return factories;
    }


    private ProducerFactory<Object, Object> defaultProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerProps());
    }

    private Map<String, Object> producerProps() {
        // String Kafka 가 제공하는 Template 과 아주 유사함
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_DOC, StringSerializer.class);
        return props;
    }
}
