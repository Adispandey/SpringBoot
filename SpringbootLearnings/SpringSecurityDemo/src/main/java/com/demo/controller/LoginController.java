package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage(){
        return  "plain-login";
    }


    //add request mapping for the access denied page
    @GetMapping("/access-denied")
    public String showAccessDenied(){
        return  "access-denied";
    }


}
