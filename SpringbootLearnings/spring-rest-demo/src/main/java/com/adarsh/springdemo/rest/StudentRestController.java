package com.adarsh.springdemo.rest;


import com.adarsh.springdemo.entity.Std;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Std> stds;

    //post construct to load the students data only once
    @PostConstruct
    public void loadData(){

        stds = new ArrayList<>();

        stds.add(new Std( 1,"Adarsh","Pandey"));
        stds.add(new Std(2,"Adi","Pandey"));
        stds.add(new Std(3,"Jinan","Khan"));



    }
    //define the end pints for students--- to return list of the students
    @GetMapping("/std")
    public List<Std> getStudents(){

        return stds;
    }

    //define endpoints for the "students/{students}" --- returning student index

    @GetMapping("/std/{id}")
    public Std getStd(@PathVariable int id){

    //just index into the list

        //check student id against list size
        if((id>=stds.size() || id > 0)){
            throw new StudentNotFoundException("Student not found"+id);
        }

        return  stds.get(id);
    }

}
