package com.adarsh.springdemo.entity;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@ToString
public class Std {

    private int id;
    private String firstName;
    private String lastName;

}
