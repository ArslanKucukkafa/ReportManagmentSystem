package com.example.reportmanagmentsystem.service.interfaces;

import com.example.reportmanagmentsystem.model.Laborant;
import com.example.reportmanagmentsystem.model.Report;
import com.example.reportmanagmentsystem.model.response.Response;

import java.util.HashMap;
import java.util.List;

public interface AdminService {

    Response laborantAccountActivate(Boolean activated, String laborant_id);

    Response deleteLaborant(String laborant_id);

    List<Report> getAllReportsLaboratories(String laborant_id);

    List<Laborant> getAllPerson(boolean activate);

    HashMap<String, String> getDetailsLaborant(String laborant_id);
}
