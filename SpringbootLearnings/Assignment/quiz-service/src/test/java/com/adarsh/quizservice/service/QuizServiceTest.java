package com.adarsh.quizservice.service;

import com.adarsh.quizservice.dao.QuizDao;
import com.adarsh.quizservice.entity.QuestionWrapper;
import com.adarsh.quizservice.entity.Quiz;
import com.adarsh.quizservice.entity.Response;
import com.adarsh.quizservice.feign.QuizInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class QuizServiceTest {

    @Mock
    private QuizDao quizDao;

    @Mock
    private QuizInterface quizInterface;

    @InjectMocks
    private QuizService quizService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateQuiz() {
        // Mock input data
        String category = "Category1";
        int numQuestions = 5;
        String title = "Quiz Title";
        List<Integer> questionIds = List.of(1, 2, 3, 4, 5);

        // Mock external service response
        ResponseEntity<List<Integer>> questionsResponse = ResponseEntity.ok(questionIds);
        when(quizInterface.getQuestionsForQuiz(eq(category), eq(numQuestions))).thenReturn(questionsResponse);

        // Test the service method
        ResponseEntity<String> response = quizService.createQuiz(category, numQuestions, title);

        // Verify that the external service method was called with the correct arguments
        verify(quizInterface, times(1)).getQuestionsForQuiz(eq(category), eq(numQuestions));

        // Verify that the quiz was saved to the database with the correct data
        verify(quizDao, times(1)).save(any(Quiz.class));

        // Verify the response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Success", response.getBody());
    }

    @Test
    public void testGetQuizQuestions() {
        // Mock input data
        int quizId = 1;
        List<Integer> questionIds = List.of(1, 2, 3, 4, 5);

        // Mock database response
        Quiz quiz = new Quiz();
        quiz.setId(quizId);
        quiz.setQuestionIds(questionIds);
        when(quizDao.findById(eq(quizId))).thenReturn(Optional.of(quiz));

        // Mock external service response
        List<QuestionWrapper> mockQuestions = new ArrayList<>();
        mockQuestions.add(new QuestionWrapper(1, "Question1", "Option1", "Option2", "Option3", "Option4"));
        when(quizInterface.getQuestionsFromId(anyList())).thenReturn(ResponseEntity.ok(mockQuestions));

        // Test the service method
        ResponseEntity<List<QuestionWrapper>> response = quizService.getQuizQuestions(quizId);

        // Verify that the database method was called with the correct argument
        verify(quizDao, times(1)).findById(eq(quizId));

        // Verify that the external service method was called with the correct argument
        verify(quizInterface, times(1)).getQuestionsFromId(eq(questionIds));

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockQuestions, response.getBody());
    }

    @Test
    public void testCalculateResult() {
        // Mock input data
        int quizId = 1;
        List<Response> responses = new ArrayList<>();
        responses.add(new Response(1, "Option1"));
        responses.add(new Response(2, "Option2"));
        responses.add(new Response(3, "Option3"));

        // Mock external service response
        ResponseEntity<Integer> scoreResponse = ResponseEntity.ok(2);
        when(quizInterface.getScore(anyList())).thenReturn(scoreResponse);

        // Test the service method
        ResponseEntity<Integer> response = quizService.calculateResult(quizId, responses);

        // Verify that the external service method was called with the correct argument
        verify(quizInterface, times(1)).getScore(eq(responses));

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody());
    }
}

