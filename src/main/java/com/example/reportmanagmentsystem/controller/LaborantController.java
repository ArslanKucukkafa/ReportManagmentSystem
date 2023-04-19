package com.example.reportmanagmentsystem.controller;

import com.example.reportmanagmentsystem.model.Laborant;
import com.example.reportmanagmentsystem.model.Report;
import com.example.reportmanagmentsystem.model.dto.*;
import com.example.reportmanagmentsystem.model.response.Response;
import com.example.reportmanagmentsystem.model.response.SuccesResponse;
import com.example.reportmanagmentsystem.service.LaborantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    return laborantService.registerLaborant(registerDto);
    }
    @PostMapping("/login")
    public Response loginLaborant(@RequestBody LaborantLoginDto loginDto){
       return laborantService.loginLaborant(loginDto);
    }

    @PostMapping(value = {"/saveReport"},consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Response saveReport(@RequestPart("reportSaveDto") ReportSaveDto reportSaveDto,@RequestPart("image") MultipartFile []image) throws Exception {
        return new SuccesResponse(laborantService.saveReport(reportSaveDto,image[0]).toString(),true);
    }
    @PutMapping (value = {"/updateReport"},consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Response updateReport(@RequestPart("ReportGetDto") ReportGetDto reportGetDto,@RequestPart("image") MultipartFile []image) throws Exception {
        return laborantService.updateReport(reportGetDto,image[0]);
    }
    @PostMapping("/deleteReport")
    public Response deleteReport(@RequestParam Long report_id){
        return laborantService.deleteReport(report_id);}
    @GetMapping("/getAllReports")
    public List<ReportGetDto> getAllReports(){ return laborantService.getAllReports();}

    @GetMapping("/getAllPatientReports")
    public List<Report> getPatinetReports(@RequestParam String patient_identity_no){
        return laborantService.getAllReportsWithAboutPatient(patient_identity_no);}

    @GetMapping("/currentUser")
    public Optional<Laborant> currentUser(){
        return laborantService.getPrincipal();
    }
    @GetMapping("/getReport")
    public Report getReport(@RequestParam Long reportId){ return laborantService.getReport(reportId);}
}
