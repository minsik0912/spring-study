package com.example.springstudy.services;

import com.example.springstudy.entities.People;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Transactional(readOnly = true)
    public People get(Long id) {
        return peopleRepository.findById(id).orElse(new People());
    }

    @SneakyThrows
    @Transactional
    public People create(People people) {
        int randomSleep = (int) (Math.random() *  3 + 1) * 1000;
        Thread.sleep(randomSleep);
        people.setName("Sleep:" + Integer.toString(randomSleep));
        return peopleRepository.save(people);
    }

    @Transactional
    public void updateName(People people) {
        peopleRepository.updateName(people);
    }

    @Transactional
    public void delete(Long id) {
        peopleRepository.deleteById(id);
    }
}
