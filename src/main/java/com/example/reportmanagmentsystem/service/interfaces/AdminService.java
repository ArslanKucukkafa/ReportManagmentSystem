package com.example.reportmanagmentsystem.service.interfaces;

import com.example.reportmanagmentsystem.model.Laborant;
import com.example.reportmanagmentsystem.model.Role;
import com.example.reportmanagmentsystem.model.dto.RoleDto;
import com.example.reportmanagmentsystem.model.response.Response;

public interface AdminService {

    Response saveRole(RoleDto roleDto);

    Response addRoleToLaborant(Laborant laborant);

    abstract Response laborantAccountActivate(Boolean activated, String laborant_id);

    Response deleteLaborant(Laborant laborant);

    Response getAllReportsLaboratories(String laborant_id);

    Response getAllPerson();
}
