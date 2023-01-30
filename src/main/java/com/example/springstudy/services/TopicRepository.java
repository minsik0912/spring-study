package com.example.springstudy.services;

import com.example.springstudy.entities.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<People, Long>, TopicCustomRepository {

}
