package com.adarsh.QuizApp.dao;

import com.adarsh.QuizApp.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz,Integer> {
}
