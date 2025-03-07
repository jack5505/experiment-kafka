package com.tuit.learn.producer.config;

import com.tuit.learn.producer.dto.Person;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProducerConfigKafka {

    @Value("${kafka.server}")
    private String server;

    @Bean
    public Map<String,Object> configuration(){
        Map<String,Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,server);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        config.put(ProducerConfig.CLIENT_ID_CONFIG,1);
        return config;
    }

    @Bean
    public KafkaTemplate<String, Person> kafkaTemplate(){
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(configuration()));
    }

    @Bean
    public NewTopic topic()
    {
        return TopicBuilder.name("person-topic")
                .partitions(2)
                .build();
    }

}
