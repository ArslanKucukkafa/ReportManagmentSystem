package com.example.reportmanagmentsystem.service.interfaces;

import com.example.reportmanagmentsystem.model.Laborant;
import com.example.reportmanagmentsystem.model.dto.RoleDto;
import com.example.reportmanagmentsystem.model.response.Response;

public interface AdminService {

    Response saveRole(RoleDto roleDto);

    Response putRoleToLaborant(Laborant laborant);

}
