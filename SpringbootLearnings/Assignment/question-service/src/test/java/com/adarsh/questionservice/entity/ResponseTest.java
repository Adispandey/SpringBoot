package com.adarsh.questionservice.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ResponseTest {

    @Test
    public void testAllArgsConstructor() {
        Integer id = 1;
        String response = "Option1";

        Response responseObj = new Response(id, response);

        assertNotNull(responseObj);
        assertEquals(id, responseObj.getId());
        assertEquals(response, responseObj.getResponse());
    }

    @Test
    public void testRequiredArgsConstructor() {
        String response = null;

        Response responseObj = new Response();

        assertNotNull(responseObj);
        assertNull(responseObj.getId());
        assertEquals(response, responseObj.getResponse());
    }

    @Test
    public void testGettersAndSetters() {
        Response responseObj = new Response();

        // Test setters
        responseObj.setId(1);
        responseObj.setResponse("Option1");

        // Test getters
        assertEquals(1, responseObj.getId());
        assertEquals("Option1", responseObj.getResponse());
    }

    @Test
    public void testToString() {
        Integer id = 1;
        String response = "Option1";

        Response responseObj = new Response(id, response);

        String expectedToString = "Response(id=1, response=Option1)";
        assertEquals(expectedToString, responseObj.toString());
    }
}

