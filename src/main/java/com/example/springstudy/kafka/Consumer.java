package com.example.springstudy.kafka;

import com.example.springstudy.entities.People;
import com.example.springstudy.services.PeopleService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Consumer {
    private final PeopleService peopleService;

    @SneakyThrows
    @KafkaListener(topics = "dbsync-mysql.inventory.customers", groupId = "abc", containerFactory = "concurrentKafkaListenerContainerFactory")
    public void consume(String msg) {
        People people = new People();
        people.setId(Long.parseLong(msg));
        people.setAddress(msg);
        peopleService.create(people);
        System.out.println(msg);

    }
}
