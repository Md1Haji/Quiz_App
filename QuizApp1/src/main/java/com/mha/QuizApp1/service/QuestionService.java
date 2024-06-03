package com.mha.QuizApp1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mha.QuizApp1.dao.QuestionDao;
import com.mha.QuizApp1.model.Question;
import com.mha.QuizApp1.model.QuestionWrapper;
import com.mha.QuizApp1.model.Response;




@Service
public class QuestionService {
    
    @Autowired
    QuestionDao questiondao;
    public ResponseEntity<List<Question>> getAllQuestion() {
        try{
        return new ResponseEntity<>(questiondao.findAll(),HttpStatus.OK);
    }catch (Exception e){
        e.printStackTrace();
    }

            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
}


    // public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
    //     try{
    //         return new ResponseEntity<>(questiondao.findByCategory(category),HttpStatus.OK);
    //     }catch (Exception e){
    //         e.printStackTrace();
    //     }
    //     return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    // }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questiondao.findByCategory(category),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> addQuestion(Question question) { 
        try{
            questiondao.save(question);
            return new ResponseEntity<>("Question added successfully",HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        //return new ResponseEntity<>(new Question question, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>("Question not added",HttpStatus.BAD_REQUEST);

    }


    public ResponseEntity<List<Integer>> getQuestionFoRQuiz(String categoryName, Integer numQuestions) {
        List<Integer> question = questiondao.findRandomQuestionCategory(categoryName, numQuestions);

        return new ResponseEntity<>(question, HttpStatus.OK);

        }


    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();
        for (Integer id : questionIds) {
            questions.add(questiondao.findById(id).get());
        }
        for (Question question : questions) {
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestionTitle(question.getQuestionTitle());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            wrappers.add(wrapper);

    }
    return new ResponseEntity<>(wrappers, HttpStatus.OK);    
}

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int right = 0;
        for (Response response : responses) {
            Question question = questiondao.findById(response.getId()).get();
            if (response.getResponse().equals(question.getRightAnswer())) 
                right++;
                
            }
        return new ResponseEntity<>(right , HttpStatus.OK) ;
    }
} 