package com.adarsh.quizservice.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionWrapperTest {

    @Test
    public void testConstructorAndGetters() {
        Integer id = 1;
        String questionTitle = "What is the capital of France?";
        String option1 = "London";
        String option2 = "Paris";
        String option3 = "Berlin";
        String option4 = "Madrid";

        QuestionWrapper questionWrapper = new QuestionWrapper(id, questionTitle, option1, option2, option3, option4);

        // Test constructor
        assertNotNull(questionWrapper);
        assertEquals(id, questionWrapper.getId());
        assertEquals(questionTitle, questionWrapper.getQuestionTitle());
        assertEquals(option1, questionWrapper.getOption1());
        assertEquals(option2, questionWrapper.getOption2());
        assertEquals(option3, questionWrapper.getOption3());
        assertEquals(option4, questionWrapper.getOption4());

        // Test toString method
        String expectedToString = "QuestionWrapper(id=1, questionTitle=What is the capital of France?, " +
                "option1=London, option2=Paris, option3=Berlin, option4=Madrid)";
        assertEquals(expectedToString, questionWrapper.toString());
    }
    @Test
    public void testSetters() {
        QuestionWrapper questionWrapper = new QuestionWrapper();

        // Test setters
        questionWrapper.setId(2);
        questionWrapper.setQuestionTitle("What is the capital of Germany?");
        questionWrapper.setOption1("London");
        questionWrapper.setOption2("Paris");
        questionWrapper.setOption3("Berlin");
        questionWrapper.setOption4("Madrid");

        assertEquals(2, questionWrapper.getId());
        assertEquals("What is the capital of Germany?", questionWrapper.getQuestionTitle());
        assertEquals("London", questionWrapper.getOption1());
        assertEquals("Paris", questionWrapper.getOption2());
        assertEquals("Berlin", questionWrapper.getOption3());
        assertEquals("Madrid", questionWrapper.getOption4());
    }

    @Test
    public void testEqualsAndHashCode() {
        QuestionWrapper questionWrapper1 = new QuestionWrapper(1, "Question1", "Option1", "Option2", "Option3", "Option4");
        QuestionWrapper questionWrapper2 = new QuestionWrapper(1, "Question1", "Option1", "Option2", "Option3", "Option4");
        QuestionWrapper questionWrapper3 = new QuestionWrapper(2, "Question2", "Option1", "Option2", "Option3", "Option4");

        // Test equals
        assertEquals(questionWrapper1, questionWrapper1);
        assertEquals(questionWrapper1, questionWrapper2);
        assertNotEquals(questionWrapper1, questionWrapper3);

        // Test hashCode
        assertEquals(questionWrapper1.hashCode(), questionWrapper2.hashCode());
        assertNotEquals(questionWrapper1.hashCode(), questionWrapper3.hashCode());
    }

    @Test
    public void testToString() {
        Integer id = 1;
        String questionTitle = "What is the capital of France?";
        String option1 = "London";
        String option2 = "Paris";
        String option3 = "Berlin";
        String option4 = "Madrid";

        QuestionWrapper questionWrapper = new QuestionWrapper(id, questionTitle, option1, option2, option3, option4);

        // Test toString method
        String expectedToString = "QuestionWrapper(id=1, questionTitle=What is the capital of France?, " +
                "option1=London, option2=Paris, option3=Berlin, option4=Madrid)";
        assertEquals(expectedToString, questionWrapper.toString());
    }
}

