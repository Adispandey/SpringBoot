package com.adarsh.springdemo.rest;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(force = true)
public class StudentErrorResponse {
    private int status;
    private String message;

    private long timestamp;
}
