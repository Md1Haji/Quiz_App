package com.mha.Quiz_service.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mha.Quiz_service.model.Quiz;


public interface QuizDao extends JpaRepository<Quiz, Integer>{

}
