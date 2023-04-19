package com.example.reportmanagmentsystem.controller;

import com.example.reportmanagmentsystem.model.Laborant;
import com.example.reportmanagmentsystem.model.response.ErrorResponse;
import com.example.reportmanagmentsystem.model.response.Response;
import com.example.reportmanagmentsystem.model.response.SuccesResponse;
import com.example.reportmanagmentsystem.service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/admin")
public class AdminController {
    @Autowired
    private AdminServiceImpl adminService;
    @PostMapping("/deleteLaborant")
    public Response deleteLaborant(@RequestParam String laborant_id){return adminService.deleteLaborant(laborant_id);}
    @GetMapping("/getAllReportsLaboratories")
    public Response getAllReportsLaboratories(@RequestParam String laborant_id){
        try {
            return new SuccesResponse(adminService.getAllReportsLaboratories(laborant_id).toString(),true);
        }catch (Exception ex){
            return new ErrorResponse(ex.toString(),false);
        }
    }

    @GetMapping("/getAllLaboratories/{activate}")
    public List<Laborant> getAllLaboratories(@PathVariable(value ="activate") boolean activate){return adminService.getAllPerson(activate);}

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

    @GetMapping("/laborantDetail")
    public ResponseEntity laborantDetail(String laborant_id){;
        try {
            return new ResponseEntity<>(adminService.getDetailsLaborant(laborant_id), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(ex,HttpStatus.BAD_REQUEST);
        }
    }
}
