package com.mha.Quiz_service.service;
//import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mha.Quiz_service.dao.QuizDao;
import com.mha.Quiz_service.feign.QuizInterface;
import com.mha.Quiz_service.model.QuestionWrapper;
import com.mha.Quiz_service.model.Quiz;
import com.mha.Quiz_service.model.Response;
import com.mha.Quiz_service.model.Question;


import java.util.*;

@Service

public class QuizService {
    @Autowired
    QuizDao quizDao;
    
    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
       List<Integer> question = quizInterface.getQuestionFoRQuiz(category,numQ).getBody();
       Quiz quiz = new Quiz();
       quiz.setTitle(title);
       quiz.setQuestion(question);
       quizDao.save(quiz);
    
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
    	Quiz quiz = quizDao.findById(id).get();
    	List<Integer> questionIds = quiz.getQuestion();
    	quizInterface.getQuestionsFromId(questionIds);
    	
    	ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionIds);
    return questions;
        
}

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;
    }
}