package com.example.reportmanagmentsystem.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurity {

@Autowired
private final AuthenticationProvider authenticationProvider;

@Autowired
private JwtTokenFilter jwtTokenFilter;
    private static final String[] LaborantControllerEndpoints = {
            "/api/v1/laboratories/login",
            "/api/v1/laboratories/save",
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

        httpSecurity
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers(LaborantControllerEndpoints)
                .permitAll()
                .antMatchers(HttpMethod.GET,"/api/v1/laboratories/hello")
                .hasAnyRole("LABORANT")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

       /* httpSecurity.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(LaborantControllerEndpoints).permitAll().anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);*/
        return httpSecurity.build();
    }
}
