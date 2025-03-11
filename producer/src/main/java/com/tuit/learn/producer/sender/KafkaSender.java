package com.tuit.learn.producer.sender;


import com.tuit.learn.data.Person;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaSender {

    @Autowired
    private  KafkaTemplate<String, Person> kafkaTemplate;

    public void sendMessage(Person person)
    {
        Message<Person> message = MessageBuilder
                .withPayload(person)
                        .setHeader(KafkaHeaders.TOPIC,"person-topic")
                        .build();
        kafkaTemplate.send(message);
    }

}
