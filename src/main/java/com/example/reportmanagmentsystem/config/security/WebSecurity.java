package com.example.reportmanagmentsystem.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;


@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurity {


    @Autowired
    JwtTokenFilter jwtTokenFilter;


    private static final String[] LaborantControllerEndpoints = {
            "/api/v1/laboratories/updateReport/**",
            "/api/v1/laboratories/saveReport/**",
            "/api/v1/laboratories/saveReport",
            "/api/v1/laboratories/deleteReport/**",
            "/api/v1/laboratories/getReport/**",
            "/api/v1/laboratories/getAllReports/**",
            "/api/v1/laboratories/getAllPatientReports/**",
            "/api/v1/laboratories/currentUser"
    };
    private static final String[] AdminControllerEndpoints = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/api/v1/admin/**"
    };
    private static final String[] LogRegisterControllerEndpoints = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/api/v1/laboratories/save",
            "/api/v1/laboratories/login"

    };


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws Exception{
        httpSecurity.cors().and().csrf().disable().authorizeRequests().antMatchers(LaborantControllerEndpoints).hasAuthority("LABORANT");
        httpSecurity.authorizeRequests().antMatchers(AdminControllerEndpoints).hasAuthority("ADMIN");
        httpSecurity.cors().and().csrf().disable().authorizeRequests().antMatchers(LogRegisterControllerEndpoints).permitAll()
                .anyRequest().authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
