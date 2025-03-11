package com.tuit.learn.consumer.consumer.config;


import com.tuit.learn.data.Person;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${kafka.server}")
    private String kafka;

    @Bean
    public Map<String,Object> config(){
        Map<String,Object> map = new HashMap<>();
        map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,kafka);
        map.put(ConsumerConfig.GROUP_ID_CONFIG,"consumer.group.id");
        return map;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Person> person(){
        return buildContainerFactory(Person.class);
    }

    private <T> ConcurrentKafkaListenerContainerFactory<String, T> buildContainerFactory(Class<T> type){
        JsonDeserializer<T> jsonDeserializer = new JsonDeserializer<>(type,false);

        DefaultKafkaConsumerFactory<String,T> consumerFactory =
                new DefaultKafkaConsumerFactory<>(config(),
                        new StringDeserializer(),
                        jsonDeserializer);

        ConcurrentKafkaListenerContainerFactory<String, T> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

}
