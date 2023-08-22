package com.adarsh.quizservice.entity;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuizTest {

    @Test
    public void testQuizConstructor() {
        Integer id = 1;
        String quizTitle = "Test Quiz";
        List<Integer> questionIds = Arrays.asList(1, 2, 3);

        Quiz quiz = new Quiz(id, quizTitle, questionIds);

        assertEquals(id, quiz.getId());
        assertEquals(quizTitle, quiz.getQuizTitle());
        assertEquals(questionIds, quiz.getQuestionIds());
    }

    @Test
    public void testQuizSetters() {
        Quiz quiz = new Quiz();

        Integer id = 1;
        String quizTitle = "Test Quiz";
        List<Integer> questionIds = Arrays.asList(1, 2, 3);

        quiz.setId(id);
        quiz.setQuizTitle(quizTitle);
        quiz.setQuestionIds(questionIds);

        assertEquals(id, quiz.getId());
        assertEquals(quizTitle, quiz.getQuizTitle());
        assertEquals(questionIds, quiz.getQuestionIds());
    }

    @Test
    public void testEqualsAndHashCode() {
        List<Integer> questionIds1 = Arrays.asList(1, 2, 3);
        List<Integer> questionIds2 = Arrays.asList(1, 2, 3);
        List<Integer> questionIds3 = Arrays.asList(4, 5, 6);

        Quiz quiz1 = new Quiz(1, "Quiz 1", questionIds1);
        Quiz quiz2 = new Quiz(1, "Quiz 1", questionIds2);
        Quiz quiz3 = new Quiz(2, "Quiz 3", questionIds3);

        assertEquals(quiz1, quiz2); // Same id and questionIds, so should be equal
        assertNotEquals(quiz1, quiz3); // Different id, so should not be equal

        assertEquals(quiz1.hashCode(), quiz2.hashCode());
        assertNotEquals(quiz1.hashCode(), quiz3.hashCode());
    }


    @Test
    public void testToString() {
        Integer id = 1;
        String quizTitle = "Test Quiz";
        List<Integer> questionIds = Arrays.asList(1, 2, 3);

        Quiz quiz = new Quiz(id, quizTitle, questionIds);

        String expectedToString = "Quiz(id=1, quizTitle=Test Quiz, questionIds=[1, 2, 3])";
        assertEquals(expectedToString, quiz.toString());
    }

    @Test
    public void testNoArgsConstructor() {
        Quiz quiz = new Quiz();

        assertNull(quiz.getId());
        assertNull(quiz.getQuizTitle());
        assertNull(quiz.getQuestionIds());
    }
}
