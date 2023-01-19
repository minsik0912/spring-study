package com.example.springstudy.controller;

import com.example.springstudy.SpringStudyApplication;
import com.example.springstudy.entities.People;
import com.example.springstudy.services.PeopleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(tags = "people")
@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {
    private final PeopleService peopleService;
    private final Logger logger = LogManager.getLogger(PeopleService.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/get")
    @ApiOperation(value = "사람 조회")
    public People get(Long id) throws Exception {
//        logger.info("test");
//        logger.debug("test");
//        logger.error("test");
        throw new Exception("ERRORORRORO");
//        return peopleService.get(id);
    }

    @PostMapping("/create")
    @ApiOperation(value = "사람 추가")
    public People create(People people) {
        return peopleService.create(people);
    }

//    @PutMapping("/put")
//    @ApiOperation(value = "사람 이름 업데이트")
//    public void update(People people) {
//        peopleService.updateName(people);
//    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "사람 삭제")
    public void delete(Long id) {
        peopleService.delete(id);
    }

    @PutMapping("/put")
    @ApiOperation(value = "카프카 프로듀싱")
    public void update(String id) {
        this.kafkaTemplate.send("dbsync-mysql.inventory.customers", id);
    }
}
