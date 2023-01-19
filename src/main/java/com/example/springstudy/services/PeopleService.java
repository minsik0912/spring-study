package com.example.springstudy.services;

import com.example.springstudy.entities.People;
import lombok.RequiredArgsConstructor;
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

    @Transactional
    public People create(People people) {
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
