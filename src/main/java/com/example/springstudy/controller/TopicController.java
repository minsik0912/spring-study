package com.example.springstudy.controller;

import com.example.springstudy.services.TopicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "topic")
@RestController
@RequestMapping("/topic")
@RequiredArgsConstructor
public class TopicController {
    private final TopicService topicService;
    private final Logger logger = LogManager.getLogger(TopicService.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    @PutMapping("/put/message/publish")
    @ApiOperation(value = "메세지 발행")
    public void update(@RequestParam String topicName, @RequestParam String message) {
        String kafkaKey = message;
        this.kafkaTemplate.send(topicName, kafkaKey, message);
    }

    @GetMapping("/get/listener/all")
    @ApiOperation(value = "컨슈머 전체목록")
    public List<ContainerProperties> getAll() {
        Collection<MessageListenerContainer> kafkaListener = kafkaListenerEndpointRegistry.getAllListenerContainers();
        List<ContainerProperties> containerProperties = kafkaListener.stream().map(v -> v.getContainerProperties()).collect(Collectors.toList());
        return containerProperties;
    }

    @PostMapping("/get/listener/start")
    @ApiOperation(value = "컨슈머 시작")
    public void start(@RequestParam String topicName) {
        Collection<MessageListenerContainer> kafkaListener = kafkaListenerEndpointRegistry.getAllListenerContainers();
        MessageListenerContainer listenerContainer = kafkaListener.stream()
                .filter(v -> v.getContainerProperties().getTopics()[0].contains(topicName))
                .findFirst()
                .get();
        listenerContainer.start();
        logger.info("{} Kafka Listener Started.", listenerContainer.getListenerId());
    }

    @PostMapping("/get/listener/stop")
    @ApiOperation(value = "컨슈머 스탑")
    public void stop(@RequestParam String topicName) {
        Collection<MessageListenerContainer> kafkaListener = kafkaListenerEndpointRegistry.getAllListenerContainers();
        MessageListenerContainer listenerContainer = kafkaListener.stream()
                .filter(v -> v.getContainerProperties().getTopics()[0].contains(topicName))
                .findFirst()
                .get();
        listenerContainer.stop();
        logger.info("{} Kafka Listener Started.", listenerContainer.getListenerId());
    }

//    @GetMapping("/get")
//    @ApiOperation(value = "사람 조회")
//    public People get(Long id) throws Exception {
//        logger.info("test");
//        logger.debug("test");
//        logger.error("test");
//        throw new Exception("ERRORORRORO");
//        return peopleService.get(id);
//    }

//    @PostMapping("/create")
//    @ApiOperation(value = "사람 추가")
//    public People create(People people) {
//        return peopleService.create(people);
//    }

//    @PutMapping("/put")
//    @ApiOperation(value = "사람 이름 업데이트")
//    public void update(People people) {
//        peopleService.updateName(people);
//    }

//    @DeleteMapping("/delete")
//    @ApiOperation(value = "사람 삭제")
//    public void delete(Long id) {
//        peopleService.delete(id);
//    }
}
