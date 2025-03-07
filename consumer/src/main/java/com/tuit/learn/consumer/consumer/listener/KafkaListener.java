package com.tuit.learn.consumer.consumer.listener;

import com.tuit.learn.consumer.consumer.config.dto.Person;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaListener {

    @org.springframework.kafka.annotation.KafkaListener(
            topics = "person-topic",
            containerFactory = "person"
    )
    public void listenTopic(@Payload Person person) {
        System.out.println(person + " first ");
    }

    @org.springframework.kafka.annotation.KafkaListener(
            topics = "person-topic",
            containerFactory = "person",
            groupId = "second"
    )
    public void listenTopic2(@Payload Person person) {
        System.out.println(person + " second");
    }


    @org.springframework.kafka.annotation.KafkaListener(
            topics = "person-topic",
            containerFactory = "person",
            groupId = "third"
    )
    public void listenTopic3(@Payload Person person) {
        System.out.println(person + " third");
    }

    @org.springframework.kafka.annotation.KafkaListener(
            topics = "person-topic",
            containerFactory = "person",
            groupId = "third"
    )
    public void listenTopic4(@Payload Person person) {
        System.out.println(person + " fourth");
    }




}
