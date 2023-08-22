package com.adarsh.questionservice.service;

import com.adarsh.questionservice.dao.QuestionDao;
import com.adarsh.questionservice.entity.Question;
import com.adarsh.questionservice.entity.QuestionWrapper;
import com.adarsh.questionservice.entity.Response;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class QuestionServiceTest {

    @Mock
    private QuestionDao questionDao;

    @InjectMocks
    private QuestionService questionService;

    @Test
    public void testGetAllQuestions() {
        List<Question> mockQuestions = new ArrayList<>();
        mockQuestions.add(new Question(1, "Category1", "Easy", "Question1", "Option1", "Option2", "Option3", "Option4", "Option1"));
        mockQuestions.add(new Question(2, "Category2", "Medium", "Question2", "Option1", "Option2", "Option3", "Option4", "Option2"));

        Mockito.when(questionDao.findAll()).thenReturn(mockQuestions);

        ResponseEntity<List<Question>> response = questionService.getAllQuestions();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockQuestions, response.getBody());
    }

    @Test
    public void testGetQuestionByCategory() {
        String category = "Category1";
        List<Question> mockQuestions = new ArrayList<>();
        mockQuestions.add(new Question(1, "Category1", "Easy", "Question1", "Option1", "Option2", "Option3", "Option4", "Option1"));
        mockQuestions.add(new Question(3, "Category1", "Medium", "Question3", "Option1", "Option2", "Option3", "Option4", "Option1"));

        Mockito.when(questionDao.findByCategory(category)).thenReturn(mockQuestions);

        ResponseEntity<List<Question>> response = questionService.getQuestionByCategory(category);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockQuestions, response.getBody());
    }

    @Test
    public void testAddQuestion() {
        Question newQuestion = new Question(4, "Category3", "Hard", "Question4", "Option1", "Option2", "Option3", "Option4", "Option3");

        Mockito.when(questionDao.save(any(Question.class))).thenReturn(newQuestion);

        ResponseEntity<String> response = questionService.addQuestion(newQuestion);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Success" + newQuestion, response.getBody());
    }

    @Test
    public void testUpdateQuestion() {
        Question existingQuestion = new Question(1, "Category1", "Easy", "Question1", "Option1", "Option2", "Option3", "Option4", "Option1");
        Question updatedQuestion = new Question(1, "Category1", "Easy", "Updated Question1", "Option1", "Option2", "Option3", "Option4", "Option1");

        Mockito.when(questionDao.save(any(Question.class))).thenReturn(updatedQuestion);
        Mockito.when(questionDao.findById(existingQuestion.getId())).thenReturn(Optional.of(existingQuestion));

        ResponseEntity<String> response = questionService.updateQuestion(updatedQuestion);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Successfully updated" + updatedQuestion, response.getBody());
    }

    @Test
    public void testDeleteQuestion() {
        int questionId = 1;
        Question mockQuestion = new Question(1, "Category1", "Easy", "Question1", "Option1", "Option2", "Option3", "Option4", "Option1");

        // Simulate question found in the database
        Mockito.when(questionDao.findById(questionId)).thenReturn(Optional.of(mockQuestion));

        doNothing().when(questionDao).deleteById(questionId);

        String response = questionService.deleteQuestion(questionId);

        assertNotNull(response);
        assertEquals("Question with ID " + questionId + " deleted successfully", response);

        // Verify that deleteById was called with the correct ID
        verify(questionDao, times(1)).deleteById(questionId);
    }


    @Test
    public void testGetQuestionsForQuiz() {
        String categoryName = "Category1";
        int numQuestions = 3;
        List<Integer> mockQuestionIds = List.of(1, 3, 5);

        Mockito.when(questionDao.findRandomQuestionsByCategory(categoryName, numQuestions)).thenReturn(mockQuestionIds);

        ResponseEntity<List<Integer>> response = questionService.getQuestionsForQuiz(categoryName, numQuestions);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockQuestionIds, response.getBody());
    }

    @Test
    public void testGetQuestionsFromId() {
        List<Integer> questionIds = List.of(1, 3, 5);
        List<Question> mockQuestions = new ArrayList<>();
        mockQuestions.add(new Question(1, "Category1", "Easy", "Question1", "Option1", "Option2", "Option3", "Option4", "Option1"));
        mockQuestions.add(new Question(3, "Category1", "Medium", "Question3", "Option1", "Option2", "Option3", "Option4", "Option1"));
        mockQuestions.add(new Question(5, "Category2", "Hard", "Question5", "Option1", "Option2", "Option3", "Option4", "Option1"));

        Mockito.when(questionDao.findById(anyInt())).thenAnswer(invocation -> {
            int id = invocation.getArgument(0);
            return Optional.of(mockQuestions.stream().filter(q -> q.getId() == id).findFirst().orElse(null));
        });

        ResponseEntity<List<QuestionWrapper>> response = questionService.getQuestionsFromId(questionIds);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<QuestionWrapper> questionWrappers = response.getBody();
        assertNotNull(questionWrappers);
        assertEquals(questionIds.size(), questionWrappers.size());

        for (int i = 0; i < questionIds.size(); i++) {
            QuestionWrapper wrapper = questionWrappers.get(i);
            Question mockQuestion = mockQuestions.get(i);
            assertNotNull(wrapper);
            assertEquals(mockQuestion.getId(), wrapper.getId());
            assertEquals(mockQuestion.getQuestionTitle(), wrapper.getQuestionTitle());
            assertEquals(mockQuestion.getOption1(), wrapper.getOption1());
            assertEquals(mockQuestion.getOption2(), wrapper.getOption2());
            assertEquals(mockQuestion.getOption3(), wrapper.getOption3());
            assertEquals(mockQuestion.getOption4(), wrapper.getOption4());
        }
    }

    @Test
    public void testGetScore() {
        List<Response> responses = new ArrayList<>();
        responses.add(new Response(1, "Option1"));
        responses.add(new Response(3, "Option2"));
        responses.add(new Response(5, "Option3"));

        List<Question> mockQuestions = new ArrayList<>();
        mockQuestions.add(new Question(1, "Category1", "Easy", "Question1", "Option1", "Option2", "Option3", "Option4", "Option1"));
        mockQuestions.add(new Question(3, "Category1", "Medium", "Question3", "Option1", "Option2", "Option3", "Option4", "Option1"));
        mockQuestions.add(new Question(5, "Category2", "Hard", "Question5", "Option1", "Option2", "Option3", "Option4", "Option1"));

        Mockito.when(questionDao.findById(anyInt())).thenAnswer(invocation -> {
            int id = invocation.getArgument(0);
            return Optional.of(mockQuestions.stream().filter(q -> q.getId() == id).findFirst().orElse(null));
        });

        ResponseEntity<Integer> response = questionService.getScore(responses);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody());
    }

    @Test
    public void testAddQuestionFailure() {
        Question newQuestion = new Question(4, "Category3", "Hard", "Question4", "Option1", "Option2", "Option3", "Option4", "Option3");

        // Simulate a failure to add the question
        Mockito.when(questionDao.save(any(Question.class))).thenThrow(new RuntimeException());

        ResponseEntity<String> response = questionService.addQuestion(newQuestion);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Failed to add data ", response.getBody());
    }

    @Test
    public void testUpdateQuestionFailure() {
        Question existingQuestion = new Question(1, "Category1", "Easy", "Question1", "Option1", "Option2", "Option3", "Option4", "Option1");
        Question updatedQuestion = new Question(1, "Category1", "Easy", "Updated Question1", "Option1", "Option2", "Option3", "Option4", "Option1");

        // Simulate a failure to update the question
        Mockito.when(questionDao.save(any(Question.class))).thenThrow(new RuntimeException());
        Mockito.when(questionDao.findById(existingQuestion.getId())).thenReturn(Optional.of(existingQuestion));

        ResponseEntity<String> response = questionService.updateQuestion(updatedQuestion);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_GATEWAY, response.getStatusCode());
        assertEquals("Failed to load ", response.getBody());
    }


    @Test
    public void testDeleteQuestionByIdNotFound() {
        int questionId = 100;

        // Simulate question not found in the database
        Mockito.when(questionDao.findById(questionId)).thenReturn(Optional.empty());

        String response = questionService.deleteQuestion(questionId);

        assertNotNull(response);
        assertEquals("Question with ID " + questionId + " not found", response);
    }



}
