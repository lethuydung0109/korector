//package com.projet.korector.security;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    private Logger log = LoggerFactory.getLogger(SecurityConfig.class);
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        // add this line to use H2 web console
//        http.headers().frameOptions().disable();
//    }
//
//}
