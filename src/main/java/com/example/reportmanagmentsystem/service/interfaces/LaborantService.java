package com.example.reportmanagmentsystem.service.interfaces;

import com.example.reportmanagmentsystem.model.Report;
import com.example.reportmanagmentsystem.model.dto.LaborantLoginDto;
import com.example.reportmanagmentsystem.model.dto.LaborantRegisterDto;
import com.example.reportmanagmentsystem.model.dto.ReportDto;
import com.example.reportmanagmentsystem.model.dto.ReportSaveDto;
import com.example.reportmanagmentsystem.model.response.Response;

import java.util.List;

public interface LaborantService {

    void registerLaborant(LaborantRegisterDto registerDto);
     Response loginLaborant(LaborantLoginDto loginDto);

    Response saveReport(ReportSaveDto reportSaveDto);

    Response updateReport(ReportDto reportDto);
    String getPrincipal();

    List<Report> getAllReports();
}
