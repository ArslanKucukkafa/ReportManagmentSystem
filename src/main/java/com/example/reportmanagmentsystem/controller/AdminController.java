package com.example.reportmanagmentsystem.controller;

import com.example.reportmanagmentsystem.model.Laborant;
import com.example.reportmanagmentsystem.model.Report;
import com.example.reportmanagmentsystem.model.dto.ReportDto;
import com.example.reportmanagmentsystem.model.dto.RoleDto;
import com.example.reportmanagmentsystem.model.response.Response;
import com.example.reportmanagmentsystem.repository.LaborantRepository;
import com.example.reportmanagmentsystem.repository.ReportRepository;
import com.example.reportmanagmentsystem.service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/admin")
public class AdminController {
    @Autowired
    private AdminServiceImpl adminService;
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private LaborantRepository laborantRepository;

    @PostMapping("/saveRole")
    public Response saveRole(RoleDto roleDto){
       return adminService.saveRole(roleDto);
    }
    @PostMapping("/deleteLaborant")
    public Response deleteLaborant(@RequestParam String laborant_id){return adminService.deleteLaborant(laborant_id);}
    @GetMapping("/getAllReportsLaboratories")
    public List<Report> getAllReportsLaboratories(@RequestParam String laborant_id){return adminService.getAllReportsLaboratories(laborant_id);}

    @GetMapping("/getAllLaboratories")
    public List<Laborant> getAllLaboratories(){return adminService.getAllPerson();}

    //Hesap durumu aktifse deaktif degilse aktif olarak degiştirilir.
    @PutMapping("/laborantAccountActivate")
    public Response laborantAccountActivate(@RequestParam Boolean activated,String laborant_id){return adminService.laborantAccountActivate(activated,laborant_id);}

    @GetMapping("/hello")
    public String HelloAdmin(){
        return "Merhaba Admin";
    }

    @PostMapping("/changeRole")
    public Response changeRole(@RequestParam String laborant_id){
       return adminService.upgradeRole(laborant_id);
    }
}
