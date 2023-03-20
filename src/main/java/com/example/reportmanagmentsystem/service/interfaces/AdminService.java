package com.example.reportmanagmentsystem.service.interfaces;

import com.example.reportmanagmentsystem.model.Laborant;
import com.example.reportmanagmentsystem.model.Report;
import com.example.reportmanagmentsystem.model.dto.RoleDto;
import com.example.reportmanagmentsystem.model.response.Response;

import java.util.List;

public interface AdminService {

    Response saveRole(RoleDto roleDto);

    abstract Response laborantAccountActivate(Boolean activated, String laborant_id);

    Response deleteLaborant(String laborant_id);

    List<Report> getAllReportsLaboratories(String laborant_id);

    List<Laborant> getAllPerson(boolean activate);
}
