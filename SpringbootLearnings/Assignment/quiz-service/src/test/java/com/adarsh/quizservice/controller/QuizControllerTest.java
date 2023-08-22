package com.adarsh.quizservice.controller;

import com.adarsh.quizservice.Dto.QuizDto;
import com.adarsh.quizservice.entity.QuestionWrapper;
import com.adarsh.quizservice.entity.Response;
import com.adarsh.quizservice.service.QuizService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class QuizControllerTest {

    @Mock
    private QuizService quizService;

    @InjectMocks
    private QuizController quizController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateQuiz() {
        // Mock input data
        QuizDto quizDto = new QuizDto();
        quizDto.setCategoryName("Category1");
        quizDto.setNumQuestions(5);
        quizDto.setTitle("Quiz Title");

        // Mock service response
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Quiz created successfully");
        when(quizService.createQuiz(anyString(), anyInt(), anyString())).thenReturn(expectedResponse);

        // Test the controller method
        ResponseEntity<String> response = quizController.createQuiz(quizDto);

        // Verify the service method was called with the correct arguments
        verify(quizService, times(1)).createQuiz(eq("Category1"), eq(5), eq("Quiz Title"));

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Quiz created successfully", response.getBody());
    }

    @Test
    public void testGetQuizQuestions() {
        // Mock input data
        int quizId = 1;

        // Mock service response
        List<QuestionWrapper> mockQuestions = new ArrayList<>();
        mockQuestions.add(new QuestionWrapper(1, "Question1", "Option1", "Option2", "Option3", "Option4"));
        when(quizService.getQuizQuestions(anyInt())).thenReturn(ResponseEntity.ok(mockQuestions));

        // Test the controller method
        ResponseEntity<List<QuestionWrapper>> response = quizController.getQuizQuestions(quizId);

        // Verify the service method was called with the correct argument
        verify(quizService, times(1)).getQuizQuestions(eq(quizId));

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockQuestions, response.getBody());
    }

    @Test
    public void testSubmitQuiz() {
        // Mock input data
        int quizId = 1;
        List<Response> responses = new ArrayList<>();
        responses.add(new Response(1, "Option1"));
        responses.add(new Response(2, "Option2"));
        responses.add(new Response(3, "Option3"));

        // Mock service response
        ResponseEntity<Integer> expectedResponse = ResponseEntity.ok(2);
        when(quizService.calculateResult(anyInt(), anyList())).thenReturn(expectedResponse);

        // Test the controller method
        ResponseEntity<Integer> response = quizController.submitQuiz(quizId, responses);

        // Verify the service method was called with the correct arguments
        verify(quizService, times(1)).calculateResult(eq(quizId), eq(responses));

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody());
    }

}
