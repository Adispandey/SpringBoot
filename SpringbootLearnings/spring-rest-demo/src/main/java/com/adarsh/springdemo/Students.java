package com.adarsh.springdemo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Students {
    private int id;
    private String firstName;
    private String lastName;
    private boolean active;
    private Address address;

    private  String[] languages;



}
