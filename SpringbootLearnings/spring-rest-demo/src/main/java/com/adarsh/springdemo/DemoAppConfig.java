package com.adarsh.springdemo;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ComponentScan("com.adarsh.springdemo")
@Configuration
public class DemoAppConfig {

}
