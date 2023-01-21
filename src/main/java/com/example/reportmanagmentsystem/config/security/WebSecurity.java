package com.example.reportmanagmentsystem.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class WebSecurity {


    private static final String[] LaborantControllerEndpoints = {
            "/api/v1/laboratories/login",
            "/api/v1/laboratories/save",
            "/api/v1/laboratories/hello",
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws Exception{
        httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET,"/api/v1/admin/hello").hasAnyAuthority("ADMIN");

        httpSecurity.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(LaborantControllerEndpoints).permitAll().anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return httpSecurity.build();
    }
}
