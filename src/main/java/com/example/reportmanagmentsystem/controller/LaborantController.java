package com.example.reportmanagmentsystem.controller;

import com.example.reportmanagmentsystem.model.Laborant;
import com.example.reportmanagmentsystem.model.Report;
import com.example.reportmanagmentsystem.model.dto.LaborantLoginDto;
import com.example.reportmanagmentsystem.model.dto.LaborantRegisterDto;
import com.example.reportmanagmentsystem.model.dto.ReportDto;
import com.example.reportmanagmentsystem.model.dto.ReportSaveDto;
import com.example.reportmanagmentsystem.model.response.ErrorResponse;
import com.example.reportmanagmentsystem.model.response.Response;
import com.example.reportmanagmentsystem.model.response.SuccesResponse;
import com.example.reportmanagmentsystem.service.LaborantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/laboratories")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LaborantController {

    @Autowired
    private LaborantServiceImpl laborantService;

    @PostMapping("/save")
    public Response saveLaborant(@RequestBody LaborantRegisterDto registerDto) {
        try {
            laborantService.registerLaborant(registerDto);
            return new SuccesResponse("Kayıt işlemi başarılı",true);
        }catch (Exception e){
            return new ErrorResponse("Kayıt işlemi sırasında hata oluştu",false);
        }
    }
    @PostMapping("/login")
    public Response loginLaborant(@RequestBody LaborantLoginDto loginDto){
       return laborantService.loginLaborant(loginDto);
    }
    @PostMapping("/saveReport")
    public Response saveReport(@RequestBody ReportSaveDto reportSaveDto, MultipartFile file) throws Exception {
        return new SuccesResponse(laborantService.saveReport(reportSaveDto,file).toString(),true);
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
