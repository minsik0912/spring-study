package com.example.springstudy.services;

import com.example.springstudy.entities.People;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TopicService {
    private final TopicRepository topicRepository;

    @Transactional(readOnly = true)
    public People get(Long id) {
        return topicRepository.findById(id).orElse(new People());
    }

    @SneakyThrows
    @Transactional
    public People create(People people) {
//        int randomSleep = (int) (Math.random() *  3 + 1) * 1000;
        int randomSleep = 0;
        Thread.sleep(randomSleep);
        people.setDelay("Sleep:" + randomSleep);

        return topicRepository.save(people);
    }

    @Transactional
    public void updateName(People people) {
        topicRepository.updateName(people);
    }

    @Transactional
    public void delete(Long id) {
        topicRepository.deleteById(id);
    }
}
