package com.example.springstudy.services;

import com.example.springstudy.entities.People;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class PeopleRepositoryImpl implements PeopleCustomRepository {
    private final EntityManager entityManager;

    @Override
    public void updateName(People people) {
        entityManager.createQuery("UPDATE People p SET p.name = :name WHERE p.id = :id")
                .setParameter("name", people.getName())
                .setParameter("id", people.getId())
                .executeUpdate();
        entityManager.clear();
    }
}
