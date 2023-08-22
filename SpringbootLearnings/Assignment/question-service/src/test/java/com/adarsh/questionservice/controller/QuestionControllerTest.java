package com.adarsh.questionservice.controller;

import com.adarsh.questionservice.entity.Question;
import com.adarsh.questionservice.entity.QuestionWrapper;
import com.adarsh.questionservice.entity.Response;
import com.adarsh.questionservice.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class QuestionControllerTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private QuestionController questionController;

    @Mock
    private Environment environment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllQuestions() {
        // Mock the data
        List<Question> questions = Arrays.asList(
                new Question(1, "Category1", "Easy", "Question1", "Option1", "Option2", "Option3", "Option4", "Option1"),
                new Question(2, "Category2", "Medium", "Question2", "Option1", "Option2", "Option3", "Option4", "Option2"),
                new Question(3, "Category1", "Hard", "Question3", "Option1", "Option2", "Option3", "Option4", "Option3")
        );

        // Define the behavior of the questionService.getAllQuestions() method
        when(questionService.getAllQuestions()).thenReturn(new ResponseEntity<>(questions, HttpStatus.OK));

        // Call the controller method
        ResponseEntity<List<Question>> response = questionController.getAllQuestions();

        // Assert the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(questions, response.getBody());

        // Verify that the questionService.getAllQuestions() method was called
        verify(questionService, times(1)).getAllQuestions();
    }

    @Test
    void testGetQuestionByCategory() {
        // Mock the data
        String category = "Category1";
        List<Question> questions = Arrays.asList(
                new Question(1, "Category1", "Easy", "Question1", "Option1", "Option2", "Option3", "Option4", "Option1"),
                new Question(3, "Category1", "Hard", "Question3", "Option1", "Option2", "Option3", "Option4", "Option3")
        );

        // Define the behavior of the questionService.getQuestionByCategory() method
        when(questionService.getQuestionByCategory(category)).thenReturn(new ResponseEntity<>(questions, HttpStatus.OK));

        // Call the controller method
        ResponseEntity<List<Question>> response = questionController.getQuestionByCategory(category);

        // Assert the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(questions, response.getBody());

        // Verify that the questionService.getQuestionByCategory() method was called with the correct parameter
        verify(questionService, times(1)).getQuestionByCategory(category);
    }

    @Test
    void testAddQuestion() {
        // Mock the data
        Question question = new Question(1, "Category1", "Easy", "Question1", "Option1", "Option2", "Option3", "Option4", "Option1");

        // Define the behavior of the questionService.addQuestion() method
        when(questionService.addQuestion(any(Question.class))).thenReturn(new ResponseEntity<>("Success", HttpStatus.CREATED));

        // Call the controller method
        ResponseEntity<String> response = questionController.addQuestion(question);

        // Assert the result
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Success", response.getBody());

        // Verify that the questionService.addQuestion() method was called with the correct parameter
        verify(questionService, times(1)).addQuestion(any(Question.class));
    }

    @Test
    void testUpdateQuestion() {
        // Mock the data
        Question question = new Question(1, "Category1", "Easy", "Updated Question", "Option1", "Option2", "Option3", "Option4", "Option1");

        // Call the controller method
        Question updatedQuestion = questionController.updateQuestion(question);

        // Assert the result
        assertEquals(question, updatedQuestion);

        // Verify that the questionService.updateQuestion() method was called with the correct parameter
        verify(questionService, times(1)).updateQuestion(any(Question.class));
    }

    @Test
    void testDeleteQuestion() {
        // Mock the data
        int questionId = 1;

        // Define the behavior of the questionService.deleteQuestion() method
        when(questionService.deleteQuestion(questionId)).thenReturn("Question with ID " + questionId + " deleted successfully.");

        // Call the controller method
        String response = questionController.deleteQuestion(questionId);

        // Assert the result
        assertEquals("Question with ID 1 deleted successfully.", response);

        // Verify that the questionService.deleteQuestion() method was called with the correct parameter
        verify(questionService, times(1)).deleteQuestion(questionId);
    }

    // Test for getQuestionsForQuiz
    @Test
    void testGetQuestionsForQuiz() {
        // Mock the data
        String categoryName = "Java";
        int numQuestions = 3;
        List<Integer> questionIds = Arrays.asList(1, 2, 3);

        // Define the behavior of the questionService.getQuestionsForQuiz() method
        when(questionService.getQuestionsForQuiz(categoryName, numQuestions)).thenReturn(new ResponseEntity<>(questionIds, HttpStatus.OK));

        // Call the controller method
        ResponseEntity<List<Integer>> response = questionController.getQuestionsForQuiz(categoryName, numQuestions);

        // Assert the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(questionIds, response.getBody());

        // Verify that the questionService.getQuestionsForQuiz() method was called with the correct parameters
        verify(questionService, times(1)).getQuestionsForQuiz(categoryName, numQuestions);
    }

    // Test for getQuestionsFromId
    @Test
    void testGetQuestionsFromId() {
        // Mock the data
        List<Integer> questionIds = Arrays.asList(1, 2, 3);
        List<QuestionWrapper> questions = Arrays.asList(
                new QuestionWrapper(1, "Question1", "Option1", "Option2", "Option3", "Option4"),
                new QuestionWrapper(2, "Question2", "Option1", "Option2", "Option3", "Option4"),
                new QuestionWrapper(3, "Question3", "Option1", "Option2", "Option3", "Option4")
        );

        // Define the behavior of the questionService.getQuestionsFromId() method
        when(questionService.getQuestionsFromId(questionIds)).thenReturn(new ResponseEntity<>(questions, HttpStatus.OK));

        // Call the controller method
        ResponseEntity<List<QuestionWrapper>> response = questionController.getQuestionsFromId(questionIds);

        // Assert the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(questions, response.getBody());

        // Verify that the questionService.getQuestionsFromId() method was called with the correct parameter
        verify(questionService, times(1)).getQuestionsFromId(questionIds);
    }

    // Test for getScore
    @Test
    void testGetScore() {
        // Mock the data
        List<Response> responses = Arrays.asList(
                new Response(1,"response"),
                new Response(2, "Option2"),
                new Response(3, "Option3")
        );
        int score = 2;

        // Define the behavior of the questionService.getScore() method
        when(questionService.getScore(responses)).thenReturn(new ResponseEntity<>(score, HttpStatus.OK));

        // Call the controller method
        ResponseEntity<Integer> response = questionController.getScore(responses);

        // Assert the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(score, response.getBody());

        // Verify that the questionService.getScore() method was called with the correct parameter
        verify(questionService, times(1)).getScore(responses);
    }


}
