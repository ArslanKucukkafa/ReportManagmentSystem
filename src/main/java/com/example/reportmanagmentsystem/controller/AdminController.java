package com.example.reportmanagmentsystem.controller;

import com.example.reportmanagmentsystem.model.Laborant;
import com.example.reportmanagmentsystem.model.dto.RoleDto;
import com.example.reportmanagmentsystem.model.response.Response;
import com.example.reportmanagmentsystem.repository.ReportRepository;
import com.example.reportmanagmentsystem.service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/admin")
public class AdminController {
    @Autowired
    private AdminServiceImpl adminService;
    @Autowired
    private ReportRepository reportRepository;

    @PostMapping("/saveRole")
    public Response saveRole(RoleDto roleDto){
       return adminService.saveRole(roleDto);
    }
    @PostMapping("/deleteLaborant")
    public Response deleteLaborant(@RequestBody Laborant laborant){return adminService.deleteLaborant(laborant);}

    @GetMapping("/getAllReportsLaboratories")
    public Response getAllReportsLaboratories(@RequestParam String laborant_id){return adminService.getAllReportsLaboratories(laborant_id);}

    @GetMapping("/getAllLaboratories")
    public Response getAllLaboratories(){return adminService.getAllPerson();}

    //Hesap durumu aktifse deaktif degilse aktif olarak degiştirilir.
    @PutMapping("/laborantAccountActivate")
    public Response laborantAccountActivate(@RequestBody Boolean activated,String laborant_id){return adminService.laborantAccountActivate(activated,laborant_id);}

    @PutMapping("/roleUpgrade")
    public Response roleUpgrade(){

    }

    @GetMapping("/hello")
    public String HelloAdmin(){
        return "Merhaba Admin";
    }
}
