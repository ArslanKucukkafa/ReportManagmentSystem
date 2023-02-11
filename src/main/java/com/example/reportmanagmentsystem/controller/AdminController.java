package com.example.reportmanagmentsystem.controller;

import com.example.reportmanagmentsystem.model.Laborant;
import com.example.reportmanagmentsystem.model.dto.RoleDto;
import com.example.reportmanagmentsystem.model.response.LoginResponse;
import com.example.reportmanagmentsystem.model.response.Response;
import com.example.reportmanagmentsystem.service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;

    @PostMapping("/save/role")
    public Response saveRole(RoleDto roleDto){
       return adminService.saveRole(roleDto);
    }

    @PutMapping("/update/roleLaborant")
    public Response update(Laborant laborant){return adminService.putRoleToLaborant(laborant);}

    @GetMapping("/getAllUsers")
    public Response getAllUsers(){
        return new LoginResponse();
    }
    @GetMapping("/hello")
    public String HelloAdmin(){
        return "Merhaba Admin";
    }
}
