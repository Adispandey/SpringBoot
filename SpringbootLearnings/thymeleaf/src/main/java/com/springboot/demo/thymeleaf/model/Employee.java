package com.springboot.demo.thymeleaf.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
}
