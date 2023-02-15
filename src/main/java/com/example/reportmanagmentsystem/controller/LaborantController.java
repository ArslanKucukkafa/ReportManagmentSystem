package com.example.reportmanagmentsystem.controller;

import com.example.reportmanagmentsystem.model.Report;
import com.example.reportmanagmentsystem.model.dto.LaborantLoginDto;
import com.example.reportmanagmentsystem.model.dto.LaborantRegisterDto;
import com.example.reportmanagmentsystem.model.dto.ReportDto;
import com.example.reportmanagmentsystem.model.dto.ReportSaveDto;
import com.example.reportmanagmentsystem.model.response.LoginResponse;
import com.example.reportmanagmentsystem.model.response.Response;
import com.example.reportmanagmentsystem.repository.ReportRepository;
import com.example.reportmanagmentsystem.service.LaborantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/laboratories")
public class LaborantController {

    @Autowired
    private LaborantServiceImpl laborantService;
    @Autowired
    private ReportRepository reportRepository;

    @PostMapping("/save")
    public ResponseEntity saveLaborant(@RequestBody LaborantRegisterDto registerDto) {
        laborantService.registerLaborant(registerDto);
        return ResponseEntity.ok("Kayıt Başarılı");
    }


    @PostMapping("/login")
    public Response loginLaborant(@RequestBody LaborantLoginDto loginDto){
       return laborantService.loginLaborant(loginDto);
    }

    @PostMapping("/saveReport")
    public Response saveReport(@RequestBody ReportSaveDto reportSaveDto){
        return  laborantService.saveReport(reportSaveDto);
    }

    @PutMapping ("/updateReport")
    public Response updateReport(@RequestBody ReportDto reportDto){
        return laborantService.updateReport(reportDto);
    }
    @PostMapping("/deleteReport")
    public Response deleteReport(){ return new LoginResponse();
    }

    @GetMapping("/getAllReports")
    public List<Report> getAllReports(){ return laborantService.getAllReports();}

    @GetMapping("/getPatientReport")
    public Response getPatinetReports(){
        return new Response();
    }


    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @GetMapping("/currentUser")
    public String currentUser(){
        return laborantService.getPrincipal();
    }
}
