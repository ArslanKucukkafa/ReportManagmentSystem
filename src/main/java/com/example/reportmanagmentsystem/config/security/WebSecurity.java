package com.example.reportmanagmentsystem.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class WebSecurity {


    private static final String[] AUTH_WHITELIST = {
            "/api/v1/laboratories/login",
            "/api/v1/laboratories/save",
            "/api/v1/admin/save/role"

    };


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws Exception{
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET, "/api/v1/laboratories/hello").hasRole("USER");

        httpSecurity.csrf().ignoringAntMatchers(AUTH_WHITELIST).and().authorizeRequests().antMatchers("/api/v1/laboratories/save").permitAll();

        httpSecurity.csrf().and().cors().disable();
        return httpSecurity.build();
    }

}
