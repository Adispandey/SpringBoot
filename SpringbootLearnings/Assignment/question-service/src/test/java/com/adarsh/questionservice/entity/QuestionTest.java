package com.adarsh.questionservice.entity;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuestionTest {

    @Test
    public void testGettersAndSetters() {
        Question question = new Question();
        question.setId(1);
        question.setCategory("Science");
        question.setDifficultyLevel("Easy");
        question.setQuestionTitle("What is the capital of France?");
        question.setOption1("London");
        question.setOption2("Paris");
        question.setOption3("Berlin");
        question.setOption4("Madrid");
        question.setRightAnswer("Paris");

        // Test getters
        assertEquals(1, question.getId());
        assertEquals("Science", question.getCategory());
        assertEquals("Easy", question.getDifficultyLevel());
        assertEquals("What is the capital of France?", question.getQuestionTitle());
        assertEquals("London", question.getOption1());
        assertEquals("Paris", question.getOption2());
        assertEquals("Berlin", question.getOption3());
        assertEquals("Madrid", question.getOption4());
        assertEquals("Paris", question.getRightAnswer());
    }

    @Test
    public void testNoArgsConstructor() {
        // Test no-args constructor
        Question question = new Question();

        // Make sure all fields are initialized to their default values (null for objects, 0 for int)
        assertNull(question.getCategory());
        assertNull(question.getDifficultyLevel());
        assertNull(question.getQuestionTitle());
        assertNull(question.getOption1());
        assertNull(question.getOption2());
        assertNull(question.getOption3());
        assertNull(question.getOption4());
        assertNull(question.getRightAnswer());
        assertEquals(0, question.getId());
    }

    @Test
    public void testToString() {
        Question question = new Question();
        question.setId(1);
        question.setCategory("Science");
        question.setDifficultyLevel("Easy");
        question.setQuestionTitle("What is the capital of France?");
        question.setOption1("London");
        question.setOption2("Paris");
        question.setOption3("Berlin");
        question.setOption4("Madrid");
        question.setRightAnswer("Paris");

        String expectedToString = "Question(id=1, category=Science, difficultyLevel=Easy, " +
                "questionTitle=What is the capital of France?, option1=London, option2=Paris, " +
                "option3=Berlin, option4=Madrid, rightAnswer=Paris)";

        // Test toString method
        assertEquals(expectedToString, question.toString());
    }
    @Test
    public void testEqualsAndHashCode() {
        Question question1 = new Question(1, "Science", "Easy", "What is the capital of France?", "London", "Paris", "Berlin", "Madrid", "Paris");
        Question question2 = new Question(1, "Science", "Easy", "What is the capital of France?", "London", "Paris", "Berlin", "Madrid", "Paris");
        Question question3 = new Question(2, "Geography", "Medium", "What is the capital of Germany?", "London", "Paris", "Berlin", "Madrid", "Berlin");

        // Test equals
        assertEquals(question1, question1);
        assertEquals(question1, question2);
        assertNotEquals(question1, question3);

        // Test hashCode
        assertEquals(question1.hashCode(), question2.hashCode());
        assertNotEquals(question1.hashCode(), question3.hashCode());

        // Test equals with null
        assertNotEquals(question1, null);
        assertNotEquals(null, question1);

        // Test equals with different object type
        assertNotEquals(question1, "Question");
    }
    @Test
    public void testAllArgsConstructor() {
        Question question = new Question(1, "Science", "Easy", "What is the capital of France?", "London", "Paris", "Berlin", "Madrid", "Paris");

        // Test all-args constructor
        assertEquals(1, question.getId());
        assertEquals("Science", question.getCategory());
        assertEquals("Easy", question.getDifficultyLevel());
        assertEquals("What is the capital of France?", question.getQuestionTitle());
        assertEquals("London", question.getOption1());
        assertEquals("Paris", question.getOption2());
        assertEquals("Berlin", question.getOption3());
        assertEquals("Madrid", question.getOption4());
        assertEquals("Paris", question.getRightAnswer());
    }
}

