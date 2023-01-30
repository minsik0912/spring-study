package com.example.springstudy.kafka;

import com.example.springstudy.entities.People;
import com.example.springstudy.services.TopicService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Consumer {
    private final TopicService topicService;

    @SneakyThrows
    @KafkaListener(topics = "dbsync-mysql.inventory.customers", groupId = "abc", containerFactory = "customersConcurrentKafkaListenerContainerFactory")
    public void customersConsume(String msg) {
        People people = new People();
        people.setNum(msg);
        people.setName("dbsync-mysql.inventory.customers");
        topicService.create(people);
        System.out.println(msg);
    }

    @SneakyThrows
    @KafkaListener(topics = "dbsync-mysql.inventory.address", groupId = "abc", containerFactory = "addressConcurrentKafkaListenerContainerFactory")
    public void addressConsume(String msg) {
        People people = new People();
        people.setNum(msg);
        people.setName("dbsync-mysql.inventory.address");
        topicService.create(people);
        System.out.println(msg);
    }
}
