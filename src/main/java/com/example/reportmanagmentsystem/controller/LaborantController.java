package com.example.reportmanagmentsystem.controller;

import com.example.reportmanagmentsystem.model.Laborant;
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
import java.util.Optional;

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
    public Response deleteReport(@RequestParam Long report_id){ return laborantService.deleteReport(report_id);
    }

    @GetMapping("/getAllReports")
    public List<Report> getAllReports(){ return laborantService.getAllReports();}

    @GetMapping("/getAllPatientReports")
    public List<Report> getPatinetReports(@RequestParam String patient_identity_no){
        return laborantService.getAllReportsWithAboutPatient(patient_identity_no);
    }
    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @GetMapping("/currentUser")
    public Optional<Laborant> currentUser(){
        return laborantService.getPrincipal();
    }
}
