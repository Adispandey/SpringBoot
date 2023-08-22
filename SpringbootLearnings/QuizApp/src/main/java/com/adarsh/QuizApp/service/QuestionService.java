package com.adarsh.QuizApp.service;


import com.adarsh.QuizApp.dao.QuestionDao;
import com.adarsh.QuizApp.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_GATEWAY);
        }
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            return new ResponseEntity<>("Success" + questionDao.save(question), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed to add data ", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> updateQuestion(Question question) {
        try {
            return new ResponseEntity<>("Successfully updated" + questionDao.save(question), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return  new ResponseEntity<>("Failed to load ",HttpStatus.BAD_GATEWAY);
        }
    }

    public String deleteQuestion(int id) {
        questionDao.deleteById(id);
        return "Question with ID " + id + " deleted successfully";
    }
}
