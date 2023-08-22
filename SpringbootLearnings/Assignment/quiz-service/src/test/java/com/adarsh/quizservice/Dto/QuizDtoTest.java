package com.adarsh.quizservice.Dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuizDtoTest {

    @Test
    public void testGettersAndSetters() {
        QuizDto quizDto = new QuizDto();

        // Test setters
        quizDto.setCategoryName("Category1");
        quizDto.setNumQuestions(10);
        quizDto.setTitle("Quiz Title");

        // Test getters
        assertEquals("Category1", quizDto.getCategoryName());
        assertEquals(10, quizDto.getNumQuestions());
        assertEquals("Quiz Title", quizDto.getTitle());
    }

    @Test
    public void testEqualsAndHashCode() {
        QuizDto quizDto1 = new QuizDto();
        quizDto1.setCategoryName("Category1");
        quizDto1.setNumQuestions(10);
        quizDto1.setTitle("Quiz Title");

        QuizDto quizDto2 = new QuizDto();
        quizDto2.setCategoryName("Category1");
        quizDto2.setNumQuestions(10);
        quizDto2.setTitle("Quiz Title");

        QuizDto quizDto3 = new QuizDto();
        quizDto3.setCategoryName("Category2");
        quizDto3.setNumQuestions(5);
        quizDto3.setTitle("Another Quiz");

        assertEquals(quizDto1, quizDto2); // quizDto1 and quizDto2 have the same values, so should be equal
        assertNotEquals(quizDto1, quizDto3); // quizDto1 and quizDto3 have different values, so should not be equal

        assertEquals(quizDto1.hashCode(), quizDto2.hashCode());
        assertNotEquals(quizDto1.hashCode(), quizDto3.hashCode());
    }
}

