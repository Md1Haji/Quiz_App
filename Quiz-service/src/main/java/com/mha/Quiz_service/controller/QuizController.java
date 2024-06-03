package com.mha.Quiz_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mha.Quiz_service.model.QuestionWrapper;
import com.mha.Quiz_service.model.QuizDto;
import com.mha.Quiz_service.model.Response;
import com.mha.Quiz_service.service.QuizService;

import java.util.*;


@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;
        @PostMapping("create")
        public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizdto) {
            return quizService.createQuiz(quizdto.getCategoryName(), quizdto.getNumQuestions(), quizdto.getTitle());
            

}

        @GetMapping("get/{id}")
        public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable Integer id) {
            return quizService.getQuizQuestion(id);
           
}

        @PostMapping("submit/{id}")
        public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
            return quizService.calculateResult(id, responses);
        }

}