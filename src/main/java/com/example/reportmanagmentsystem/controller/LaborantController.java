package com.example.reportmanagmentsystem.controller;

import com.example.reportmanagmentsystem.model.dto.LaborantLoginDto;
import com.example.reportmanagmentsystem.model.dto.LaborantRegisterDto;
import com.example.reportmanagmentsystem.model.response.Response;
import com.example.reportmanagmentsystem.service.LaborantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/laboratories")
public class LaborantController {

    @Autowired
    private LaborantService laborantService;

    @PostMapping("/save")
    public ResponseEntity saveLaborant(@RequestBody LaborantRegisterDto registerDto) {
        laborantService.registerLaborant(registerDto);
        return ResponseEntity.ok("Kayıt Başarılı");
    }


    @PostMapping("/login")
    public Response loginLaborant(@RequestBody LaborantLoginDto loginDto){
       return laborantService.loginLaborant(loginDto);
    }


    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }
}
