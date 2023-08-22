package com.adarsh.springdemo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.geom.AffineTransform;
import java.io.File;

public class Driver {
    public static void main(String[] args) {
        try{
// create object mapper
            ObjectMapper mapper = new ObjectMapper();

            // read JSON  file and map/convert to Java POJO:
            // data/sample-lite.json

            Students theStudent = mapper.readValue(
                    new File("src/main/resources/sample-full.json"), Students.class);

            // print first name and last name
            System.out.println("First name = " + theStudent.getFirstName());
            System.out.println("Last name = " + theStudent.getLastName());

            //print address of the street and city

            Address address = theStudent.getAddress();

            System.out.println("Street"+address.getStreet());
            System.out.println("City"+address.getCity());

            //print out the languages

            for(String lang: theStudent.getLanguages()){
                System.out.println(lang);
            }


        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
