package com.tuit.learn.producer.controller;


import com.tuit.learn.producer.dto.Person;
import com.tuit.learn.producer.sender.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TestController {

    @Autowired
    private KafkaSender kafkaSender;

    @GetMapping
    public ResponseEntity<?> test(@RequestParam(value = "fio",defaultValue = "jack") String fio,
                                  @RequestParam(value = "age",defaultValue = "20") int age)
    {
        kafkaSender.sendMessage(new Person(fio,age));
        return ResponseEntity.ok().build();
    }
}
