package com.example.reportmanagmentsystem;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Arslan Apı", version = "2.0", description = "Loaborant Documentation"))
public class ReportManagmentSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReportManagmentSystemApplication.class, args);
    }

}
