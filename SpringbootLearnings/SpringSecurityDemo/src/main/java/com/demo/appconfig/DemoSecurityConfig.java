package com.demo.appconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {


    // adding a reference to our security data source
//    @Autowired
//    private DataSource securityDataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{


        //using jdbc authentication
//        auth.jdbcAuthentication().dataSource(securityDataSource);

        // add our users for in memory authentication
        User.UserBuilder users = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser(users.username("Adarsh").password("Adi@123").roles("Employee"))
                .withUser(users.username("Nitish").password("Nitish@123").roles("Employee","Manager"))
                .withUser(users.username("Rakesh").password("Rakesh@123").roles("Employee","Admin"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasRole("Employee")
                .antMatchers("/leaders/**").hasRole("Manager")
                .antMatchers("/systems/**").hasRole("Admin")
                .and()
                .formLogin()
                .loginPage("/showMyLoginPage")
                .loginProcessingUrl("/authenticateTheUser")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");
    }
}
