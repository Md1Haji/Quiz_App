package com.mha.QuizApp1.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mha.QuizApp1.model.Question;
import com.mha.QuizApp1.model.QuestionWrapper;
import com.mha.QuizApp1.model.Response;
import com.mha.QuizApp1.service.QuestionService;



@RestController
@RequestMapping("question")
class QuestionController {

        @Autowired
    QuestionService questionService;
        @GetMapping("allQuestion")
        public ResponseEntity<List<Question>> getAllQuestion(){
            return questionService.getAllQuestion();
        }

        // @GetMapping("category/{category}")
        // public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
        //     return questionService.getQuestionByCategory(category) ;

        // }

        @GetMapping("category/{category}")
        public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
            return questionService.getQuestionsByCategory(category);
    }

        @PostMapping("add")
        public ResponseEntity<String> addQuestion(@RequestBody Question question){
            return questionService.addQuestion(question) ;
        }

        @GetMapping("generate")
        public ResponseEntity<List<Integer>> getQuestionFoRQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestions){
            return questionService.getQuestionFoRQuiz(categoryName, numQuestions);
        }

        @PostMapping("getQuestions")
        public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
            return questionService.getQuestionsFromId(questionIds) ;
}

        @PostMapping("getScore")
        public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
            return questionService.getScore(responses) ;

        }
}