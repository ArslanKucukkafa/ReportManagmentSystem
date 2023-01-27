package com.example.reportmanagmentsystem.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
public class WebSecurity {

@Autowired
private JwtTokenFilter jwtTokenFilter;
    private static final String[] LaborantControllerEndpoints = {
            "/api/v1/laboratories/login",
            "/api/v1/laboratories/save",
            "/api/v1/laboratories/hello",
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/api/v1/admin/**"
    };




    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws Exception{
       // httpSecurity.authorizeRequests().antMatchers(HttpMethod.GET,"/api/v1/admin/**").permitAll();
        httpSecurity.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(LaborantControllerEndpoints).permitAll().anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
