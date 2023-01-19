package com.example.reportmanagmentsystem;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Arslan Apı", version = "2.0", description = "Loaborant Documentation"))
public class ReportManagmentSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReportManagmentSystemApplication.class, args);
    }


}
