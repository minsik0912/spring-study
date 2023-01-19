package com.example.springstudy.services;

import com.example.springstudy.entities.People;

public interface PeopleCustomRepository {
    void updateName(People people);
}
